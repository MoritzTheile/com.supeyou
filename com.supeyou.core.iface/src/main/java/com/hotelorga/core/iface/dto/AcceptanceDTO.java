package com.hotelorga.core.iface.dto;

import java.io.Serializable;

import com.hotelorga.foundation.iface.datatype.types.AmountType;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.iface.datatype.types.PositivIntegerType;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.iface.dto.AbstrDTO;

public class AcceptanceDTO extends AbstrDTO<AcceptanceIDType> implements Serializable {

	private static final long serialVersionUID = 13626417L;

	private SingleLineString256Type tmpName;
	private SingleLineString256Type comment;
	private DateType fromDate;
	private DateType toDate;
	private AmountType acceptedCosts;
	private AmountType fixOwnCosts;
	private PositivIntegerType acceptedDays;

	public AcceptanceDTO() {

	}

	public AcceptanceDTO(AcceptanceIDType id) {
		setId(id);
	}

	public AcceptanceDTO(String id) {
		setId(new AcceptanceIDType(id));
	}

	@Override
	public void setId(AcceptanceIDType id) {
		super.setId(id);
	}

	@Override
	public AcceptanceIDType getId() {
		return super.getId();
	}

	public SingleLineString256Type getComment() {
		return comment;
	}

	public void setComment(SingleLineString256Type comment) {
		this.comment = comment;
	}

	public DateType getFromDate() {
		return fromDate;
	}

	public void setFromDate(DateType fromDate) {
		this.fromDate = fromDate;
	}

	public DateType getToDate() {
		return toDate;
	}

	public void setToDate(DateType toDate) {
		this.toDate = toDate;
	}

	public AmountType getAcceptedCosts() {
		return acceptedCosts;
	}

	public void setAcceptedCosts(AmountType acceptedCosts) {
		this.acceptedCosts = acceptedCosts;
	}

	public SingleLineString256Type getTmpName() {
		return tmpName;
	}

	public void setTmpName(SingleLineString256Type tmpName) {
		this.tmpName = tmpName;
	}

	public PositivIntegerType getAcceptedDays() {
		return acceptedDays;
	}

	public void setAcceptedDays(PositivIntegerType acceptedDays) {
		this.acceptedDays = acceptedDays;
	}

	public AmountType getFixOwnCosts() {
		return fixOwnCosts;
	}

	public void setFixOwnCosts(AmountType fixOwnCosts) {
		this.fixOwnCosts = fixOwnCosts;
	}

}
