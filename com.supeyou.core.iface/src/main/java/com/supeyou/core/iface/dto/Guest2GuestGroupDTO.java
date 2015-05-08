package com.supeyou.core.iface.dto;

import com.supeyou.core.iface.datatype.enums.GROUPROLE;
import com.supeyou.crudie.iface.dto.AbstrAssoDTO;

public class Guest2GuestGroupDTO extends AbstrAssoDTO<GuestDTO, GuestGroupDTO, Guest2GuestGroupIDType> {

	private static final long serialVersionUID = 72936831737L;

	private GROUPROLE groupRole = GROUPROLE.NO_ROLE;
	private Boolean groupLeader;

	public Guest2GuestGroupDTO() {

	}

	public Guest2GuestGroupDTO(Guest2GuestGroupIDType id) {
		setId(id);
	}

	public Guest2GuestGroupDTO(String id) {
		setId(new Guest2GuestGroupIDType(id));
	}

	@Override
	public void setId(Guest2GuestGroupIDType id) {
		super.setId(id);
	}

	@Override
	public Guest2GuestGroupIDType getId() {
		return super.getId();
	}

	@Override
	public void setDtoA(GuestDTO dtoA) {
		super.setDtoA(dtoA);
	}

	@Override
	public GuestDTO getDtoA() {
		return super.getDtoA();
	}

	@Override
	public void setDtoB(GuestGroupDTO dtoB) {
		super.setDtoB(dtoB);
	}

	@Override
	public GuestGroupDTO getDtoB() {
		return super.getDtoB();
	}

	public GROUPROLE getGroupRole() {
		return groupRole;
	}

	public void setGroupRole(GROUPROLE groupRole) {
		this.groupRole = groupRole;
	}

	public Boolean getGroupLeader() {
		return groupLeader;
	}

	public void setGroupLeader(Boolean groupLeader) {
		this.groupLeader = groupLeader;
	}

}
