package com.supeyou.crudie.iface.dto;

import java.io.Serializable;

import com.supeyou.crudie.iface.datatype.enums.MIMETYPE;
import com.supeyou.crudie.iface.datatype.types.FileIDType;
import com.supeyou.crudie.iface.datatype.types.PositivLongType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;

public class FileDTO extends AbstrDTO<FileIDType> implements Serializable {

	private static final long serialVersionUID = 268459612;

	private SingleLineString256Type name;

	private PositivLongType size;

	private MIMETYPE mimetype;

	public FileDTO() {

	}

	public FileDTO(FileIDType id) {
		super.setId(id);
	}

	public FileDTO(String id) {
		super.setId(new FileIDType(id));
	}

	public SingleLineString256Type getName() {
		return name;
	}

	@Override
	public FileIDType getId() {
		return super.getId();
	}

	@Override
	public void setId(FileIDType id) {
		super.setId(id);
	}

	public void setName(SingleLineString256Type name) {
		this.name = name;
	}

	public MIMETYPE getMimetype() {
		return mimetype;
	}

	public void setMimetype(MIMETYPE mimetype) {
		this.mimetype = mimetype;
	}

	public PositivLongType getSize() {
		return size;
	}

	public void setSize(PositivLongType size) {
		this.size = size;
	}

}
