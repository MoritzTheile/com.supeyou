package com.supeyou.core.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.supeyou.core.iface.dto.SupporterIDType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.impl.entity.AbstrEntity;

@Entity
@Table(name = "SupporterEntity")
public class SupporterEntity extends AbstrEntity<SupporterIDType> {

	private String comment;

	public SingleLineString256Type getComment() {
		if (comment == null) {
			return null;
		}
		return new SingleLineString256Type(comment);
	}

	public void setComment(SingleLineString256Type comment) {
		if (comment != null) {
			this.comment = comment.value();
		} else {
			this.comment = null;
		}
	}

	@Override
	public SupporterIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new SupporterIDType(dbid);
	}

	@Override
	public void setId(SupporterIDType typedId) {
		if (typedId != null) {
			dbid = typedId.value();
		} else {
			this.dbid = null;
		}

	}

}