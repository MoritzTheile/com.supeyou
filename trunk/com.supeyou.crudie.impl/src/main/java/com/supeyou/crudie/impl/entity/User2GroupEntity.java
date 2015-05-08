package com.supeyou.crudie.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.supeyou.crudie.iface.datatype.types.User2GroupIDType;
import com.supeyou.crudie.impl.util.STATICS;

@Entity
@Table(name = "User2GroupAsso", uniqueConstraints = @UniqueConstraint(columnNames = { STATICS.A_DBID, STATICS.B_DBID }))
public class User2GroupEntity extends AbstrManyToManyAsso<UserEntity, GroupEntity, User2GroupIDType> {

	@Override
	public User2GroupIDType getId() {
		if (this.dbid != null) {
			return new User2GroupIDType(dbid);
		} else {
			return null;
		}
	}

	@Override
	public void setId(User2GroupIDType typedId) {
		if (typedId != null) {
			this.dbid = typedId.value();
		} else {
			this.dbid = null;
		}
	}

}
