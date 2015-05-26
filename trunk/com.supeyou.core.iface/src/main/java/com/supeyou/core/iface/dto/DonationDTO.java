package com.supeyou.core.iface.dto;

import java.io.Serializable;

import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.AbstrDTO;

public class DonationDTO extends AbstrDTO<DonationIDType> implements Serializable {

	private static final long serialVersionUID = 824618946818L;

	private SingleLineString256Type comment;

	public DonationDTO() {

	}

	public DonationDTO(DonationIDType id) {
		setId(id);
	}

	public DonationDTO(String id) {
		setId(new DonationIDType(id));
	}

	@Override
	public void setId(DonationIDType id) {
		super.setId(id);
	}

	@Override
	public DonationIDType getId() {
		return super.getId();
	}

	public SingleLineString256Type getComment() {
		return comment;
	}

	public void setComment(SingleLineString256Type comment) {
		this.comment = comment;
	}

}
