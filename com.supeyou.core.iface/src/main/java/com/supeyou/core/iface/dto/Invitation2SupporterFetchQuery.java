package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.datatype.FetchAssoQuery;

public class Invitation2SupporterFetchQuery extends FetchAssoQuery {

	private static final long serialVersionUID = 2478245726L;
	private SupporterDTO invitor;

	public SupporterDTO getInvitor() {
		return invitor;
	}

	public void setInvitor(SupporterDTO invitor) {
		this.invitor = invitor;
	}

}
