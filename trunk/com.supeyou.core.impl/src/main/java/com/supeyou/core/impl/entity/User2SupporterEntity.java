package com.supeyou.core.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.supeyou.core.iface.dto.User2SupporterIDType;
import com.supeyou.crudie.impl.entity.AbstrManyToManyAsso;
import com.supeyou.crudie.impl.entity.UserEntity;
import com.supeyou.crudie.impl.util.STATICS;

@Entity
@Table(name = "User2SupporterAsso", uniqueConstraints = @UniqueConstraint(columnNames = { STATICS.A_DBID, STATICS.B_DBID }))
public class User2SupporterEntity extends AbstrManyToManyAsso<UserEntity, SupporterEntity, User2SupporterIDType> {

	@Override
	public User2SupporterIDType getId() {
		if (this.dbid != null) {
			return new User2SupporterIDType(dbid);
		} else {
			return null;
		}
	}

	@Override
	public void setId(User2SupporterIDType typedId) {
		if (typedId != null) {
			this.dbid = typedId.value();
		} else {
			this.dbid = null;
		}
	}

}
