package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.dto.AbstrAssoDTO;

public class Supporter2DonationDTO extends AbstrAssoDTO<SupporterDTO, DonationDTO, Supporter2DonationIDType> {

	private static final long serialVersionUID = 536835689L;

	public Supporter2DonationDTO() {
	}

	public Supporter2DonationDTO(Supporter2DonationIDType id) {
		setId(id);
	}

	@Override
	public Supporter2DonationIDType getId() {
		return super.getId();
	}

	@Override
	public void setId(Supporter2DonationIDType id) {
		super.setId(id);
	}

	public Supporter2DonationDTO(String id) {
		setId(new Supporter2DonationIDType(id));
	}

	@Override
	public SupporterDTO getDtoA() {
		return super.getDtoA();
	}

	@Override
	public void setDtoA(SupporterDTO dtoA) {
		super.setDtoA(dtoA);
	}

	@Override
	public DonationDTO getDtoB() {
		return super.getDtoB();
	}

	@Override
	public void setDtoB(DonationDTO dtoB) {
		super.setDtoB(dtoB);
	}
}
