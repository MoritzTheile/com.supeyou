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

	// redundant info, just for having it "in place"
	private String userLoginId;
	// redundant info, just for having it "in place"
	private String userId;
	// redundant info, just for having it "in place"
	private String userName;
	// redundant info, just for having it "in place"
	private String sessionId;

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

	public void setUserLoginId(SingleLineString256Type value) {
		if (value != null) {
			this.userLoginId = value.value();
		} else {
			this.userLoginId = null;
		}
	}

	public SingleLineString256Type getUserLoginId() {
		if (userLoginId == null) {
			return null;
		}
		return new SingleLineString256Type(userLoginId);
	}

	public void setUserId(SingleLineString256Type value) {
		if (value != null) {
			this.userId = value.value();
		} else {
			this.userId = null;
		}
	}

	public SingleLineString256Type getUserId() {
		if (userId == null) {
			return null;
		}
		return new SingleLineString256Type(userId);
	}

	public void setUserName(SingleLineString256Type value) {
		if (value != null) {
			this.userName = value.value();
		} else {
			this.userName = null;
		}
	}

	public SingleLineString256Type getUserName() {
		if (userName == null) {
			return null;
		}
		return new SingleLineString256Type(userName);
	}

	public void setSessionId(SingleLineString256Type value) {
		if (value != null) {
			this.sessionId = value.value();
		} else {
			this.sessionId = null;
		}
	}

	public SingleLineString256Type getSessionId() {
		if (sessionId == null) {
			return null;
		}
		return new SingleLineString256Type(sessionId);
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