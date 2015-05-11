package com.supeyou.core.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.supeyou.core.iface.dto.HeroIDType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.impl.entity.AbstrEntity;

@Entity
@Table(name = "HeroEntity")
public class HeroEntity extends AbstrEntity<HeroIDType> {

	/**
	 * Login of User. Can be used additionally to the email.
	 */
	@Column(name = "NAME", nullable = true, length = 256, unique = true)
	private String name;

	private String comment;

	public SingleLineString256Type getName() {
		if (name == null) {
			return null;
		}
		return new SingleLineString256Type(name);
	}

	public void setName(SingleLineString256Type name) {
		if (name != null) {
			this.name = name.value();
		} else {
			this.name = null;
		}
	}

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
	public HeroIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new HeroIDType(dbid);
	}

	@Override
	public void setId(HeroIDType typedId) {
		if (typedId != null) {
			dbid = typedId.value();
		} else {
			this.dbid = null;
		}

	}

}