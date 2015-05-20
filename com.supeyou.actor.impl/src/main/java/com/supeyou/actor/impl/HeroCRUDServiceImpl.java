package com.supeyou.actor.impl;

import javax.persistence.EntityManager;

import com.supeyou.actor.iface.HeroCRUDService;
import com.supeyou.actor.iface.dto.HeroDTO;
import com.supeyou.actor.iface.dto.HeroFetchQuery;
import com.supeyou.actor.impl.entity.HeroEntity;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.entity.UserEntity;

public class HeroCRUDServiceImpl extends AbstrCRUDServiceImpl<HeroDTO, HeroEntity, HeroFetchQuery> implements HeroCRUDService {

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, HeroFetchQuery query) {

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
		super(HeroDTO.class, HeroEntity.class);
	}

	public static HeroCRUDServiceImpl i() {
		if (service == null) {
			service = new HeroCRUDServiceImpl();
		}
		return service;
	}

}
