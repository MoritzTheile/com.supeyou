package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.dto.AbstrAssoDTO;
import com.supeyou.crudie.iface.dto.UserDTO;

public class User2SupporterDTO extends AbstrAssoDTO<UserDTO, SupporterDTO, User2SupporterIDType> {

	private static final long serialVersionUID = 536835689L;

	public User2SupporterDTO() {
	}

	public User2SupporterDTO(User2SupporterIDType id) {
		setId(id);
	}

	@Override
	public User2SupporterIDType getId() {
		return super.getId();
	}

	@Override
	public void setId(User2SupporterIDType id) {
		super.setId(id);
	}

	public User2SupporterDTO(String id) {
		setId(new User2SupporterIDType(id));
	}

	@Override
	public UserDTO getDtoA() {
		return super.getDtoA();
	}

	@Override
	public void setDtoA(UserDTO dtoA) {
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
