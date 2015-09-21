package com.supeyou.actor.impl;

import javax.persistence.EntityManager;

import com.supeyou.actor.iface.EventCRUDService;
import com.supeyou.actor.iface.dto.EventDTO;
import com.supeyou.actor.iface.dto.EventFetchQuery;
import com.supeyou.actor.impl.entity.EventEntity;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.entity.UserEntity;

public class EventCRUDServiceImpl extends AbstrCRUDServiceImpl<EventDTO, EventEntity, EventFetchQuery> implements EventCRUDService {

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, EventFetchQuery query) {

		String whereClause = "";

		return whereClause;
	}

	// Singleton

	private static EventCRUDServiceImpl service;

	private EventCRUDServiceImpl() {
		super(EventDTO.class, EventEntity.class);
	}

	public static EventCRUDService i() {
		if (service == null) {
			service = new EventCRUDServiceImpl();
		}
		return service;
	}

}
