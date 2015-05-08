package com.hotelorga.foundation.iface.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hotelorga.foundation.iface.datatype.enums.LOCALE;
import com.hotelorga.foundation.iface.datatype.enums.ROLE;
import com.hotelorga.foundation.iface.datatype.types.AmountType;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.iface.datatype.types.EmailAddressType;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.iface.datatype.types.UserIDType;

public class UserDTO extends AbstrDTO<UserIDType> implements Serializable {

	private static final long serialVersionUID = 242457943L;

	private EmailAddressType emailAddress;

	private SingleLineString256Type loginId;

	private List<ROLE> roles = new ArrayList<ROLE>();

	private LOCALE locale;

	private AmountType amount;

	private DateType birthday;

	private Boolean active;

	public UserDTO() {
	}

	public UserDTO(UserIDType id) {
		setId(id);
	}

	public UserDTO(String id) {
		setId(new UserIDType(id));
	}

	@Override
	public UserIDType getId() {
		return super.getId();
	}

	@Override
	public void setId(UserIDType id) {
		super.setId(id);
	}

	public SingleLineString256Type getLoginId() {
		return loginId;
	}

	public void setLoginId(SingleLineString256Type name) {
		this.loginId = name;
	}

	public EmailAddressType getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(EmailAddressType emailAddress) {
		this.emailAddress = emailAddress;
	}

	public List<ROLE> getRoles() {
		return Collections.unmodifiableList(roles);
	}

	public void setRoles(List<ROLE> roles) {
		this.roles = new ArrayList<>(roles);
	}

	public void addRole(ROLE role) {
		roles.add(role);
	}

	public void removeRole(ROLE role) {
		roles.remove(role);
	}

	public LOCALE getLocale() {
		return locale;
	}

	public void setLocale(LOCALE locale) {
		this.locale = locale;
	}

	public void clearRoles() {
		roles.clear();
	}

	public AmountType getAmount() {
		return amount;
	}

	public void setAmount(AmountType amount) {
		this.amount = amount;
	}

	public DateType getBirthday() {
		return birthday;
	}

	public void setBirthday(DateType birthday) {
		this.birthday = birthday;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
