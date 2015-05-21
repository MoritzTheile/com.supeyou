package com.supeyou.actor.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.supeyou.actor.iface.dto.Session2UserIDType;
import com.supeyou.crudie.impl.entity.AbstrManyToManyAsso;
import com.supeyou.crudie.impl.entity.UserEntity;
import com.supeyou.crudie.impl.util.STATICS;

@Entity
@Table(name = "Session2UserAsso", uniqueConstraints = @UniqueConstraint(columnNames = { STATICS.A_DBID, STATICS.B_DBID }))
public class Session2UserEntity extends AbstrManyToManyAsso<SessionEntity, UserEntity, Session2UserIDType> {

	@Override
	public Session2UserIDType getId() {
		if (this.dbid != null) {
			return new Session2UserIDType(dbid);
		} else {
			return null;
		}
	}

	@Override
	public void setId(Session2UserIDType typedId) {
		if (typedId != null) {
			this.dbid = typedId.value();
		} else {
			this.dbid = null;
		}
	}

}
