package com.supeyou.crudie.web.client.resources;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.Window;

public class URLHelper {

	public static void reloadWithLocale(String locale) {

		String queryString = getCurrentQueryString(singleQueryAsMap("locale", locale));

		Window.Location.assign(getCurrentURL() + queryString);
	}

	private static Map<String, String> singleQueryAsMap(String key, String value) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(key, value);
		return map;
	}

	public static String getCurrentURL() {
		String urlString = Window.Location.getProtocol() + "//" + Window.Location.getHost() + Window.Location.getPath();
		return urlString;
	}

	/**
	 * 
	 * Returns a queryString of the form '?key1=value1&key2=value2'.
	 * 
	 * If there are additionalQueries as Map 'key2=value2a;key3=value3' the result will be '?key1=value1&key2=value2a&key3=value3'
	 * 
	 * Returns an empty String if no queries are given.
	 * 
	 */
	public static String getCurrentQueryString(Map<String, String> additionalQueries) {

		Map<String, String> queryMap = queryStringToMap(Window.Location.getQueryString());

		if (additionalQueries != null) {
			queryMap.putAll(additionalQueries);
		}

		return queryMapToString(queryMap);

	}

	private static Map<String, String> queryStringToMap(String queryString) {

		// removing '?'
		if (queryString.startsWith("?")) {

			queryString = queryString.substring(1, queryString.length());

		}

		String[] params = queryString.split("&");

		Map<String, String> queryMap = new HashMap<String, String>();

		for (String param : params) {

			String[] keyValueArray = param.split("=");

			if (keyValueArray.length > 0) {

				String key = keyValueArray[0];
				if (key == null || key.isEmpty()) {
					continue;
				}
				String value = "";
				if (keyValueArray.length > 1) {
					value = keyValueArray[1];
				}
				queryMap.put(key, value);

			}
		}

		return queryMap;
	}

	private static String queryMapToString(Map<String, String> queryMap) {

		String query = "";
		for (String key : queryMap.keySet()) {
			String value = queryMap.get(key);
			query += "&" + key + "=" + value;
		}
		return "?" + query;

	}

}