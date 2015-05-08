package com.supeyou.core.impl.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.supeyou.core.iface.dto.AcceptanceIDType;
import com.supeyou.crudie.iface.datatype.types.AmountType;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.iface.datatype.types.PositivIntegerType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.impl.entity.AbstrEntity;

@Entity
@Table(name = "AcceptanceEntity")
public class AcceptanceEntity extends AbstrEntity<AcceptanceIDType> {

	private String comment;
	private String fromDate;
	private String toDate;
	private Integer acceptedCosts;
	private Integer fixOwnCosts;

	private Integer acceptedDays;

	@OneToMany(mappedBy = "a"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<Acceptance2PayerEntity> acceptance2payerCollection = new ArrayList<Acceptance2PayerEntity>();

	@OneToMany(mappedBy = "b"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<Guest2AcceptanceEntity> guest2acceptanceCollection = new ArrayList<Guest2AcceptanceEntity>();

	public Collection<Acceptance2PayerEntity> getAcceptance2payerCollection() {
		return acceptance2payerCollection;
	}

	public void setAcceptance2payerCollection(Collection<Acceptance2PayerEntity> acceptance2payerCollection) {
		this.acceptance2payerCollection = acceptance2payerCollection;
	}

	public Collection<Guest2AcceptanceEntity> getGuest2acceptanceCollection() {
		return guest2acceptanceCollection;
	}

	public void setGuest2acceptanceCollection(Collection<Guest2AcceptanceEntity> guest2acceptanceCollection) {
		this.guest2acceptanceCollection = guest2acceptanceCollection;
	}

	public SingleLineString256Type getComment() {

		if (comment == null) {
			return null;
		}
		return new SingleLineString256Type(comment);
	}

	public void setComment(SingleLineString256Type comment) {
		if (comment != null) {
			this.comment = comment.value();
		} else {
			this.comment = null;
		}
	}

	public DateType getFromDate() {
		if (fromDate != null) {
			return new DateType(fromDate);
		} else {
			return null;
		}
	}

	public void setFromDate(DateType date) {
		if (date != null) {
			this.fromDate = date.value();
		} else {
			this.fromDate = null;
		}
	}

	public DateType getToDate() {
		if (toDate != null) {
			return new DateType(toDate);
		} else {
			return null;
		}
	}

	public void setToDate(DateType date) {
		if (date != null) {
			this.toDate = date.value();
		} else {
			this.toDate = null;
		}
	}

	@Override
	public AcceptanceIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new AcceptanceIDType(dbid);
	}

	@Override
	public void setId(AcceptanceIDType typedId) {

		if (typedId != null) {
			dbid = typedId.value();
		} else {
			this.dbid = null;
		}

	}

	public AmountType getAcceptedCosts() {
		if (acceptedCosts == null) {
			return null;
		}
		return new AmountType(acceptedCosts);
	}

	public void setAcceptedCosts(AmountType value) {
		if (value != null) {
			this.acceptedCosts = value.value();
		} else {
			this.acceptedCosts = null;
		}
	}

	public AmountType getFixOwnCosts() {
		if (fixOwnCosts == null) {
			return null;
		}
		return new AmountType(fixOwnCosts);
	}

	public void setFixOwnCosts(AmountType value) {
		if (value != null) {
			this.fixOwnCosts = value.value();
		} else {
			this.fixOwnCosts = null;
		}
	}

	public PositivIntegerType getAcceptedDays() {
		if (acceptedDays == null) {
			return null;
		}
		return new PositivIntegerType(acceptedDays);
	}

	public void setAcceptedDays(PositivIntegerType value) {
		if (value != null) {
			this.acceptedDays = value.value();
		} else {
			this.acceptedDays = null;
		}
	}

}