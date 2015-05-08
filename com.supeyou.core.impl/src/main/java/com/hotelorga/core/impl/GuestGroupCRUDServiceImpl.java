package com.hotelorga.core.impl;

import javax.persistence.EntityManager;

import com.hotelorga.core.iface.GuestGroupCRUDService;
import com.hotelorga.core.iface.dto.GuestGroupDTO;
import com.hotelorga.core.iface.dto.GuestGroupFetchQuery;
import com.hotelorga.core.impl.entity.GuestGroupEntity;
import com.hotelorga.foundation.impl.AbstrCRUDServiceImpl;
import com.hotelorga.foundation.impl.entity.UserEntity;

public class GuestGroupCRUDServiceImpl extends AbstrCRUDServiceImpl<GuestGroupDTO, GuestGroupEntity, GuestGroupFetchQuery> implements GuestGroupCRUDService {

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, GuestGroupFetchQuery query) {

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

	private static GuestGroupCRUDServiceImpl service;

	private GuestGroupCRUDServiceImpl() {
		super(GuestGroupDTO.class, GuestGroupEntity.class);
	}

	public static GuestGroupCRUDServiceImpl i() {
		if (service == null) {
			service = new GuestGroupCRUDServiceImpl();
		}
		return service;
	}

}
