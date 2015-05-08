package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.dto.AbstrAssoDTO;

public class Acceptance2PayerDTO extends AbstrAssoDTO<AcceptanceDTO, PayerDTO, Acceptance2PayerIDType> {

	private static final long serialVersionUID = 2424730969L;

	public Acceptance2PayerDTO() {

	}

	public Acceptance2PayerDTO(Acceptance2PayerIDType id) {
		setId(id);
	}

	public Acceptance2PayerDTO(String id) {
		setId(new Acceptance2PayerIDType(id));
	}

	@Override
	public void setId(Acceptance2PayerIDType id) {
		super.setId(id);
	}

	@Override
	public Acceptance2PayerIDType getId() {
		return super.getId();
	}

	@Override
	public void setDtoA(AcceptanceDTO dtoA) {
		super.setDtoA(dtoA);
	}

	@Override
	public AcceptanceDTO getDtoA() {
		return super.getDtoA();
	}

	@Override
	public void setDtoB(PayerDTO dtoB) {
		super.setDtoB(dtoB);
	}

	@Override
	public PayerDTO getDtoB() {
		return super.getDtoB();
	}

}
