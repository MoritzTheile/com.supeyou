package com.supeyou.core.iface.dto;

import java.io.Serializable;

import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.AbstrDTO;

public class PayerDTO extends AbstrDTO<PayerIDType> implements Serializable {

	private static final long serialVersionUID = 2471346368L;

	private SingleLineString256Type name;
	private SingleLineString256Type confideTo;
	private SingleLineString256Type billAddrName;
	private SingleLineString256Type billAddrStreet;
	private SingleLineString256Type billAddrPostalCode;
	private SingleLineString256Type billAddrCity;
	private SingleLineString256Type comment;

	public PayerDTO() {

	}

	public PayerDTO(PayerIDType id) {
		setId(id);
	}

	public PayerDTO(String id) {
		setId(new PayerIDType(id));
	}

	@Override
	public void setId(PayerIDType id) {
		super.setId(id);
	}

	@Override
	public PayerIDType getId() {
		return super.getId();
	}

	public SingleLineString256Type getName() {
		return name;
	}

	public void setName(SingleLineString256Type name) {
		this.name = name;
	}

	public SingleLineString256Type getConfideTo() {
		return confideTo;
	}

	public void setConfideTo(SingleLineString256Type confideTo) {
		this.confideTo = confideTo;
	}

	public SingleLineString256Type getBillAddrName() {
		return billAddrName;
	}

	public void setBillAddrName(SingleLineString256Type billAddrName) {
		this.billAddrName = billAddrName;
	}

	public SingleLineString256Type getBillAddrStreet() {
		return billAddrStreet;
	}

	public void setBillAddrStreet(SingleLineString256Type billAddrStreet) {
		this.billAddrStreet = billAddrStreet;
	}

	public SingleLineString256Type getBillAddrPostalCode() {
		return billAddrPostalCode;
	}

	public void setBillAddrPostalCode(SingleLineString256Type billAddrPostalCode) {
		this.billAddrPostalCode = billAddrPostalCode;
	}

	public SingleLineString256Type getBillAddrCity() {
		return billAddrCity;
	}

	public void setBillAddrCity(SingleLineString256Type billAddrCity) {
		this.billAddrCity = billAddrCity;
	}

	public SingleLineString256Type getComment() {
		return comment;
	}

	public void setComment(SingleLineString256Type comment) {
		this.comment = comment;
	}

}
