package com.supeyou.actor.impl;

import com.supeyou.actor.iface.SessionCRUDService;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.iface.dto.SessionFetchQuery;
import com.supeyou.actor.impl.entity.SessionEntity;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;

public class SessionCRUDServiceImpl extends AbstrCRUDServiceImpl<SessionDTO, SessionEntity, SessionFetchQuery> implements SessionCRUDService {

	// Singleton

	private static SessionCRUDServiceImpl service;

	private SessionCRUDServiceImpl() {
		super(SessionDTO.class, SessionEntity.class);
	}

	public static SessionCRUDServiceImpl i() {
		if (service == null) {
			service = new SessionCRUDServiceImpl();
		}
		return service;
	}

}
