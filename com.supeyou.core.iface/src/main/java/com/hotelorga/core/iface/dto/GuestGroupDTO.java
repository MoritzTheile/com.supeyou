package com.hotelorga.core.iface.dto;

import java.io.Serializable;

import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.iface.dto.AbstrDTO;

public class GuestGroupDTO extends AbstrDTO<GuestGroupIDType> implements Serializable {

	private static final long serialVersionUID = 934578252L;

	private SingleLineString256Type name;

	public GuestGroupDTO() {

	}

	public GuestGroupDTO(GuestGroupIDType id) {
		setId(id);
	}

	public GuestGroupDTO(String id) {
		setId(new GuestGroupIDType(id));
	}

	@Override
	public void setId(GuestGroupIDType id) {
		super.setId(id);
	}

	@Override
	public GuestGroupIDType getId() {
		return super.getId();
	}

	public SingleLineString256Type getName() {
		return name;
	}

	public void setName(SingleLineString256Type name) {
		this.name = name;
	}

}
