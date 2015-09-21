package com.supeyou.actor.impl.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.supeyou.actor.iface.dto.EventIDType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.impl.entity.AbstrEntity;

@Entity
@Table(name = "EventEntity")
public class EventEntity extends AbstrEntity<EventIDType> {

	private String formattedTimestamp;
	private String pageAgeSeconds;
	private String category;
	private String action;
	private String value;

	@OneToMany(mappedBy = "b"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<Session2EventEntity> actingUser2sessionCollection = new ArrayList<Session2EventEntity>();

	public void setFormattedTimestamp(SingleLineString256Type value) {
		if (value != null) {
			this.formattedTimestamp = value.value();
		} else {
			this.formattedTimestamp = null;
		}
	}

	public SingleLineString256Type getFormattedTimestamp() {
		if (formattedTimestamp == null) {
			return null;
		}
		return new SingleLineString256Type(formattedTimestamp);
	}

	public void setPageAgeSeconds(SingleLineString256Type value) {
		if (value != null) {
			this.pageAgeSeconds = value.value();
		} else {
			this.pageAgeSeconds = null;
		}
	}

	public SingleLineString256Type getPageAgeSeconds() {
		if (pageAgeSeconds == null) {
			return null;
		}
		return new SingleLineString256Type(pageAgeSeconds);
	}

	public void setCategory(SingleLineString256Type value) {
		if (value != null) {
			this.category = value.value();
		} else {
			this.category = null;
		}
	}

	public SingleLineString256Type getCategory() {
		if (category == null) {
			return null;
		}
		return new SingleLineString256Type(category);
	}

	public void setAction(SingleLineString256Type value) {
		if (value != null) {
			this.action = value.value();
		} else {
			this.action = null;
		}
	}

	public SingleLineString256Type getAction() {
		if (action == null) {
			return null;
		}
		return new SingleLineString256Type(action);
	}

	public void setValue(SingleLineString256Type value) {
		if (value != null) {
			this.value = value.value();
		} else {
			this.value = null;
		}
	}

	public SingleLineString256Type getValue() {
		if (value == null) {
			return null;
		}
		return new SingleLineString256Type(value);
	}

	@Override
	public EventIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new EventIDType(dbid);
	}

	@Override
	public void setId(EventIDType typedId) {
		if (typedId != null) {
			dbid = typedId.value();
		} else {
			this.dbid = null;
		}

	}

}