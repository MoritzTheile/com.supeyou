package com.supeyou.core.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.supeyou.core.iface.dto.DonationIDType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.impl.entity.AbstrEntity;

@Entity
@Table(name = "DonationEntity")
public class DonationEntity extends AbstrEntity<DonationIDType> {

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
	public DonationIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new DonationIDType(dbid);
	}

	@Override
	public void setId(DonationIDType typedId) {
		if (typedId != null) {
			dbid = typedId.value();
		} else {
			this.dbid = null;
		}

	}

}