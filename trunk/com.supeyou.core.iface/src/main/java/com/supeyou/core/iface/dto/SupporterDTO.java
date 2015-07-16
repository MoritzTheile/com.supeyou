package com.supeyou.core.iface.dto;

import java.io.Serializable;

import com.supeyou.crudie.iface.dto.AbstrDTO;
import com.supeyou.crudie.iface.dto.UserDTO;

public class SupporterDTO extends AbstrDTO<SupporterIDType> implements Serializable {

	private static final long serialVersionUID = 824618946818L;

	private UserDTO userDTO;

	private HeroDTO heroDTO;

	public SupporterDTO() {

	}

	public SupporterDTO(SupporterIDType id) {
		setId(id);
	}

	public SupporterDTO(String id) {
		setId(new SupporterIDType(id));
	}

	@Override
	public void setId(SupporterIDType id) {
		super.setId(id);
	}

	@Override
	public SupporterIDType getId() {
		return super.getId();
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public HeroDTO getHeroDTO() {
		return heroDTO;
	}

	public void setHeroDTO(HeroDTO heroDTO) {
		this.heroDTO = heroDTO;
	}

}
