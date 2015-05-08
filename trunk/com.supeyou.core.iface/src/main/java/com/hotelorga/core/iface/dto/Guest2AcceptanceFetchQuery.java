package com.hotelorga.core.iface.dto;

import com.hotelorga.foundation.iface.datatype.FetchAssoQuery;
import com.hotelorga.foundation.iface.datatype.types.DateType;

public class Guest2AcceptanceFetchQuery extends FetchAssoQuery {

	private static final long serialVersionUID = 827206L;

	private DateType from = null;

	private DateType to = null;

	public DateType getFrom() {
		return from;
	}

	public void setFrom(DateType from) {
		this.from = from;
	}

	public DateType getTo() {
		return to;
	}

	public void setTo(DateType to) {
		this.to = to;
	}

}
