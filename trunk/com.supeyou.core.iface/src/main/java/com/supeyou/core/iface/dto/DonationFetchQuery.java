package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.datatype.FetchQuery;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;

public class DonationFetchQuery extends FetchQuery {

	private static final long serialVersionUID = 4892062046L;

	private SingleLineString256Type txnId;

	public SingleLineString256Type getTxnId() {
		return txnId;
	}

	public void setTxnId(SingleLineString256Type txnId) {
		this.txnId = txnId;
	}

}
