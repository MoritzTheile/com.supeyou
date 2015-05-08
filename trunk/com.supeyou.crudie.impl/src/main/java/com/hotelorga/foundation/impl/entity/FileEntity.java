package com.hotelorga.foundation.impl.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.hotelorga.foundation.iface.datatype.enums.MIMETYPE;
import com.hotelorga.foundation.iface.datatype.types.FileIDType;
import com.hotelorga.foundation.iface.datatype.types.PositivLongType;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;

@Entity
@Table(name = "FileEntity")
public class FileEntity extends AbstrEntity<FileIDType> {

	private String name;

	private MIMETYPE mimetype;

	private Long size;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] data;

	public SingleLineString256Type getName() {
		if (this.name != null) {
			return new SingleLineString256Type(name);
		} else {
			return null;
		}
	}

	public void setName(SingleLineString256Type name) {
		if (name != null) {
			this.name = name.value();
		} else {
			this.name = null;
		}
	}

	public MIMETYPE getMimetype() {
		return mimetype;
	}

	public void setMimetype(MIMETYPE mimetype) {
		this.mimetype = mimetype;
	}

	@Override
	public FileIDType getId() {
		if (this.dbid != null) {
			return new FileIDType(dbid);
		} else {
			return null;
		}
	}

	@Override
	public void setId(FileIDType typedId) {
		if (typedId != null) {
			this.dbid = typedId.value();
		} else {
			this.dbid = null;
		}
	}

	public PositivLongType getSize() {
		if (size == null) {
			return null;
		}
		return new PositivLongType(size);
	}

	public void setSize(PositivLongType value) {
		if (value != null) {
			this.size = value.value();
		} else {
			this.size = null;
		}
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}