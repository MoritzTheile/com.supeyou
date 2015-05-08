package com.hotelorga.core.iface.dto;

import java.io.Serializable;

import com.hotelorga.core.iface.datatype.enums.TITLE;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.iface.dto.AbstrDTO;

public class GuestDTO extends AbstrDTO<GuestIDType> implements Serializable {

	private static final long serialVersionUID = 8278328568L;

	private TITLE title = TITLE.UNDEFINED; // GUEST_TABLE_Title=Anrede
	private SingleLineString256Type firstname; // GUEST_TABLE_FirstName=Vorname
	private SingleLineString256Type lastname; // GUEST_TABLEO_Lastname=Name
	private DateType dateOfBirth; // GUEST_TABLE_DateOfBirth=Geburtsdatum
	private SingleLineString256Type passNr; // GUEST_TABLE_PassNr=Pass Nummer
	private DateType passDate; // GUEST_TABLE_PassDate=Pass Datum
	private SingleLineString256Type passAgency; // GUEST_TABLE_PassAgency=Pass Beh�rde
	private SingleLineString256Type addressStreet; // GUEST_TABLE_AddressStreet=Stra�e
	private SingleLineString256Type addressPostalCode; // GUEST_TABLE_AddressPostalCode=PLZ
	private SingleLineString256Type addressCity; // GUEST_TABLE_AddressOrt=Ort
	private SingleLineString256Type comment; // GUEST_TABLE_Comment=Anmerkung
	private SingleLineString256Type telefone;
	private SingleLineString256Type email;
	private SingleLineString256Type otherContact;

	public GuestDTO() {

	}

	public GuestDTO(GuestIDType id) {
		setId(id);
	}

	@Override
	public void setId(GuestIDType id) {
		super.setId(id);
	}

	@Override
	public GuestIDType getId() {
		return super.getId();
	}

	public GuestDTO(String id) {
		setId(new GuestIDType(id));
	}

	public TITLE getTitle() {
		return title;
	}

	public void setTitle(TITLE title) {
		this.title = title;
	}

	public SingleLineString256Type getFirstname() {
		return firstname;
	}

	public void setFirstname(SingleLineString256Type firstname) {
		this.firstname = firstname;
	}

	public SingleLineString256Type getLastname() {
		return lastname;
	}

	public void setLastname(SingleLineString256Type lastname) {
		this.lastname = lastname;
	}

	public DateType getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(DateType dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public SingleLineString256Type getPassNr() {
		return passNr;
	}

	public void setPassNr(SingleLineString256Type passNr) {
		this.passNr = passNr;
	}

	public DateType getPassDate() {
		return passDate;
	}

	public void setPassDate(DateType passDate) {
		this.passDate = passDate;
	}

	public SingleLineString256Type getPassAgency() {
		return passAgency;
	}

	public void setPassAgency(SingleLineString256Type passAgency) {
		this.passAgency = passAgency;
	}

	public SingleLineString256Type getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(SingleLineString256Type addressStreet) {
		this.addressStreet = addressStreet;
	}

	public SingleLineString256Type getAddressPostalCode() {
		return addressPostalCode;
	}

	public void setAddressPostalCode(SingleLineString256Type addressPostalCode) {
		this.addressPostalCode = addressPostalCode;
	}

	public SingleLineString256Type getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(SingleLineString256Type addressCity) {
		this.addressCity = addressCity;
	}

	public SingleLineString256Type getComment() {
		return comment;
	}

	public void setComment(SingleLineString256Type comment) {
		this.comment = comment;
	}

	public SingleLineString256Type getTelefone() {
		return telefone;
	}

	public void setTelefone(SingleLineString256Type telefone) {
		this.telefone = telefone;
	}

	public SingleLineString256Type getEmail() {
		return email;
	}

	public void setEmail(SingleLineString256Type email) {
		this.email = email;
	}

	public SingleLineString256Type getOtherContact() {
		return otherContact;
	}

	public void setOtherContact(SingleLineString256Type otherContact) {
		this.otherContact = otherContact;
	}

}
