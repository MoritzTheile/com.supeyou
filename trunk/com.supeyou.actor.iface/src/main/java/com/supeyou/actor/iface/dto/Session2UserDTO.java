package com.supeyou.actor.iface.dto;

import com.supeyou.crudie.iface.dto.AbstrAssoDTO;
import com.supeyou.crudie.iface.dto.UserDTO;

public class Session2UserDTO extends AbstrAssoDTO<SessionDTO, UserDTO, Session2UserIDType> {

	private static final long serialVersionUID = 536835689L;

	public Session2UserDTO() {
	}

	public Session2UserDTO(Session2UserIDType id) {
		setId(id);
	}

	@Override
	public Session2UserIDType getId() {
		return super.getId();
	}

	@Override
	public void setId(Session2UserIDType id) {
		super.setId(id);
	}

	public Session2UserDTO(String id) {
		setId(new Session2UserIDType(id));
	}

	@Override
	public SessionDTO getDtoA() {
		return super.getDtoA();
	}

	@Override
	public void setDtoA(SessionDTO dtoA) {
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
