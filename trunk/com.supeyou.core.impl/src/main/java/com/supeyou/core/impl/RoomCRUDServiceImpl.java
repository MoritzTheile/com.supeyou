package com.supeyou.core.impl;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.RoomCRUDService;
import com.supeyou.core.iface.dto.RoomDTO;
import com.supeyou.core.iface.dto.RoomFetchQuery;
import com.supeyou.core.impl.entity.RoomEntity;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.entity.UserEntity;

public class RoomCRUDServiceImpl extends AbstrCRUDServiceImpl<RoomDTO, RoomEntity, RoomFetchQuery> implements RoomCRUDService {

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, RoomFetchQuery query) {

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

	private static RoomCRUDServiceImpl service;

	private RoomCRUDServiceImpl() {
		super(RoomDTO.class, RoomEntity.class);
	}

	public static RoomCRUDServiceImpl i() {
		if (service == null) {
			service = new RoomCRUDServiceImpl();
		}
		return service;
	}

}
