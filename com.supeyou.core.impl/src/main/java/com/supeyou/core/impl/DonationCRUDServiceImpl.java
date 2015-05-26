package com.supeyou.core.impl;

import com.supeyou.core.iface.DonationCRUDService;
import com.supeyou.core.iface.dto.DonationDTO;
import com.supeyou.core.iface.dto.DonationFetchQuery;
import com.supeyou.core.impl.entity.DonationEntity;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;

public class DonationCRUDServiceImpl extends AbstrCRUDServiceImpl<DonationDTO, DonationEntity, DonationFetchQuery> implements DonationCRUDService {

	// Singleton

	private static DonationCRUDServiceImpl service;

	private DonationCRUDServiceImpl() {
		super(DonationDTO.class, DonationEntity.class);
	}

	public static DonationCRUDServiceImpl i() {
		if (service == null) {
			service = new DonationCRUDServiceImpl();
		}
		return service;
	}

}
