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
import javax.servlet.http.HttpServletRequest;

import com.supeyou.actor.iface.dto.Session2UserDTO;
import com.supeyou.actor.iface.dto.Session2UserFetchQuery;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.impl.Session2UserCRUDServiceImpl;
import com.supeyou.actor.impl.SessionCRUDServiceImpl;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.DTOFetchList;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.UserCRUDServiceImpl;
import com.supeyou.crudie.web.server.SessionStore;

/**
 * This filter ensures that there is always an actor assigned to session. Also it tries to find a user based on BrowserMark-Cookie.
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

			SessionDTO sessionDTO = SessionCRUDServiceImpl.i().getBySessionId(null, httpServletRequest.getSession().getId());

			if (sessionDTO == null) {

				log.log(Level.SEVERE, "SessionDTO not found. Session won't be attached to user.");

				return;

			}

			UserDTO actor = null;

			{// trying to find actor by BrowserMark

				SessionDTO newestSessionOnBrowser = SessionCRUDServiceImpl.i().getNewestSessionOnBrowserButNotCurrent(null, BrowserMarkingFilter.getBrowserMark(httpServletRequest), sessionDTO);
				if (newestSessionOnBrowser != null) {

					Session2UserFetchQuery session2UserFetchQuery = new Session2UserFetchQuery();
					session2UserFetchQuery.setIdA(newestSessionOnBrowser.getId());
					DTOFetchList<Session2UserDTO> session2UserDTOs = Session2UserCRUDServiceImpl.i().fetchList(null, new Page(), session2UserFetchQuery);

					if (session2UserDTOs.size() == 0) {

						log.log(Level.WARNING, "Session id=" + httpServletRequest.getSession().getId() + " is not assigned to an user");

					} else {

						actor = session2UserDTOs.iterator().next().getDtoB();

						if (session2UserDTOs.size() > 1) {
							log.log(Level.WARNING, "Session id=" + httpServletRequest.getSession().getId() + " is assigned to multiple users (" + session2UserDTOs.size() + ")");
						}

					}
				}
			}

			if (actor == null) {// actor not found, creating anonymous actor

				actor = new UserDTO();
				actor.setLoginId(new SingleLineString256Type("anonymousActor_" + System.currentTimeMillis()));
				actor = UserCRUDServiceImpl.i().updadd(null, actor);
				SessionStore.setActor(httpServletRequest.getSession(), actor);

			}

			{// attaching session to actor

				Session2UserDTO session2UserDTO = new Session2UserDTO();
				session2UserDTO.setDtoA(sessionDTO);
				session2UserDTO.setDtoB(actor);
				Session2UserCRUDServiceImpl.i().updadd(actor, session2UserDTO);

			}

		} catch (Exception e) {

			log.log(Level.SEVERE, "Problems on assigning actor to http session.", e);

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
