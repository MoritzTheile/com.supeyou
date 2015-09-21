package com.supeyou.actor.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.supeyou.actor.iface.dto.Session2EventIDType;
import com.supeyou.crudie.impl.entity.AbstrManyToManyAsso;
import com.supeyou.crudie.impl.util.STATICS;

@Entity
@Table(name = "Session2EventAsso", uniqueConstraints = @UniqueConstraint(columnNames = { STATICS.A_DBID, STATICS.B_DBID }))
public class Session2EventEntity extends AbstrManyToManyAsso<SessionEntity, EventEntity, Session2EventIDType> {

	@Override
	public Session2EventIDType getId() {
		if (this.dbid != null) {
			return new Session2EventIDType(dbid);
		} else {
			return null;
		}
	}

	@Override
	public void setId(Session2EventIDType typedId) {
		if (typedId != null) {
			this.dbid = typedId.value();
		} else {
			this.dbid = null;
		}
	}

}
