package com.hotelorga.core.impl;

import javax.persistence.EntityManager;

import com.hotelorga.core.iface.RoomCRUDService;
import com.hotelorga.core.iface.dto.RoomDTO;
import com.hotelorga.core.iface.dto.RoomFetchQuery;
import com.hotelorga.core.impl.entity.RoomEntity;
import com.hotelorga.foundation.impl.AbstrCRUDServiceImpl;
import com.hotelorga.foundation.impl.entity.UserEntity;

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
