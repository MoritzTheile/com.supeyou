package com.supeyou.core.impl;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.AcceptanceCRUDService;
import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.AcceptanceFetchQuery;
import com.supeyou.core.impl.entity.AcceptanceEntity;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.entity.UserEntity;

public class AcceptanceCRUDServiceImpl extends AbstrCRUDServiceImpl<AcceptanceDTO, AcceptanceEntity, AcceptanceFetchQuery> implements AcceptanceCRUDService {

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, AcceptanceFetchQuery query) {

		String whereClause = "";

		if (query.getSearchString() != null && !query.getSearchString().isEmpty()) {

			whereClause += " " + "lower(comment)" + " like '%" + query.getSearchString().toLowerCase() + "%' ";

		}

		if (!"".equals(whereClause)) {
			whereClause = " WHERE " + whereClause;
		}

		return whereClause;
	}

	@Override
	protected void postprocessEntity2DTO(EntityManager em, AcceptanceEntity entity, AcceptanceDTO dto) throws Exception {

		addTmpInfosToAcceptanceDTO(entity, dto);

	}

	public static void addTmpInfosToAcceptanceDTO(AcceptanceEntity entity, AcceptanceDTO dto) {
		if (entity.getAcceptance2payerCollection().size() == 1) {

			dto.setTmpName(entity.getAcceptance2payerCollection().iterator().next().getB().getName());

		}
	}

	// Singleton

	private static AcceptanceCRUDServiceImpl service;

	private AcceptanceCRUDServiceImpl() {
		super(AcceptanceDTO.class, AcceptanceEntity.class);
	}

	public static AcceptanceCRUDServiceImpl i() {
		if (service == null) {
			service = new AcceptanceCRUDServiceImpl();
		}
		return service;
	}

}
