package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.dto.AbstrAssoDTO;

public class Hero2SupporterDTO extends AbstrAssoDTO<HeroDTO, SupporterDTO, Hero2SupporterIDType> {

	private static final long serialVersionUID = 536835689L;

	public Hero2SupporterDTO() {
	}

	public Hero2SupporterDTO(Hero2SupporterIDType id) {
		setId(id);
	}

	@Override
	public Hero2SupporterIDType getId() {
		return super.getId();
	}

	@Override
	public void setId(Hero2SupporterIDType id) {
		super.setId(id);
	}

	public Hero2SupporterDTO(String id) {
		setId(new Hero2SupporterIDType(id));
	}

	@Override
	public HeroDTO getDtoA() {
		return super.getDtoA();
	}

	@Override
	public void setDtoA(HeroDTO dtoA) {
		super.setDtoA(dtoA);
	}

	@Override
	public SupporterDTO getDtoB() {
		return super.getDtoB();
	}

	@Override
	public void setDtoB(SupporterDTO dtoB) {
		super.setDtoB(dtoB);
	}
}
