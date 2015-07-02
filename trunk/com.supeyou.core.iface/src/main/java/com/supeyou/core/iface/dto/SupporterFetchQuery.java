package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.datatype.FetchQuery;

public class SupporterFetchQuery extends FetchQuery {

	private static final long serialVersionUID = 4892062046L;

	private SupporterDTO invitor;

	public SupporterDTO getInvitor() {
		return invitor;
	}

	public void setInvitor(SupporterDTO invitor) {
		this.invitor = invitor;
	}

}
