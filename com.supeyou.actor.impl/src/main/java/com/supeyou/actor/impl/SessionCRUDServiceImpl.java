package com.supeyou.actor.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.supeyou.actor.iface.SessionCRUDService;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.iface.dto.SessionFetchQuery;
import com.supeyou.actor.impl.entity.SessionEntity;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.FetchQuery.SORTDIRECTION;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.dto.DTOFetchList;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.entity.UserEntity;

public class SessionCRUDServiceImpl extends AbstrCRUDServiceImpl<SessionDTO, SessionEntity, SessionFetchQuery> implements SessionCRUDService {

	private Logger log = Logger.getLogger(SessionCRUDServiceImpl.class.getName());

	private static final String OPERAND = "AND";

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, SessionFetchQuery query) {

		String whereClause = "";

		if (query.getHttpSessionId() != null && !query.getHttpSessionId().isEmpty()) {

			whereClause += OPERAND + " " + "httpSessionId" + " = '" + query.getHttpSessionId() + "' ";

		}
		if (query.getBrowserMark() != null && !query.getBrowserMark().isEmpty()) {

			whereClause += OPERAND + " " + "browserMark" + " = '" + query.getBrowserMark() + "' ";

		}

		if (whereClause.startsWith(OPERAND)) {
			whereClause = whereClause.substring(OPERAND.length(), whereClause.length());
		}

		if (!"".equals(whereClause)) {
			whereClause = " WHERE " + whereClause;
		}

		return whereClause;
	}

	/**
	 * Convenience:
	 */
	@Override
	public SessionDTO getBySessionId(UserDTO actor, String sessionID) {

		try {

			SessionFetchQuery dtoQuery = new SessionFetchQuery();

			dtoQuery.setSessionId(sessionID);

			DTOFetchList<SessionDTO> sessionDTOs = null;
			sessionDTOs = SessionCRUDServiceImpl.i().fetchList(null, new Page(), dtoQuery);
			if (sessionDTOs.size() != 1) {

				log.log(Level.SEVERE, "There are " + sessionDTOs.size() + " sessions found but there should exactly be one. ");

				return null;
			}

			return sessionDTOs.iterator().next();

		} catch (CRUDException e) {

			log.log(Level.SEVERE, "Problems on fetching session by id", e);

		}

		return null;

	}

	@Override
	public SessionDTO getNewestSessionOnBrowserButNotCurrent(UserDTO actor, String browserMark, SessionDTO currentSessionDTO) {

		try {

			SessionFetchQuery dtoQuery = new SessionFetchQuery();

			dtoQuery.setBrowserMark(browserMark);

			dtoQuery.setSortDirection(SORTDIRECTION.DESC);

			DTOFetchList<SessionDTO> sessionDTOs = SessionCRUDServiceImpl.i().fetchList(actor, new Page(), dtoQuery);
			if (sessionDTOs.size() == 0) {

				log.log(Level.SEVERE, "There are no sessions found for browserMark '" + browserMark + "'.");

				return null;
			}

			for (SessionDTO sessionDTO : sessionDTOs) {

				// don't use current Session
				if (!sessionDTO.equals(currentSessionDTO)) {

					return sessionDTO;

				}

			}

			return null;

		} catch (CRUDException e) {

			log.log(Level.SEVERE, "Problems on fetching session by id", e);

		}

		return null;

	}

	// Singleton

	private static SessionCRUDServiceImpl service;

	private SessionCRUDServiceImpl() {
		super(SessionDTO.class, SessionEntity.class);
	}

	public static SessionCRUDService i() {
		if (service == null) {
			service = new SessionCRUDServiceImpl();
		}
		return service;
	}

}
