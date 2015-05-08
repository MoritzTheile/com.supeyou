package com.hotelorga.foundation.impl.dtohelper;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hotelorga.foundation.impl.dtohelper.csv.CSVParser;

public class ReflectionImporter {

	private static Logger log = Logger.getLogger(ReflectionImporter.class.getName());

	public static <D> D fillDTO(D dto, Map<String, String> attribName2stringvalue) throws Exception {

		List<Method> setterMethods = findSetters(dto.getClass());

		for (String attribName : attribName2stringvalue.keySet()) {
			try {

				Method setterMethod = findSetterToInvoke(findSetterForAttrib(attribName, setterMethods));

				if (setterMethod != null) {

					setterMethod.invoke(dto, getSetterArg(setterMethod, attribName2stringvalue.get(attribName)));

				}

			} catch (Exception e) {
				log.log(Level.INFO, "Problems on setting attribute '" + attribName + "' with value '" + attribName2stringvalue.get(attribName) + "' in class " + dto.getClass().getName(), e);
			}
		}

		return dto;

	}

	/**
	 * There might be multiple setters defined for one attribute in inheritance chain, so this method is responsible to find the appropriate one. Normally it is the setter of derived class, not one of the super classes.
	 */
	private static Method findSetterToInvoke(List<Method> setterMethodsForAttrib) {

		for (Method currentMethod : setterMethodsForAttrib) {

			if (!currentMethod.isBridge()) {
				return currentMethod;
			}

		}

		return null;
	}

	private static List<Method> findSetterForAttrib(String attribName, List<Method> setterMethods) {

		List<Method> result = new ArrayList<>();
		for (Method setter : setterMethods) {
			if (setter.getName().length() == attribName.length() + "set".length() &&
					setter.getName().endsWith(attribName)) {
				result.add(setter);
			}
		}

		return result;
	}

	private static Object getSetterArg(Method setterMethod, String attribValue) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		Object setterArg = null;

		if ("null".equals(attribValue)) {

			setterArg = null;

		} else {

			Class<?> attribClass = setterMethod.getParameterTypes()[0];

			if (isCollection(attribClass)) {

				Class<?> genericClass = getGenericTypeOfAttribClass(setterMethod);

				Collection<Object> list = new ArrayList<>();

				for (String splittedAttribValue : splitValues(attribValue)) {

					list.add(instantiateFromStringValue(splittedAttribValue, genericClass));

				}

				setterArg = list;

			} else {

				setterArg = instantiateFromStringValue(attribValue, attribClass);

			}

		}

		return setterArg;

	}

	/**
	 * Since java is throwing away information on generics at runtime we have to reconstruct the generic Class via TypeName String...
	 */
	private static Class<?> getGenericTypeOfAttribClass(Method setterMethod) throws ClassNotFoundException {

		Type attribType = setterMethod.getGenericParameterTypes()[0];
		String genericTypeName = extractGenericFromTypeName(attribType.toString());
		return Class.forName(genericTypeName);

	}

	private static boolean isCollection(Class<?> clazz) {

		for (Class<?> iface : clazz.getInterfaces()) {
			if (iface.equals(Collection.class)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object instantiateFromStringValue(String attribValue, Class<?> clazz) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		if (clazz.isEnum()) {

			return Enum.valueOf(((Class<? extends Enum>) clazz), attribValue);

		} else {

			return clazz.getDeclaredConstructor(String.class).newInstance(attribValue);

		}

	}

	private static List<String> splitValues(String attribValue) {
		if (attribValue.startsWith("[")) {
			attribValue = attribValue.substring(1, attribValue.length());
		}
		if (attribValue.endsWith("]")) {
			attribValue = attribValue.substring(0, attribValue.length() - 1);
		}
		String[] splittedString = attribValue.split(",");
		List<String> values = new ArrayList<>();
		for (String value : splittedString) {
			if (!"".equals(value)) {
				values.add(value.trim());
			}
		}
		return values;
	}

	private static String extractGenericFromTypeName(String className) {
		String result = null;
		int ltIndex = className.indexOf('<');
		int gtIndex = className.indexOf('>');
		if (ltIndex > 0 && ltIndex < gtIndex) {
			result = (className.substring(ltIndex + 1, gtIndex));
		}
		return result;
	}

	private static List<Method> findSetters(Class<?> clazz) {

		List<Method> setterMethods = new ArrayList<Method>();

		for (Method method : clazz.getMethods()) {

			// getters start with "set" and don't have an return parameter:
			if (method.getName().startsWith("set") && method.getParameterTypes().length == 1 && method.getReturnType().getName().equals("void")) {

				setterMethods.add(method);

			}

		}

		return setterMethods;

	}

	public static <D> void getDTOsFromCSVStream(InputStream inputStream, Charset charset, Callback<D> callback) {

		try {
			List<String> headerLine = null;

			for (List<String> row : CSVParser.parseCSV(';', inputStream, charset)) {

				if (headerLine == null) {
					headerLine = row;
					continue;
				}

				Map<String, String> attribName2attribValue = new HashMap<>();
				Iterator<String> rowIterator = row.iterator();

				for (String attribName : headerLine) {

					if (rowIterator.hasNext()) {

						attribName2attribValue.put(attribName, rowIterator.next());

					}
				}

				D dto = callback.newDTO();

				ReflectionImporter.fillDTO(dto, attribName2attribValue);

				callback.filledDTO(dto);

			}

		} catch (Exception e) {

			log.log(Level.INFO, "csv stream could not be translated to DTOs", e);

		} finally {

			callback.finished();

		}

	}

	public interface Callback<D> {

		void filledDTO(D dto);

		void finished();

		D newDTO();

	}

	// public static void main(String[] args) {
	// User2GroupDTO user2GroupDTO = new User2GroupDTO();
	// for (Method method : findSetters(user2GroupDTO.getClass())) {
	// if ("setDtoA".equals(method.getName())) {
	// System.out.println("asdf" + method.getName());
	// System.out.println(method.getDeclaringClass().getCanonicalName());
	// System.out.println(method.toGenericString());
	// System.out.println(method.isBridge());
	// }
	// }
	// }
}
