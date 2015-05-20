package com.supeyou.auth.web.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.supeyou.crudie.web.server.SessionStore;

/**
 * This filter ensures that there is always an actor assigned to session.
 * 
 * @author MoritzTheile
 * 
 */
public class ActorFilter implements Filter {

	private static Logger log = Logger.getLogger(ActorFilter.class.getName());

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

			if (SessionStore.getActor(httpServletRequest.getSession()) != null) {

				// nothing to do, session has actor already

				return;

			}

			// trying to find actor by BrowserMark

			// TODO

			// creating anonymous actor

			// UserDTO anonymousActor = new UserDTO();
			// anonymousActor.set
			// TODO

		} finally {

			filterChain.doFilter(servletRequest, servletResponse);

		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// nothing
	}

	public static void main(String[] args) {
		System.out.println(getRandom());
	}

	private static String getRandom() {
		return ("" + Math.random()).replaceAll("\\.", "");
	}
}
