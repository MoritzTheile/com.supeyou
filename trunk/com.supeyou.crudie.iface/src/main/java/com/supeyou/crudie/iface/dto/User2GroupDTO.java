package com.supeyou.crudie.iface.dto;

import com.supeyou.crudie.iface.datatype.types.User2GroupIDType;

public class User2GroupDTO extends AbstrAssoDTO<UserDTO, GroupDTO, User2GroupIDType> {

	private static final long serialVersionUID = 536835689L;

	public User2GroupDTO() {
	}

	public User2GroupDTO(User2GroupIDType id) {
		setId(id);
	}

	@Override
	public User2GroupIDType getId() {
		return super.getId();
	}

	@Override
	public void setId(User2GroupIDType id) {
		super.setId(id);
	}

	public User2GroupDTO(String id) {
		setId(new User2GroupIDType(id));
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
	public GroupDTO getDtoB() {
		return super.getDtoB();
	}

	@Override
	public void setDtoB(GroupDTO dtoB) {
		super.setDtoB(dtoB);
	}
}
