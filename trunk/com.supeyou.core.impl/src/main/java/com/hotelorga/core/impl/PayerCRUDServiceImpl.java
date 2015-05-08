package com.hotelorga.core.impl;

import javax.persistence.EntityManager;

import com.hotelorga.core.iface.PayerCRUDService;
import com.hotelorga.core.iface.dto.PayerDTO;
import com.hotelorga.core.iface.dto.PayerFetchQuery;
import com.hotelorga.core.impl.entity.PayerEntity;
import com.hotelorga.foundation.impl.AbstrCRUDServiceImpl;
import com.hotelorga.foundation.impl.entity.UserEntity;

public class PayerCRUDServiceImpl extends AbstrCRUDServiceImpl<PayerDTO, PayerEntity, PayerFetchQuery> implements PayerCRUDService {

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, PayerFetchQuery query) {

		String whereClause = "";

		if (query.getSearchString() != null && !query.getSearchString().isEmpty()) {

			whereClause += " " + "lower(name)" + " like '%" + query.getSearchString().toLowerCase() + "%' OR ";
			whereClause += " " + "lower(comment)" + " like '%" + query.getSearchString().toLowerCase() + "%' OR ";
			whereClause += " " + "lower(confideTo)" + " like '%" + query.getSearchString().toLowerCase() + "%' ";

		}

		if (!"".equals(whereClause)) {
			whereClause = " WHERE " + whereClause;
		}

		return whereClause;
	}

	// Singleton

	private static PayerCRUDServiceImpl service;

	private PayerCRUDServiceImpl() {
		super(PayerDTO.class, PayerEntity.class);
	}

	public static PayerCRUDServiceImpl i() {
		if (service == null) {
			service = new PayerCRUDServiceImpl();
		}
		return service;
	}

}
