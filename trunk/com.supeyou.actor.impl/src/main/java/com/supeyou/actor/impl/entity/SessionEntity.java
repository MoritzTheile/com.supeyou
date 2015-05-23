package com.supeyou.actor.impl.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.supeyou.actor.iface.dto.SessionIDType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.impl.entity.AbstrEntity;

@Entity
@Table(name = "SessionEntity")
public class SessionEntity extends AbstrEntity<SessionIDType> {

	private String httpSessionId;

	private String browserMark;

	@OneToMany(mappedBy = "b"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<Session2UserEntity> actingUser2sessionCollection = new ArrayList<Session2UserEntity>();

	public SingleLineString256Type getHttpSessionId() {
		if (httpSessionId == null) {
			return null;
		}
		return new SingleLineString256Type(httpSessionId);
	}

	public void setHttpSessionId(SingleLineString256Type httpSessionId) {
		if (httpSessionId != null) {
			this.httpSessionId = httpSessionId.value();
		} else {
			this.httpSessionId = null;
		}
	}

	public void setBrowserMark(SingleLineString256Type browserMark) {
		if (browserMark != null) {
			this.browserMark = browserMark.value();
		} else {
			this.browserMark = null;
		}
	}

	public SingleLineString256Type getBrowserMark() {
		if (browserMark == null) {
			return null;
		}
		return new SingleLineString256Type(browserMark);
	}

	@Override
	public SessionIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new SessionIDType(dbid);
	}

	@Override
	public void setId(SessionIDType typedId) {
		if (typedId != null) {
			dbid = typedId.value();
		} else {
			this.dbid = null;
		}

	}

}