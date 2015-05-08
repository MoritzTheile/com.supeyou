package com.supeyou.core.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.supeyou.core.iface.dto.Guest2RoomIDType;
import com.supeyou.crudie.iface.datatype.types.AmountType;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.impl.entity.AbstrManyToManyAsso;

@Entity
// multiple assos are possible so no unique constraint over A and B.
// no overlapping in time shall be enforced in logic
@Table(name = "Guest2RoomAsso")
public class Guest2RoomEntity extends AbstrManyToManyAsso<GuestEntity, RoomEntity, Guest2RoomIDType> {

	private String fromDate;
	private String toDate;
	private Integer monthlyCosts = 0;
	private Integer ownCosts = 0;

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

	public AmountType getOwnCosts() {
		if (ownCosts == null) {
			return null;
		}
		return new AmountType(ownCosts);
	}

	public void setOwnCosts(AmountType value) {
		if (value != null) {
			this.ownCosts = value.value();
		} else {
			this.ownCosts = null;
		}
	}

	public AmountType getMonthlyCosts() {
		if (monthlyCosts == null) {
			return null;
		}
		return new AmountType(monthlyCosts);
	}

	public void setMonthlyCosts(AmountType value) {
		if (value != null) {
			this.monthlyCosts = value.value();
		} else {
			this.monthlyCosts = null;
		}
	}

	@Override
	public Guest2RoomIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new Guest2RoomIDType(dbid);
	}

	@Override
	public void setId(Guest2RoomIDType typedId) {
		if (typedId != null) {
			dbid = typedId.value();
		} else {
			dbid = null;
		}

	}

}
