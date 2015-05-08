package com.supeyou.core.impl.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.supeyou.core.iface.datatype.enums.TITLE;
import com.supeyou.core.iface.dto.GuestIDType;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.impl.entity.AbstrEntity;

@Entity
@Table(name = "GuestEntity")
public class GuestEntity extends AbstrEntity<GuestIDType> {

	private TITLE title = TITLE.UNDEFINED; // GUEST_TABLE_Title=Anrede
	private String firstname; // GUEST_TABLE_FirstName=Vorname
	private String lastname; // GUEST_TABLEO_Lastname=Name
	private String dateOfBirth; // GUEST_TABLE_DateOfBirth=Geburtsdatum
	private String passNr; // GUEST_TABLE_PassNr=Pass Nummer
	private String passDate; // GUEST_TABLE_PassDate=Pass Datum
	private String passAgency; // GUEST_TABLE_PassAgency=Pass Behörde
	private String addressStreet; // GUEST_TABLE_AddressStreet=Straße
	private String addressPostalCode; // GUEST_TABLE_AddressPostalCode=PLZ
	private String addressCity; // GUEST_TABLE_AddressOrt=Ort
	private String comment; // GUEST_TABLE_Comment=Anmerkung
	private String telefone;
	private String email;
	private String otherContact;

	// no getter or setter, just for defining cascades
	@OneToMany(mappedBy = "a"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<Guest2RoomEntity> guest2roomCollection = new ArrayList<Guest2RoomEntity>();

	@OneToMany(mappedBy = "a"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<Guest2GuestGroupEntity> guest2guestGroupCollection = new ArrayList<Guest2GuestGroupEntity>();

	// no getter or setter, just for defining cascades
	@OneToMany(mappedBy = "a"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<Guest2AcceptanceEntity> guest2acceptanceCollection = new ArrayList<Guest2AcceptanceEntity>();

	public Collection<Guest2GuestGroupEntity> getGuest2guestGroupCollection() {
		return guest2guestGroupCollection;
	}

	public void setGuest2guestGroupCollection(Collection<Guest2GuestGroupEntity> guest2guestGroupCollection) {
		this.guest2guestGroupCollection = guest2guestGroupCollection;
	}

	public TITLE getTitle() {
		return title;
	}

	public void setTitle(TITLE title) {
		this.title = title;
	}

	public SingleLineString256Type getFirstname() {
		if (firstname == null) {
			return null;
		}
		return new SingleLineString256Type(firstname);
	}

	public void setFirstname(SingleLineString256Type firstname) {
		if (firstname != null) {
			this.firstname = firstname.value();
		} else {
			this.firstname = null;
		}
	}

	public SingleLineString256Type getLastname() {
		if (lastname == null) {
			return null;
		}
		return new SingleLineString256Type(lastname);
	}

	public void setLastname(SingleLineString256Type value) {
		if (value != null) {
			this.lastname = value.value();
		} else {
			this.lastname = null;
		}
	}

	public DateType getDateOfBirth() {
		if (dateOfBirth == null) {
			return null;
		}
		return new DateType(dateOfBirth);

	}

	public void setDateOfBirth(DateType date) {
		if (date != null) {
			this.dateOfBirth = date.value();
		} else {
			this.dateOfBirth = null;
		}
	}

	public SingleLineString256Type getPassNr() {
		if (passNr == null) {
			return null;
		}
		return new SingleLineString256Type(passNr);
	}

	public void setPassNr(SingleLineString256Type value) {
		if (value != null) {
			this.passNr = value.value();
		} else {
			this.passNr = null;
		}
	}

	public DateType getPassDate() {
		if (passDate == null) {
			return null;
		}
		return new DateType(passDate);

	}

	public void setPassDate(DateType date) {
		if (date != null) {
			this.passDate = date.value();
		} else {
			this.passDate = null;
		}
	}

	public SingleLineString256Type getPassAgency() {
		if (passAgency == null) {
			return null;
		}
		return new SingleLineString256Type(passAgency);
	}

	public void setPassAgency(SingleLineString256Type value) {
		if (value != null) {
			this.passAgency = value.value();
		} else {
			this.passAgency = null;
		}
	}

	public SingleLineString256Type getAddressStreet() {
		if (addressStreet == null) {
			return null;
		}
		return new SingleLineString256Type(addressStreet);
	}

	public void setAddressStreet(SingleLineString256Type value) {
		if (value != null) {
			this.addressStreet = value.value();
		} else {
			this.addressStreet = null;
		}
	}

	public SingleLineString256Type getAddressPostalCode() {
		if (addressPostalCode == null) {
			return null;
		}
		return new SingleLineString256Type(addressPostalCode);
	}

	public void setAddressPostalCode(SingleLineString256Type value) {
		if (value != null) {
			this.addressPostalCode = value.value();
		} else {
			this.addressPostalCode = null;
		}
	}

	public SingleLineString256Type getAddressCity() {
		if (addressCity == null) {
			return null;
		}
		return new SingleLineString256Type(addressCity);
	}

	public void setAddressCity(SingleLineString256Type value) {
		if (value != null) {
			this.addressCity = value.value();
		} else {
			this.addressCity = null;
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

	public SingleLineString256Type getTelefone() {
		if (telefone == null) {
			return null;
		}
		return new SingleLineString256Type(telefone);
	}

	public void setTelefone(SingleLineString256Type value) {
		if (value != null) {
			this.telefone = value.value();
		} else {
			this.telefone = null;
		}
	}

	public SingleLineString256Type getEmail() {
		if (email == null) {
			return null;
		}
		return new SingleLineString256Type(email);
	}

	public void setEmail(SingleLineString256Type value) {
		if (value != null) {
			this.email = value.value();
		} else {
			this.email = null;
		}
	}

	public SingleLineString256Type getOtherContact() {
		if (otherContact == null) {
			return null;
		}
		return new SingleLineString256Type(otherContact);
	}

	public void setOtherContact(SingleLineString256Type value) {
		if (value != null) {
			this.otherContact = value.value();
		} else {
			this.otherContact = null;
		}
	}

	@Override
	public GuestIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new GuestIDType(dbid);
	}

	@Override
	public void setId(GuestIDType typedId) {
		if (typedId != null) {
			dbid = typedId.value();
		} else {
			this.dbid = null;
		}

	}

}