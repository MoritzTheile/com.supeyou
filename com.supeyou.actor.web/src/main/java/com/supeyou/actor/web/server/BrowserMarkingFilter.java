package com.supeyou.actor.web.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This filter ensures a cookie for recognizing browser.
 * 
 * 
 * @author MoritzTheile
 * 
 */
public class BrowserMarkingFilter implements Filter {

	private static Logger log = Logger.getLogger(BrowserMarkingFilter.class.getName());

	@Override
	public void destroy() {
		// nothing

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		try {

			if (!(servletRequest instanceof HttpServletRequest)) {
				log.log(Level.WARNING, "servletRequest is not instance of HttpServletRequest");
				return;
			}

			HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

			if (!(servletResponse instanceof HttpServletResponse)) {
				log.log(Level.WARNING, "servletResponse is not instance of HttpServletResponse");
				return;
			}

			HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

			if (getBrowserMark(httpServletRequest) == null) {

				setBrowserMarkCookie(httpServletResponse);

			}

		} finally {

			filterChain.doFilter(servletRequest, servletResponse);

		}

	}

	private void setBrowserMarkCookie(HttpServletResponse httpServletResponse) {

		Cookie cookie = new Cookie(BROWSER_MARK_COOKIE_KEY, BROWSER_MARK_COOKIE_VALUE_PREFIX + getRandom());

		cookie.setMaxAge(3 * 365 * 24 * 3600); // 3 Years

		httpServletResponse.addCookie(cookie);

	}

	private static String BROWSER_MARK_COOKIE_VALUE_PREFIX = "BROWSER_MARK_";
	private static String BROWSER_MARK_COOKIE_KEY = "BROWSER_MARK_COOKIE_KEY";

	public static String getBrowserMark(HttpServletRequest httpServletRequest) {

		Cookie cookie = getCookie(httpServletRequest, BROWSER_MARK_COOKIE_KEY);

		if (cookie != null) {
			return cookie.getValue();
		}

		return null;
	}

	private static Cookie getCookie(HttpServletRequest httpServletRequest, String key) {

		for (Cookie cookie : httpServletRequest.getCookies()) {

			if (cookie.getName().equals(key)) {

				return cookie;

			}

		}

		return null;

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// nothing
	}

	private static String getRandom() {
		return ("" + Math.random()).replaceAll("\\.", "");
	}
}
