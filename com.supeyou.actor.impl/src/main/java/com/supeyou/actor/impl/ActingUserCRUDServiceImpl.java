package com.supeyou.actor.impl;

import com.supeyou.actor.iface.ActingUserCRUDService;
import com.supeyou.actor.iface.dto.ActingUserDTO;
import com.supeyou.actor.iface.dto.ActingUserFetchQuery;
import com.supeyou.actor.impl.entity.ActingUserEntity;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;

public class ActingUserCRUDServiceImpl extends AbstrCRUDServiceImpl<ActingUserDTO, ActingUserEntity, ActingUserFetchQuery> implements ActingUserCRUDService {

	// Singleton

	private static ActingUserCRUDServiceImpl service;

	private ActingUserCRUDServiceImpl() {
		super(ActingUserDTO.class, ActingUserEntity.class);
	}

	public static ActingUserCRUDServiceImpl i() {
		if (service == null) {
			service = new ActingUserCRUDServiceImpl();
		}
		return service;
	}

}
