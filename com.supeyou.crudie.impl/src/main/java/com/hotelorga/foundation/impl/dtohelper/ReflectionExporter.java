package com.hotelorga.foundation.impl.dtohelper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hotelorga.foundation.iface.dto.AbstrDTO;

public class ReflectionExporter {

	private static Logger log = Logger.getLogger(ReflectionExporter.class.getName());

	public static Map<String, String> fillStringMap(Object dto) {

		Map<String, String> attribName2StringValue = new HashMap<>();

		try {

			Map<String, Method> attribName2getterMethod = findGetters(dto.getClass());

			for (String attribName : attribName2getterMethod.keySet()) {

				Method getterMethod = attribName2getterMethod.get(attribName);
				Object attribValue = getterMethod.invoke(dto, new Object[0]);
				if (attribValue instanceof AbstrDTO<?>) {
					attribName2StringValue.put(attribName, ((AbstrDTO<?>) attribValue).getId() + "");
				} else {
					attribName2StringValue.put(attribName, attribValue + "");
				}

			}

		} catch (Exception e) {

			log.log(Level.INFO, "Problems on reading attribute reflecively", e);

		}
		// not interested in class
		attribName2StringValue.remove("Class");
		return attribName2StringValue;

	}

	public static String dtos2csv(List<?> dtoList) {
		String csvString = "";

		List<String> headerrow = null;

		for (Object dto : dtoList) {

			Map<String, String> attribName2attribValue = ReflectionExporter.fillStringMap(dto);
			if (headerrow == null) {
				headerrow = new ArrayList<>(attribName2attribValue.keySet());
				for (String header : headerrow) {
					csvString += header + ";";
				}
				csvString += "\n";
				// continue;
			}

			for (String header : headerrow) {
				String value = attribName2attribValue.get(header);
				value = value.replaceAll(";", ",");
				value = value.replaceAll("\"", "'");
				csvString += value + ";";
			}

			csvString += "\n";

		}

		return csvString;
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

}
