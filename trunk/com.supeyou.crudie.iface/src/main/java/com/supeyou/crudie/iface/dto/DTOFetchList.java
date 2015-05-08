package com.supeyou.crudie.iface.dto;

import java.util.ArrayList;

public class DTOFetchList<T> extends ArrayList<T> {
	private static final long serialVersionUID = 245727936980173L;

	private int totalSize;

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

}
