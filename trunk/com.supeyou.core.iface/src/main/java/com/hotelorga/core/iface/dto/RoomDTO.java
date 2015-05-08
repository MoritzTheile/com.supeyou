package com.hotelorga.core.iface.dto;

import java.io.Serializable;

import com.hotelorga.foundation.iface.datatype.types.PositivIntegerType;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.iface.dto.AbstrDTO;

public class RoomDTO extends AbstrDTO<RoomIDType> implements Serializable {

	private static final long serialVersionUID = 824618946818L;

	private SingleLineString256Type name;
	private SingleLineString256Type comment;
	private PositivIntegerType numberOfBeds;

	public RoomDTO() {

	}

	public RoomDTO(RoomIDType id) {
		setId(id);
	}

	public RoomDTO(String id) {
		setId(new RoomIDType(id));
	}

	@Override
	public void setId(RoomIDType id) {
		super.setId(id);
	}

	@Override
	public RoomIDType getId() {
		return super.getId();
	}

	public SingleLineString256Type getName() {
		return name;
	}

	public void setName(SingleLineString256Type name) {
		this.name = name;
	}

	public SingleLineString256Type getComment() {
		return comment;
	}

	public void setComment(SingleLineString256Type comment) {
		this.comment = comment;
	}

	public PositivIntegerType getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(PositivIntegerType numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

}
