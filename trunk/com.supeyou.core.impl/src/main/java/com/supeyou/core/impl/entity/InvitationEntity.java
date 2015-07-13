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

	@OneToMany(mappedBy = "a"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<Invitation2SupporterEntity> invitation2SupporterEntity = new ArrayList<Invitation2SupporterEntity>();

	public Collection<Invitation2SupporterEntity> getInvitation2SupporterEntity() {
		return invitation2SupporterEntity;
	}

	public void setInvitation2SupporterEntity(Collection<Invitation2SupporterEntity> invitation2SupporterEntity) {
		this.invitation2SupporterEntity = invitation2SupporterEntity;
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