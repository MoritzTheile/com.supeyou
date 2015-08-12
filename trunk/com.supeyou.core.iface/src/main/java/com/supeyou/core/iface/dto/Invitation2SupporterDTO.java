package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.dto.AbstrAssoDTO;

public class Invitation2SupporterDTO extends AbstrAssoDTO<InvitationDTO, SupporterDTO, Invitation2SupporterIDType> {

	private static final long serialVersionUID = 536835689L;

	private boolean treeDestroying = true;

	public Invitation2SupporterDTO() {
	}

	public Invitation2SupporterDTO(Invitation2SupporterIDType id) {
		setId(id);
	}

	@Override
	public Invitation2SupporterIDType getId() {
		return super.getId();
	}

	@Override
	public void setId(Invitation2SupporterIDType id) {
		super.setId(id);
	}

	public Invitation2SupporterDTO(String id) {
		setId(new Invitation2SupporterIDType(id));
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
	public SupporterDTO getDtoB() {
		return super.getDtoB();
	}

	@Override
	public void setDtoB(SupporterDTO dtoB) {
		super.setDtoB(dtoB);
	}

	public boolean getTreeDestroying() {
		return treeDestroying;
	}

	public void setTreeDestroying(boolean treeDestroying) {
		this.treeDestroying = treeDestroying;
	}

}
