package com.supeyou.actor.iface.dto;

import com.supeyou.crudie.iface.datatype.FetchQuery;

public class SessionFetchQuery extends FetchQuery {

	private static final long serialVersionUID = 4892062046L;

	private String httpSessionId;

	public String getHttpSessionId() {
		return httpSessionId;
	}

	public void setSessionId(String httpSessionId) {
		this.httpSessionId = httpSessionId;
	}

}
