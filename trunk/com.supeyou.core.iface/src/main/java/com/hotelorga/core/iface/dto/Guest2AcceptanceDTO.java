package com.hotelorga.core.iface.dto;

import com.hotelorga.foundation.iface.dto.AbstrAssoDTO;

public class Guest2AcceptanceDTO extends AbstrAssoDTO<GuestDTO, AcceptanceDTO, Guest2AcceptanceIDType> {

	private static final long serialVersionUID = 72858237389L;

	public Guest2AcceptanceDTO() {

	}

	public Guest2AcceptanceDTO(Guest2AcceptanceIDType id) {
		setId(id);
	}

	public Guest2AcceptanceDTO(String id) {
		setId(new Guest2AcceptanceIDType(id));
	}

	@Override
	public void setId(Guest2AcceptanceIDType id) {
		super.setId(id);
	}

	@Override
	public Guest2AcceptanceIDType getId() {
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
	public void setDtoB(AcceptanceDTO dtoB) {
		super.setDtoB(dtoB);
	}

	@Override
	public AcceptanceDTO getDtoB() {
		return super.getDtoB();
	}

}
