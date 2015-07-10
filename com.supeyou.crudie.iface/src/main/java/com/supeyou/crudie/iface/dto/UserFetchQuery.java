package com.supeyou.crudie.iface.dto;

import com.supeyou.crudie.iface.datatype.FetchQuery;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;

public class UserFetchQuery extends FetchQuery {

	private static final long serialVersionUID = 2482451638L;

	public static final String COLUMN_EMAILADDRESS = "EMAILADDRESS";

	public static final String COLUMN_AMOUNT = "AMOUNT";

	public static final String COLUMN_BIRTHDATE = "BIRTHDATE";

	public static final String COLUMN_ACTIVE = "ACTIVE";

	public static final String COLUMN_LOGINID = "LOGINID";

	public static final String COLUMN_AUTHTOKEN = "AUTHTOKEN";

	public static final String COLUMN_LOCALE = "LOCALE";

	private SingleLineString256Type authToken;

	public SingleLineString256Type getAuthToken() {
		return authToken;
	}

	public void setAuthToken(SingleLineString256Type authToken) {
		this.authToken = authToken;
	}

}
