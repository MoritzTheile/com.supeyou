package com.supeyou.core.impl.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.supeyou.core.iface.dto.InvitationIDType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.impl.entity.AbstrEntity;

@Entity
@Table(name = "InvitationEntity")
public class InvitationEntity extends AbstrEntity<InvitationIDType> {

	private String comment;

	@OneToMany(mappedBy = "b"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<Supporter2InvitationEntity> supporter2InvitationCollection = new ArrayList<Supporter2InvitationEntity>();

	@OneToMany(mappedBy = "a"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<Invitation2SupporterEntity> invitation2SupporterCollection = new ArrayList<Invitation2SupporterEntity>();

	public Collection<Supporter2InvitationEntity> getSupporter2InvitationCollection() {
		return supporter2InvitationCollection;
	}

	public void setSupporter2InvitationCollection(Collection<Supporter2InvitationEntity> supporter2InvitationCollection) {
		this.supporter2InvitationCollection = supporter2InvitationCollection;
	}

	public Collection<Invitation2SupporterEntity> getInvitation2SupporterCollection() {
		return invitation2SupporterCollection;
	}

	public void setInvitation2SupporterCollection(Collection<Invitation2SupporterEntity> invitation2SupporterCollection) {
		this.invitation2SupporterCollection = invitation2SupporterCollection;
	}

	@Column(nullable = false, length = 256, unique = true)
	private String token;

	public SingleLineString256Type getComment() {
		if (comment == null) {
			return null;
		}
		return new SingleLineString256Type(comment);
	}

	public void setComment(SingleLineString256Type comment) {
		if (comment != null) {
			this.comment = comment.value();
		} else {
			this.comment = null;
		}
	}

	public SingleLineString256Type getToken() {
		if (token == null) {
			return null;
		}
		return new SingleLineString256Type(token);
	}

	public void setToken(SingleLineString256Type token) {
		if (token != null) {
			this.token = token.value();
		} else {
			this.token = null;
		}
	}

	@Override
	public InvitationIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new InvitationIDType(dbid);
	}

	@Override
	public void setId(InvitationIDType typedId) {
		if (typedId != null) {
			dbid = typedId.value();
		} else {
			this.dbid = null;
		}

	}

}