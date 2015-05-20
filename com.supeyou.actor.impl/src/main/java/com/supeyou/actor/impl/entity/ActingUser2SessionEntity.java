package com.supeyou.actor.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.supeyou.actor.iface.dto.ActingUser2SessionIDType;
import com.supeyou.crudie.impl.entity.AbstrManyToManyAsso;
import com.supeyou.crudie.impl.util.STATICS;

@Entity
@Table(name = "ActingUser2SessionAsso", uniqueConstraints = @UniqueConstraint(columnNames = { STATICS.A_DBID, STATICS.B_DBID }))
public class ActingUser2SessionEntity extends AbstrManyToManyAsso<ActingUserEntity, SessionEntity, ActingUser2SessionIDType> {

	@Override
	public ActingUser2SessionIDType getId() {
		if (this.dbid != null) {
			return new ActingUser2SessionIDType(dbid);
		} else {
			return null;
		}
	}

	@Override
	public void setId(ActingUser2SessionIDType typedId) {
		if (typedId != null) {
			this.dbid = typedId.value();
		} else {
			this.dbid = null;
		}
	}

}
