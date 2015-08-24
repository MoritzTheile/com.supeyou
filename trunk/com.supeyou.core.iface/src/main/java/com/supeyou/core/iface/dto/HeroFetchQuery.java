package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.datatype.FetchQuery;

public class HeroFetchQuery extends FetchQuery {

	private static final long serialVersionUID = 4892062046L;

	private boolean showActiveOnly = false;

	public boolean showActiveOnly() {
		return showActiveOnly;
	}

	public void setShowActiveOnly(boolean showHidden) {
		this.showActiveOnly = showHidden;
	}

}
