package com.supeyou.core.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.supeyou.core.iface.datatype.enums.GROUPROLE;
import com.supeyou.core.iface.dto.Guest2GuestGroupIDType;
import com.supeyou.crudie.impl.entity.AbstrManyToManyAsso;
import com.supeyou.crudie.impl.util.STATICS;

@Entity
@Table(name = "Guest2GuestGroupAsso", uniqueConstraints = @UniqueConstraint(columnNames = { STATICS.A_DBID }))
public class Guest2GuestGroupEntity extends AbstrManyToManyAsso<GuestEntity, GuestGroupEntity, Guest2GuestGroupIDType> {

	private GROUPROLE groupRole = GROUPROLE.NO_ROLE;
	private Boolean groupLeader = false;;

	public GROUPROLE getGroupRole() {
		return groupRole;
	}

	public void setGroupRole(GROUPROLE groupRole) {
		this.groupRole = groupRole;
	}

	public Boolean getGroupLeader() {
		return groupLeader;
	}

	public void setGroupLeader(Boolean groupLeader) {
		this.groupLeader = groupLeader;
	}

	@Override
	public Guest2GuestGroupIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new Guest2GuestGroupIDType(dbid);
	}

	@Override
	public void setId(Guest2GuestGroupIDType typedId) {
		if (typedId != null) {
			dbid = typedId.value();
		} else {
			this.dbid = null;
		}

	}

}
