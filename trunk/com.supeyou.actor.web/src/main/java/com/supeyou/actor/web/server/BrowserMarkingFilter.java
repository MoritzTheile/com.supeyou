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

import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.impl.SessionCRUDServiceImpl;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;

/**
 * This filter sets a browser mark cookie for recognizing browsers.
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

			HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

			if (!(servletResponse instanceof HttpServletResponse)) {
				log.log(Level.WARNING, "servletResponse is not instance of HttpServletResponse");
				return;
			}

			HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

			if (getBrowserMark(httpServletRequest) == null) {// Browser unknown

				String mark = setBrowserMarkCookie(httpServletResponse);

				saveMarkToSessionEntity(httpServletRequest, mark);

			} else {// Browser known

				saveMarkToSessionEntity(httpServletRequest, getBrowserMark(httpServletRequest));

			}

		} finally {

			filterChain.doFilter(servletRequest, servletResponse);

		}

	}

	private void saveMarkToSessionEntity(HttpServletRequest httpServletRequest, String mark) {

		try {// Updating session entity

			SessionDTO sessionDTO = SessionCRUDServiceImpl.i().getBySessionId(null, httpServletRequest.getSession().getId());
			sessionDTO.setBrowserMark(new SingleLineString256Type(mark));
			SessionCRUDServiceImpl.i().updadd(null, sessionDTO);

		} catch (Exception e) {

			log.log(Level.WARNING, "Problems on saving browser mark to session", e);

		}

	}

	private String setBrowserMarkCookie(HttpServletResponse httpServletResponse) {

		String mark = BROWSER_MARK_COOKIE_VALUE_PREFIX + getRandom();

		Cookie cookie = new Cookie(BROWSER_MARK_COOKIE_KEY, mark);

		cookie.setMaxAge(3 * 365 * 24 * 3600); // 3 Years

		httpServletResponse.addCookie(cookie);

		return mark;

	}

	private static String BROWSER_MARK_COOKIE_VALUE_PREFIX = "SY_BMC_";
	private static String BROWSER_MARK_COOKIE_KEY = "SY_BMCK";

	public static String getBrowserMark(HttpServletRequest httpServletRequest) {

		Cookie cookie = getCookie(httpServletRequest, BROWSER_MARK_COOKIE_KEY);

		if (cookie != null) {
			return cookie.getValue();
		}

		return null;
	}

	private static Cookie getCookie(HttpServletRequest httpServletRequest, String key) {

		if (httpServletRequest.getCookies() != null) {// getCookies can be null!

			for (Cookie cookie : httpServletRequest.getCookies()) {

				if (cookie.getName().equals(key)) {

					return cookie;

				}

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
