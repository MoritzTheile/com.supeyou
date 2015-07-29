package com.supeyou.core.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.supeyou.core.iface.dto.DonationIDType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.impl.entity.AbstrEntity;

@Entity
@Table(name = "DonationEntity")
public class DonationEntity extends AbstrEntity<DonationIDType> {

	private String itemName;
	private String itemNumber;
	private String paymentStatus;
	private String paymentAmount;
	private String paymentCurrency;
	private String txnId;
	private String receiverEmail;
	private String payerEmail;
	private String response;
	private String requestParams;
	private String error;

	public void setItemName(SingleLineString256Type itemName) {
		if (itemName != null) {
			this.itemName = itemName.value();
		} else {
			this.itemName = null;
		}
	}

	public SingleLineString256Type getItemName() {
		if (itemName == null) {
			return null;
		}
		return new SingleLineString256Type(itemName);
	}

	public SingleLineString256Type getItemNumber() {
		if (itemNumber == null) {
			return null;
		}
		return new SingleLineString256Type(itemNumber);
	}

	public void setItemNumber(SingleLineString256Type itemNumber) {
		if (itemNumber != null) {
			this.itemNumber = itemNumber.value();
		} else {
			this.itemNumber = null;
		}
	}

	public SingleLineString256Type getPaymentStatus() {
		if (paymentStatus == null) {
			return null;
		}
		return new SingleLineString256Type(paymentStatus);
	}

	public void setPaymentStatus(SingleLineString256Type paymentStatus) {
		if (paymentStatus != null) {
			this.paymentStatus = paymentStatus.value();
		} else {
			this.paymentStatus = null;
		}
	}

	public SingleLineString256Type getPaymentAmount() {
		if (paymentAmount == null) {
			return null;
		}
		return new SingleLineString256Type(paymentAmount);
	}

	public void setPaymentAmount(SingleLineString256Type paymentAmount) {
		if (paymentAmount != null) {
			this.paymentAmount = paymentAmount.value();
		} else {
			this.paymentAmount = null;
		}
	}

	public SingleLineString256Type getPaymentCurrency() {
		if (paymentCurrency == null) {
			return null;
		}
		return new SingleLineString256Type(paymentCurrency);
	}

	public void setPaymentCurrency(SingleLineString256Type paymentCurrency) {
		if (paymentCurrency != null) {
			this.paymentCurrency = paymentCurrency.value();
		} else {
			this.paymentCurrency = null;
		}
	}

	public SingleLineString256Type getTxnId() {
		if (txnId == null) {
			return null;
		}
		return new SingleLineString256Type(txnId);
	}

	public void setTxnId(SingleLineString256Type txnId) {
		if (txnId != null) {
			this.txnId = txnId.value();
		} else {
			this.txnId = null;
		}
	}

	public SingleLineString256Type getReceiverEmail() {
		if (receiverEmail == null) {
			return null;
		}
		return new SingleLineString256Type(receiverEmail);
	}

	public void setReceiverEmail(SingleLineString256Type receiverEmail) {
		if (receiverEmail != null) {
			this.receiverEmail = receiverEmail.value();
		} else {
			this.receiverEmail = null;
		}
	}

	public SingleLineString256Type getPayerEmail() {
		if (payerEmail == null) {
			return null;
		}
		return new SingleLineString256Type(payerEmail);
	}

	public void setPayerEmail(SingleLineString256Type payerEmail) {
		if (payerEmail != null) {
			this.payerEmail = payerEmail.value();
		} else {
			this.payerEmail = null;
		}
	}

	public SingleLineString256Type getResponse() {
		if (response == null) {
			return null;
		}
		return new SingleLineString256Type(response);
	}

	public void setResponse(SingleLineString256Type response) {
		if (response != null) {
			this.response = response.value();
		} else {
			this.response = null;
		}
	}

	public SingleLineString256Type getRequestParams() {
		if (requestParams == null) {
			return null;
		}
		return new SingleLineString256Type(requestParams);
	}

	public void setRequestParams(SingleLineString256Type requestParams) {
		if (requestParams != null) {
			this.requestParams = requestParams.value();
		} else {
			this.requestParams = null;
		}
	}

	public SingleLineString256Type getError() {
		if (error == null) {
			return null;
		}
		return new SingleLineString256Type(error);
	}

	public void setError(SingleLineString256Type error) {
		if (error != null) {
			this.error = error.value();
		} else {
			this.error = null;
		}
	}

	@Override
	public DonationIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new DonationIDType(dbid);
	}

	@Override
	public void setId(DonationIDType typedId) {
		if (typedId != null) {
			dbid = typedId.value();
		} else {
			this.dbid = null;
		}

	}
}