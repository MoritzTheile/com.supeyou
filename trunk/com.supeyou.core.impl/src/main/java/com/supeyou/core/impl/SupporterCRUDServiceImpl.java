package com.supeyou.core.impl;

import com.supeyou.core.iface.SupporterCRUDService;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterFetchQuery;
import com.supeyou.core.impl.entity.SupporterEntity;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;

public class SupporterCRUDServiceImpl extends AbstrCRUDServiceImpl<SupporterDTO, SupporterEntity, SupporterFetchQuery> implements SupporterCRUDService {

	// Singleton

	private static SupporterCRUDServiceImpl service;

	private SupporterCRUDServiceImpl() {
		super(SupporterDTO.class, SupporterEntity.class);
	}

	public static SupporterCRUDServiceImpl i() {
		if (service == null) {
			service = new SupporterCRUDServiceImpl();
		}
		return service;
	}

}
