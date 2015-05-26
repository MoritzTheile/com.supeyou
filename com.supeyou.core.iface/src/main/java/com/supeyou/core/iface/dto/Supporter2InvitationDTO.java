package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.dto.AbstrAssoDTO;

public class Supporter2InvitationDTO extends AbstrAssoDTO<SupporterDTO, InvitationDTO, Supporter2InvitationIDType> {

	private static final long serialVersionUID = 536835689L;

	public Supporter2InvitationDTO() {
	}

	public Supporter2InvitationDTO(Supporter2InvitationIDType id) {
		setId(id);
	}

	@Override
	public Supporter2InvitationIDType getId() {
		return super.getId();
	}

	@Override
	public void setId(Supporter2InvitationIDType id) {
		super.setId(id);
	}

	public Supporter2InvitationDTO(String id) {
		setId(new Supporter2InvitationIDType(id));
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
	public InvitationDTO getDtoB() {
		return super.getDtoB();
	}

	@Override
	public void setDtoB(InvitationDTO dtoB) {
		super.setDtoB(dtoB);
	}
}
