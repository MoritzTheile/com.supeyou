package com.supeyou.actor.web.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.impl.SessionCRUDServiceImpl;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;

public class SessionListener implements HttpSessionListener {

	private Logger log = Logger.getLogger(SessionListener.class.getName());

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {

		log.info("HttpSession created");

		try {

			SessionDTO sessionDTO = new SessionDTO();
			sessionDTO.setHttpSessionId(new SingleLineString256Type(arg0.getSession().getId()));
			SessionCRUDServiceImpl.i().updadd(null, sessionDTO);

		} catch (CRUDException e) {

			log.log(Level.SEVERE, "Problems creating session in DB", e);

		}

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {

		log.info("HttpSession destroyed");

	}

}
