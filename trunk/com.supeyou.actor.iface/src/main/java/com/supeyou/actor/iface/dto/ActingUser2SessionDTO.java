package com.supeyou.actor.iface.dto;

import com.supeyou.crudie.iface.dto.AbstrAssoDTO;

public class ActingUser2SessionDTO extends AbstrAssoDTO<ActingUserDTO, SessionDTO, ActingUser2SessionIDType> {

	private static final long serialVersionUID = 536835689L;

	public ActingUser2SessionDTO() {
	}

	public ActingUser2SessionDTO(ActingUser2SessionIDType id) {
		setId(id);
	}

	@Override
	public ActingUser2SessionIDType getId() {
		return super.getId();
	}

	@Override
	public void setId(ActingUser2SessionIDType id) {
		super.setId(id);
	}

	public ActingUser2SessionDTO(String id) {
		setId(new ActingUser2SessionIDType(id));
	}

	@Override
	public ActingUserDTO getDtoA() {
		return super.getDtoA();
	}

	@Override
	public void setDtoA(ActingUserDTO dtoA) {
		super.setDtoA(dtoA);
	}

	@Override
	public SessionDTO getDtoB() {
		return super.getDtoB();
	}

	@Override
	public void setDtoB(SessionDTO dtoB) {
		super.setDtoB(dtoB);
	}
}
