package com.supeyou.actor.iface.dto;

import com.supeyou.crudie.iface.dto.AbstrAssoDTO;

public class Session2EventDTO extends AbstrAssoDTO<SessionDTO, EventDTO, Session2EventIDType> {

	private static final long serialVersionUID = 536835689L;

	public Session2EventDTO() {
	}

	public Session2EventDTO(Session2EventIDType id) {
		setId(id);
	}

	@Override
	public Session2EventIDType getId() {
		return super.getId();
	}

	@Override
	public void setId(Session2EventIDType id) {
		super.setId(id);
	}

	public Session2EventDTO(String id) {
		setId(new Session2EventIDType(id));
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
	public EventDTO getDtoB() {
		return super.getDtoB();
	}

	@Override
	public void setDtoB(EventDTO dtoB) {
		super.setDtoB(dtoB);
	}
}
