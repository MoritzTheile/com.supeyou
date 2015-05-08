package com.hotelorga.foundation.impl;

import com.hotelorga.foundation.iface.GroupCRUDService;
import com.hotelorga.foundation.iface.dto.GroupDTO;
import com.hotelorga.foundation.iface.dto.GroupFetchQuery;
import com.hotelorga.foundation.impl.entity.GroupEntity;

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
