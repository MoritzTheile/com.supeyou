package com.supeyou.core.iface.dto;

import com.supeyou.crudie.iface.datatype.types.AmountType;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.iface.dto.AbstrAssoDTO;

public class Guest2RoomDTO extends AbstrAssoDTO<GuestDTO, RoomDTO, Guest2RoomIDType> {

	private static final long serialVersionUID = 872447269L;

	private DateType fromDate;
	private DateType toDate;
	private AmountType monthlyCosts = new AmountType(51000); // 17 Euro * 30 Tage
	private AmountType ownCosts = new AmountType(0);

	public Guest2RoomDTO() {

	}

	public Guest2RoomDTO(Guest2RoomIDType id) {
		setId(id);
	}

	public Guest2RoomDTO(String id) {
		setId(new Guest2RoomIDType(id));
	}

	@Override
	public void setId(Guest2RoomIDType id) {
		super.setId(id);
	}

	@Override
	public Guest2RoomIDType getId() {
		return super.getId();
	}

	@Override
	public void setDtoA(GuestDTO dtoA) {
		super.setDtoA(dtoA);
	}

	@Override
	public GuestDTO getDtoA() {
		return super.getDtoA();
	}

	@Override
	public void setDtoB(RoomDTO dtoB) {
		super.setDtoB(dtoB);
	}

	@Override
	public RoomDTO getDtoB() {
		return super.getDtoB();
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

	public AmountType getMonthlyCosts() {
		return monthlyCosts;
	}

	public void setMonthlyCosts(AmountType monthlyCosts) {
		this.monthlyCosts = monthlyCosts;
	}

	public AmountType getOwnCosts() {
		return ownCosts;
	}

	public void setOwnCosts(AmountType ownCosts) {
		this.ownCosts = ownCosts;
	}

}
