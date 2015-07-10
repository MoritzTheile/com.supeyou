package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.datatype.FetchQuery;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;

public class InvitationFetchQuery extends FetchQuery {

	private static final long serialVersionUID = 4892062046L;

	private SingleLineString256Type token;

	public SingleLineString256Type getToken() {
		return token;
	}

	public void setToken(SingleLineString256Type token) {
		this.token = token;
	}

}
