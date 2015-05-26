package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.dto.AbstrAssoDTO;
import com.supeyou.crudie.iface.dto.UserDTO;

public class Invitation2UserDTO extends AbstrAssoDTO<InvitationDTO, UserDTO, Invitation2UserIDType> {

	private static final long serialVersionUID = 536835689L;

	public Invitation2UserDTO() {
	}

	public Invitation2UserDTO(Invitation2UserIDType id) {
		setId(id);
	}

	@Override
	public Invitation2UserIDType getId() {
		return super.getId();
	}

	@Override
	public void setId(Invitation2UserIDType id) {
		super.setId(id);
	}

	public Invitation2UserDTO(String id) {
		setId(new Invitation2UserIDType(id));
	}

	@Override
	public InvitationDTO getDtoA() {
		return super.getDtoA();
	}

	@Override
	public void setDtoA(InvitationDTO dtoA) {
		super.setDtoA(dtoA);
	}

	@Override
	public UserDTO getDtoB() {
		return super.getDtoB();
	}

	@Override
	public void setDtoB(UserDTO dtoB) {
		super.setDtoB(dtoB);
	}
}
