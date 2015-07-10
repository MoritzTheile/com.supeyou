package com.supeyou.crudie.impl.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.supeyou.crudie.iface.datatype.enums.LOCALE;
import com.supeyou.crudie.iface.datatype.enums.ROLE;
import com.supeyou.crudie.iface.datatype.types.AmountType;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.datatype.types.UserIDType;
import com.supeyou.crudie.iface.dto.UserFetchQuery;

@Entity
@Table(name = "UserEntity")
public class UserEntity extends AbstrEntity<UserIDType> {

	/**
	 * Login of User. Can be used additionally to the email.
	 */

	// @Index(name = "LOGINID_INDEX")
	@Column(name = UserFetchQuery.COLUMN_LOGINID, nullable = true, length = 256, unique = true)
	private String loginId;

	@Column(name = UserFetchQuery.COLUMN_AUTHTOKEN, nullable = true, length = 256, unique = true)
	private String authToken;

	@Column(name = UserFetchQuery.COLUMN_AMOUNT)
	private Integer amount;

	@Column(name = UserFetchQuery.COLUMN_BIRTHDATE)
	private String birthday;

	@Column(name = UserFetchQuery.COLUMN_ACTIVE)
	private Boolean active;

	@ElementCollection
	private List<ROLE> roles = new ArrayList<>();

	@Column(name = UserFetchQuery.COLUMN_LOCALE)
	private LOCALE locale;

	// no getter or setter, just for defining cascades
	@OneToMany(mappedBy = "a"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<User2GroupEntity> user2groupCollection = new ArrayList<User2GroupEntity>();

	public SingleLineString256Type getLoginId() {
		if (this.loginId != null) {
			return new SingleLineString256Type(loginId);
		} else {
			return null;
		}
	}

	public void setLoginId(SingleLineString256Type loginId) {
		if (loginId != null) {
			this.loginId = loginId.value();
		} else {
			this.loginId = null;
		}
	}

	public SingleLineString256Type getAuthToken() {
		if (this.authToken != null) {
			return new SingleLineString256Type(authToken);
		} else {
			return null;
		}
	}

	public void setAuthToken(SingleLineString256Type authToken) {
		if (authToken != null) {
			this.authToken = authToken.value();
		} else {
			this.authToken = null;
		}
	}

	public List<ROLE> getRoles() {
		return roles;
	}

	public void setRoles(List<ROLE> roles) {
		this.roles = roles;
	}

	public LOCALE getLocale() {
		return locale;
	}

	public void setLocale(LOCALE locale) {
		this.locale = locale;
	}

	@Override
	public UserIDType getId() {
		if (this.dbid != null) {
			return new UserIDType(dbid);
		} else {
			return null;
		}
	}

	@Override
	public void setId(UserIDType typedId) {
		if (typedId != null) {
			this.dbid = typedId.value();
		} else {
			this.dbid = null;
		}
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public AmountType getAmount() {
		if (amount != null) {
			return new AmountType(amount);
		} else {
			return null;
		}
	}

	public void setAmount(AmountType amount) {
		if (amount != null) {
			this.amount = amount.value();
		} else {
			this.amount = null;
		}
	}

	public DateType getBirthday() {
		if (birthday != null) {
			return new DateType(birthday);
		} else {
			return null;
		}
	}

	public void setBirthday(DateType birthday) {
		if (birthday != null) {
			this.birthday = birthday.value();
		} else {
			this.birthday = null;
		}
	}

}