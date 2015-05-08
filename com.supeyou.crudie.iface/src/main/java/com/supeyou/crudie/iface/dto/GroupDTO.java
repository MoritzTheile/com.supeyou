package com.supeyou.crudie.iface.dto;

import java.io.Serializable;

import com.supeyou.crudie.iface.datatype.types.GroupIDType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;

public class GroupDTO extends AbstrDTO<GroupIDType> implements Serializable {

	private static final long serialVersionUID = 3272656;

	private SingleLineString256Type name;

	public GroupDTO() {

	}

	public GroupDTO(GroupIDType id) {
		setId(id);
	}

	public GroupDTO(String id) {
		setId(new GroupIDType(id));
	}

	@Override
	public void setId(GroupIDType id) {
		super.setId(id);
	}

	@Override
	public GroupIDType getId() {
		return super.getId();
	}

	public SingleLineString256Type getName() {
		return name;
	}

	public void setName(SingleLineString256Type name) {
		this.name = name;
	}

}
