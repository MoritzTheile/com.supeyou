package com.hotelorga.foundation.impl.dtohelper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstrDTOHelper<E, D> {

	private static transient Logger log = Logger.getLogger(AbstrDTOHelper.class.getName());

	public final Class<D> dtoClass;

	public final Class<E> entityClass;

	public AbstrDTOHelper(Class<D> dtoClass, Class<E> entityClass) {

		this.dtoClass = dtoClass;

		this.entityClass = entityClass;

	}

	public D entity2DTO(E entity) throws Exception {

		if (entity == null) {
			return null;
		}

		D dto = dtoClass.newInstance();

		invokeSettersWithGetterresults(entity, entityClass, dto, dtoClass);

		return dto;
	}

	private void invokeSettersWithGetterresults(Object source, Class<?> sourceClass, Object target, Class<?> targetClass) {

		Map<String, Method> attribName2getterMethod = findGetters(sourceClass);

		Map<String, Method> attribName2setterMethod = findSetters(targetClass);

		for (String sourceAttribName : attribName2getterMethod.keySet()) {

			Method setterMethod = attribName2setterMethod.get(sourceAttribName);

			if (setterMethod == null) {
				continue;
			}
			log.log(Level.FINE, "setterMethod.getName()=" + setterMethod.getName());

			Method getterMethod = attribName2getterMethod.get(sourceAttribName);

			if (getterMethod == null) {
				continue;
			}
			log.log(Level.FINE, "getterMethod.getName()=" + getterMethod.getName());

			// This is unfortunately not reliable. *Sometimes* the type of Superclass is returned ?!?!
			// Instead just trying to invoke method and catching exceptions.
			//
			// if (!getterReturnType.equals(setterParameterType)) {
			// continue;
			// }

			try {

				Object getterResult = getterMethod.invoke(source, new Object[0]);

				setterMethod.invoke(target, getterResult);

			} catch (Exception e) {

				try {
					Class<?> getterReturnType = getterMethod.getReturnType();
					Class<?> setterParameterType = setterMethod.getParameterTypes()[0];

					log.log(Level.FINE, "ClassCastException: getterReturnType=" + getterReturnType.getName() + " not equals setterParameterType=" + setterParameterType.getName());

				} catch (Exception e2) {
					e2.printStackTrace();
				}
				continue;
			}

		}
	}

	// public E dto2Entity(D dto) throws Exception {
	// E entity = entityClass.newInstance();
	// return updateEntityFromDTO(dto, entity);
	// }

	public E updateEntityFromDTO(D dto, E entity) throws Exception {

		invokeSettersWithGetterresults(dto, dtoClass, entity, entityClass);

		return entity;

	}

	private static Map<String, Method> findGetters(Class<?> clazz) {

		Map<String, Method> attribName2getterMethod = new HashMap<String, Method>();

		String prefix = "get";

		for (Method method : clazz.getMethods()) {
			// getters start with "get" and don't have parameters:
			if (method.getName().startsWith(prefix) && method.getParameterTypes().length == 0 && method.getReturnType() != null) {
				attribName2getterMethod.put(method.getName().replaceFirst(prefix, ""), method);
			}

		}

		return attribName2getterMethod;

	}

	private static Map<String, Method> findSetters(Class<?> clazz) {

		Map<String, Method> attribName2setterMethod = new HashMap<String, Method>();
		// System.out.println("================");
		String prefix = "set";
		for (Method method : clazz.getMethods()) {
			// getters start with "set" and don't have an return parameter:
			if (method.getName().startsWith(prefix) && method.getParameterTypes().length == 1 && method.getReturnType().getName().equals("void")) {
				attribName2setterMethod.put(method.getName().replaceFirst(prefix, ""), method);
			}

		}

		return attribName2setterMethod;

	}

}
