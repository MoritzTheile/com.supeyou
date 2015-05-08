package com.hotelorga.foundation.impl.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hotelorga.foundation.iface.datatype.types.GroupIDType;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;

@Entity
@Table(name = "GroupEntity")
public class GroupEntity extends AbstrEntity<GroupIDType> {

	/**
	 * Login of User. Can be used additionally to the email.
	 */
	// @Index(name = "NAME_INDEX")
	@Column(name = "NAME", nullable = false, length = 256, unique = true)
	private String name;

	// no getter or setter, just for defining cascades
	@OneToMany(mappedBy = "b"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<User2GroupEntity> user2groupCollection = new ArrayList<User2GroupEntity>();

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

	@Override
	public GroupIDType getId() {
		if (this.dbid != null) {
			return new GroupIDType(dbid);
		} else {
			return null;
		}
	}

	@Override
	public void setId(GroupIDType typedId) {
		if (typedId != null) {
			this.dbid = typedId.value();
		} else {
			this.dbid = null;
		}
	}

}