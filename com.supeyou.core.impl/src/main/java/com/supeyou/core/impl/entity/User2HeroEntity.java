package com.supeyou.core.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.supeyou.core.iface.dto.User2HeroIDType;
import com.supeyou.crudie.impl.entity.AbstrManyToManyAsso;
import com.supeyou.crudie.impl.entity.UserEntity;
import com.supeyou.crudie.impl.util.STATICS;

@Entity
@Table(name = "User2HeroAsso", uniqueConstraints = @UniqueConstraint(columnNames = { STATICS.A_DBID, STATICS.B_DBID }))
public class User2HeroEntity extends AbstrManyToManyAsso<UserEntity, HeroEntity, User2HeroIDType> {

	@Override
	public User2HeroIDType getId() {
		if (this.dbid != null) {
			return new User2HeroIDType(dbid);
		} else {
			return null;
		}
	}

	@Override
	public void setId(User2HeroIDType typedId) {
		if (typedId != null) {
			this.dbid = typedId.value();
		} else {
			this.dbid = null;
		}
	}

}
