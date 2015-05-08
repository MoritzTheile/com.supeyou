package com.supeyou.core.impl.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.supeyou.core.iface.dto.GuestGroupIDType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.impl.entity.AbstrEntity;

@Entity
@Table(name = "GuestGroupEntity")
public class GuestGroupEntity extends AbstrEntity<GuestGroupIDType> {

	/**
	 * Login of User. Can be used additionally to the email.
	 */
	@Column(name = "NAME", nullable = true, length = 256, unique = true)
	private String name;

	@OneToMany(mappedBy = "b"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<Guest2GuestGroupEntity> guest2guestGroupCollection = new ArrayList<Guest2GuestGroupEntity>();

	public Collection<Guest2GuestGroupEntity> getGuest2guestGroupCollection() {
		return guest2guestGroupCollection;
	}

	public void setGuest2guestGroupCollection(Collection<Guest2GuestGroupEntity> guest2guestGroupCollection) {
		this.guest2guestGroupCollection = guest2guestGroupCollection;
	}

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
	public GuestGroupIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new GuestGroupIDType(dbid);
	}

	@Override
	public void setId(GuestGroupIDType typedId) {
		if (typedId != null) {
			dbid = typedId.value();
		} else {
			this.dbid = null;
		}

	}

}