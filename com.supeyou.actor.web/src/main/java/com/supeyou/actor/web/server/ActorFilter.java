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
import com.supeyou.crudie.iface.datatype.CRUDException;
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

			HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

			SessionDTO sessionDTO = SessionCRUDServiceImpl.i().getBySessionId(null, httpServletRequest.getSession().getId());

			if (sessionDTO == null) {

				throw new Exception("SessionDTO not found. Session won't be attached to user.");

			}

			UserDTO actor = SessionStore.getActor(httpServletRequest.getSession());

			if (actor == null) {// session doesn't have actor so getting one somehow ...

				actor = getActorByCookie(httpServletRequest, sessionDTO, actor);

				if (actor == null) {// actor not found, creating anonymous actor

					actor = createNewActor();

				}

				// by now we can be sure there is an actor

				SessionStore.setActor(httpServletRequest.getSession(), actor);

			}
			{// if not yet connected, attaching session to actor
				Session2UserFetchQuery session2UserFetchQuery = new Session2UserFetchQuery();
				session2UserFetchQuery.setIdA(sessionDTO.getId());
				session2UserFetchQuery.setIdB(actor.getId());
				if (Session2UserCRUDServiceImpl.i().fetchList(actor, new Page(), session2UserFetchQuery).size() == 0) {

					Session2UserDTO session2UserDTO = new Session2UserDTO();
					session2UserDTO.setDtoA(sessionDTO);
					session2UserDTO.setDtoB(actor);
					Session2UserCRUDServiceImpl.i().updadd(actor, session2UserDTO);

				}
			}
		} catch (Exception e) {

			log.log(Level.SEVERE, "Problems on assigning actor to http session.", e);

		} finally {

			filterChain.doFilter(servletRequest, servletResponse);

		}

	}

	private UserDTO createNewActor() throws CRUDException {
		UserDTO actor;
		actor = new UserDTO();
		actor.setLoginId(new SingleLineString256Type("anonymousActor_" + System.currentTimeMillis()));
		actor = UserCRUDServiceImpl.i().updadd(null, actor);
		return actor;
	}

	private UserDTO getActorByCookie(HttpServletRequest httpServletRequest, SessionDTO sessionDTO, UserDTO actor) throws CRUDException {
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
		return actor;
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
