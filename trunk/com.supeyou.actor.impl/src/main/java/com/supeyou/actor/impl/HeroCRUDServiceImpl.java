package com.supeyou.actor.impl;

import javax.persistence.EntityManager;

import com.supeyou.actor.iface.SessionCRUDService;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.iface.dto.SessionFetchQuery;
import com.supeyou.actor.impl.entity.SessionEntity;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.entity.UserEntity;

public class HeroCRUDServiceImpl extends AbstrCRUDServiceImpl<SessionDTO, SessionEntity, SessionFetchQuery> implements SessionCRUDService {

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, SessionFetchQuery query) {

		String whereClause = "";

		if (query.getSearchString() != null && !query.getSearchString().isEmpty()) {

			whereClause += " " + "lower(name)" + " like '%" + query.getSearchString().toLowerCase() + "%' ";

		}

		if (!"".equals(whereClause)) {
			whereClause = " WHERE " + whereClause;
		}

		return whereClause;
	}

	// Singleton

	private static HeroCRUDServiceImpl service;

	private HeroCRUDServiceImpl() {
		super(SessionDTO.class, SessionEntity.class);
	}

	public static HeroCRUDServiceImpl i() {
		if (service == null) {
			service = new HeroCRUDServiceImpl();
		}
		return service;
	}

}
