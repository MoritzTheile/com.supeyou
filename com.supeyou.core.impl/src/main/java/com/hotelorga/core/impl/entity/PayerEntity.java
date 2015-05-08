package com.hotelorga.core.impl.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hotelorga.core.iface.dto.PayerIDType;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.impl.entity.AbstrEntity;

@Entity
@Table(name = "PayerEntity")
public class PayerEntity extends AbstrEntity<PayerIDType> {

	/**
	 * Login of User. Can be used additionally to the email.
	 */
	// @Index(name = "Payer_NAME_INDEX")
	@Column(name = "NAME", nullable = true, length = 256, unique = true)
	private String name;

	private String confideTo;
	private String billAddrName;
	private String billAddrStreet;
	private String billAddrPostalCode;
	private String billAddrCity;
	private String comment; // GUEST_TABLE_Comment=Anmerkung

	// no getter or setter, just for defining cascades
	@OneToMany(mappedBy = "b"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<Acceptance2PayerEntity> acceptance2payerCollection = new ArrayList<Acceptance2PayerEntity>();

	public Collection<Acceptance2PayerEntity> getAcceptance2payerCollection() {
		return acceptance2payerCollection;
	}

	public void setAcceptance2payerCollection(Collection<Acceptance2PayerEntity> acceptance2payerCollection) {
		this.acceptance2payerCollection = acceptance2payerCollection;
	}

	public SingleLineString256Type getConfideTo() {
		if (confideTo == null) {
			return null;
		}
		return new SingleLineString256Type(confideTo);
	}

	public void setConfideTo(SingleLineString256Type value) {
		if (value != null) {
			this.confideTo = value.value();
		} else {
			this.confideTo = null;
		}
	}

	public SingleLineString256Type getBillAddrName() {
		if (billAddrName == null) {
			return null;
		}
		return new SingleLineString256Type(billAddrName);
	}

	public void setBillAddrName(SingleLineString256Type value) {
		if (value != null) {
			this.billAddrName = value.value();
		} else {
			this.billAddrName = null;
		}
	}

	public SingleLineString256Type getBillAddrStreet() {
		if (billAddrStreet == null) {
			return null;
		}
		return new SingleLineString256Type(billAddrStreet);
	}

	public void setBillAddrStreet(SingleLineString256Type value) {
		if (value != null) {
			this.billAddrStreet = value.value();
		} else {
			this.billAddrStreet = null;
		}
	}

	public SingleLineString256Type getBillAddrPostalCode() {
		if (billAddrPostalCode == null) {
			return null;
		}
		return new SingleLineString256Type(billAddrPostalCode);
	}

	public void setBillAddrPostalCode(SingleLineString256Type value) {
		if (value != null) {
			this.billAddrPostalCode = value.value();
		} else {
			this.billAddrPostalCode = null;
		}
	}

	public SingleLineString256Type getBillAddrCity() {
		if (billAddrCity == null) {
			return null;
		}
		return new SingleLineString256Type(billAddrCity);
	}

	public void setBillAddrCity(SingleLineString256Type value) {
		if (value != null) {
			this.billAddrCity = value.value();
		} else {
			this.billAddrCity = null;
		}
	}

	public SingleLineString256Type getComment() {
		if (comment == null) {
			return null;
		}
		return new SingleLineString256Type(comment);
	}

	public void setComment(SingleLineString256Type value) {
		if (value != null) {
			this.comment = value.value();
		} else {
			this.comment = null;
		}
	}

	// // no getter or setter, just for defining cascades
	// @OneToMany(mappedBy = "b"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	// private Collection<User2GroupEntity> user2groupCollection = new ArrayList<User2GroupEntity>();

	public SingleLineString256Type getName() {
		if (name == null) {
			return null;
		}
		return new SingleLineString256Type(name);
	}

	public void setName(SingleLineString256Type name) {
		if (name != null) {
			this.name = name.value();
		} else {
			this.name = null;
		}
	}

	@Override
	public PayerIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new PayerIDType(dbid);
	}

	@Override
	public void setId(PayerIDType typedId) {
		if (typedId != null) {
			dbid = typedId.value();
		} else {
			this.dbid = null;
		}

	}

}