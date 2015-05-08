package com.supeyou.crudie.impl;

import com.supeyou.crudie.iface.GroupCRUDService;
import com.supeyou.crudie.iface.dto.GroupDTO;
import com.supeyou.crudie.iface.dto.GroupFetchQuery;
import com.supeyou.crudie.impl.entity.GroupEntity;

public class GroupCRUDServiceImpl extends AbstrCRUDServiceImpl<GroupDTO, GroupEntity, GroupFetchQuery> implements GroupCRUDService {

	// Singleton

	private static GroupCRUDServiceImpl service;

	private GroupCRUDServiceImpl() {
		super(GroupDTO.class, GroupEntity.class);
	}

	public static GroupCRUDServiceImpl i() {
		if (service == null) {
			service = new GroupCRUDServiceImpl();
		}
		return service;
	}

}
