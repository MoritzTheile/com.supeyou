package com.supeyou.core.impl.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.supeyou.core.iface.dto.SupporterIDType;
import com.supeyou.crudie.impl.entity.AbstrEntity;

@Entity
@Table(name = "SupporterEntity")
public class SupporterEntity extends AbstrEntity<SupporterIDType> {

	@OneToMany(mappedBy = "a"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<Supporter2InvitationEntity> supporter2invitationCollection = new ArrayList<Supporter2InvitationEntity>();

	@OneToMany(mappedBy = "a"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@Size(min = 1, max = 1)
	private Collection<User2SupporterEntity> supporter2UserCollection = new ArrayList<User2SupporterEntity>();

	public Collection<User2SupporterEntity> getSupporter2UserCollection() {
		return supporter2UserCollection;
	}

	public void setSupporter2userCollection(Collection<User2SupporterEntity> supporter2userCollection) {
		this.supporter2UserCollection = supporter2userCollection;
	}

	@Override
	public SupporterIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new SupporterIDType(dbid);
	}

	@Override
	public void setId(SupporterIDType typedId) {
		if (typedId != null) {
			dbid = typedId.value();
		} else {
			this.dbid = null;
		}

	}

	public Collection<Supporter2InvitationEntity> getSupporter2invitationCollection() {
		return supporter2invitationCollection;
	}

	public void setSupporter2invitationCollection(Collection<Supporter2InvitationEntity> supporter2invitationCollection) {
		this.supporter2invitationCollection = supporter2invitationCollection;
	}

}