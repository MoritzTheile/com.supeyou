package com.supeyou.core.iface.dto;

import java.io.Serializable;

import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.AbstrDTO;

public class SupporterDTO extends AbstrDTO<SupporterIDType> implements Serializable {

	private static final long serialVersionUID = 824618946818L;

	private SingleLineString256Type comment;

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

	public SingleLineString256Type getComment() {
		return comment;
	}

	public void setComment(SingleLineString256Type comment) {
		this.comment = comment;
	}

}
