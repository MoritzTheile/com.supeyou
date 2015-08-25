package com.supeyou.core.iface.dto;

import java.io.Serializable;

import com.supeyou.crudie.iface.datatype.types.AmountType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.AbstrDTO;

public class DonationDTO extends AbstrDTO<DonationIDType> implements Serializable {

	private static final long serialVersionUID = 824618946818L;

	private SingleLineString256Type itemName;
	private SingleLineString256Type itemNumber;
	private SingleLineString256Type paymentStatus;
	private AmountType paymentAmount;
	private SingleLineString256Type paymentCurrency;
	private SingleLineString256Type txnId;
	private SingleLineString256Type receiverEmail;
	private SingleLineString256Type payerEmail;
	private SingleLineString256Type response;
	private SingleLineString256Type requestParams;

	public DonationDTO() {

	}

	public DonationDTO(DonationIDType id) {
		setId(id);
	}

	public DonationDTO(String id) {
		setId(new DonationIDType(id));
	}

	public SingleLineString256Type getItemName() {
		return itemName;
	}

	public void setItemName(SingleLineString256Type itemName) {
		this.itemName = itemName;
	}

	public SingleLineString256Type getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(SingleLineString256Type itemNumber) {
		this.itemNumber = itemNumber;
	}

	public SingleLineString256Type getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(SingleLineString256Type paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public SingleLineString256Type getPaymentCurrency() {
		return paymentCurrency;
	}

	public void setPaymentCurrency(SingleLineString256Type paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}

	public SingleLineString256Type getTxnId() {
		return txnId;
	}

	public void setTxnId(SingleLineString256Type txnId) {
		this.txnId = txnId;
	}

	public SingleLineString256Type getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(SingleLineString256Type receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	public SingleLineString256Type getPayerEmail() {
		return payerEmail;
	}

	public void setPayerEmail(SingleLineString256Type payerEmail) {
		this.payerEmail = payerEmail;
	}

	public SingleLineString256Type getResponse() {
		return response;
	}

	public void setResponse(SingleLineString256Type response) {
		this.response = response;
	}

	public SingleLineString256Type getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(SingleLineString256Type requestParams) {
		this.requestParams = requestParams;
	}

	@Override
	public void setId(DonationIDType id) {
		super.setId(id);
	}

	@Override
	public DonationIDType getId() {
		return super.getId();
	}

	public AmountType getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(AmountType paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

}
