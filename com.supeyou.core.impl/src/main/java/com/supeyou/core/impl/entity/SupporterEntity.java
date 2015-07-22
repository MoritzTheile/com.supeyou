package com.supeyou.core.impl.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.supeyou.core.iface.dto.SupporterIDType;
import com.supeyou.crudie.impl.entity.AbstrEntity;

@Entity
@Table(name = "SupporterEntity")
public class SupporterEntity extends AbstrEntity<SupporterIDType> {

	@OneToMany(mappedBy = "a"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<Supporter2InvitationEntity> supporter2invitationCollection = new ArrayList<Supporter2InvitationEntity>();

	@OneToMany(mappedBy = "b"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<User2SupporterEntity> user2SupporterCollection = new ArrayList<User2SupporterEntity>();

	@OneToMany(mappedBy = "b"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<Hero2SupporterEntity> hero2SupporterCollection = new ArrayList<Hero2SupporterEntity>();

	@OneToMany(mappedBy = "b"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<Invitation2SupporterEntity> invitation2SupporterCollection = new ArrayList<Invitation2SupporterEntity>();

	public Collection<User2SupporterEntity> getUser2SupporterCollection() {
		return user2SupporterCollection;
	}

	public void setUser2SupporterCollection(Collection<User2SupporterEntity> user2SupporterCollection) {
		this.user2SupporterCollection = user2SupporterCollection;
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

	public Collection<Hero2SupporterEntity> getHero2SupporterCollection() {
		return hero2SupporterCollection;
	}

	public void setHero2SupporterCollection(Collection<Hero2SupporterEntity> hero2SupporterCollection) {
		this.hero2SupporterCollection = hero2SupporterCollection;
	}

	public Collection<Invitation2SupporterEntity> getInvitation2SupporterCollection() {
		return invitation2SupporterCollection;
	}

	public void setInvitation2SupporterCollection(Collection<Invitation2SupporterEntity> invitation2SupporterCollection) {
		this.invitation2SupporterCollection = invitation2SupporterCollection;
	}

}