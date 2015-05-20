package com.supeyou.actor.impl;

import com.supeyou.actor.iface.dto.ActingUser2SessionDTO;
import com.supeyou.actor.iface.dto.ActingUser2SessionFetchQuery;
import com.supeyou.actor.iface.dto.ActingUser2SessionIDType;
import com.supeyou.actor.iface.dto.ActingUserDTO;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.impl.entity.ActingUser2SessionEntity;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;

public class ActingUser2SessionCRUDServiceImpl extends AbstrCRUDServiceImpl<ActingUser2SessionDTO, ActingUser2SessionEntity, ActingUser2SessionFetchQuery> implements CRUDAssoService<ActingUserDTO, SessionDTO, ActingUser2SessionIDType, ActingUser2SessionDTO, ActingUser2SessionFetchQuery> {

	// Singleton

	private static ActingUser2SessionCRUDServiceImpl service;

	private ActingUser2SessionCRUDServiceImpl() {
		super(ActingUser2SessionDTO.class, ActingUser2SessionEntity.class);

	}

	public static ActingUser2SessionCRUDServiceImpl i() {
		if (service == null) {
			service = new ActingUser2SessionCRUDServiceImpl();
		}
		return service;
	}

}
