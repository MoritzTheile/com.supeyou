package com.supeyou.core.iface.dto;

import java.io.Serializable;

import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.AbstrDTO;

public class InvitationDTO extends AbstrDTO<InvitationIDType> implements Serializable {

	private static final long serialVersionUID = 824618946818L;

	private SingleLineString256Type comment;

	public InvitationDTO() {

	}

	public InvitationDTO(InvitationIDType id) {
		setId(id);
	}

	public InvitationDTO(String id) {
		setId(new InvitationIDType(id));
	}

	@Override
	public void setId(InvitationIDType id) {
		super.setId(id);
	}

	@Override
	public InvitationIDType getId() {
		return super.getId();
	}

	public SingleLineString256Type getComment() {
		return comment;
	}

	public void setComment(SingleLineString256Type comment) {
		this.comment = comment;
	}

}
