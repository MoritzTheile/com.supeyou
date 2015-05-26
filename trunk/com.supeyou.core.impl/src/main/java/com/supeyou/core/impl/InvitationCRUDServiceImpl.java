package com.supeyou.core.impl;

import com.supeyou.core.iface.InvitationCRUDService;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.InvitationFetchQuery;
import com.supeyou.core.impl.entity.InvitationEntity;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;

public class InvitationCRUDServiceImpl extends AbstrCRUDServiceImpl<InvitationDTO, InvitationEntity, InvitationFetchQuery> implements InvitationCRUDService {

	// Singleton

	private static InvitationCRUDServiceImpl service;

	private InvitationCRUDServiceImpl() {
		super(InvitationDTO.class, InvitationEntity.class);
	}

	public static InvitationCRUDServiceImpl i() {
		if (service == null) {
			service = new InvitationCRUDServiceImpl();
		}
		return service;
	}

}
