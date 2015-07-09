package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.dto.AbstrAssoDTO;
import com.supeyou.crudie.iface.dto.UserDTO;

public class User2HeroDTO extends AbstrAssoDTO<UserDTO, HeroDTO, User2HeroIDType> {

	private static final long serialVersionUID = 536835689L;

	public User2HeroDTO() {
	}

	public User2HeroDTO(User2HeroIDType id) {
		setId(id);
	}

	@Override
	public User2HeroIDType getId() {
		return super.getId();
	}

	@Override
	public void setId(User2HeroIDType id) {
		super.setId(id);
	}

	public User2HeroDTO(String id) {
		setId(new User2HeroIDType(id));
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
	public HeroDTO getDtoB() {
		return super.getDtoB();
	}

	@Override
	public void setDtoB(HeroDTO dtoB) {
		super.setDtoB(dtoB);
	}
}
