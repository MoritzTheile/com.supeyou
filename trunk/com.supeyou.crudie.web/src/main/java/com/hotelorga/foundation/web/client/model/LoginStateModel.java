package com.hotelorga.foundation.web.client.model;

import com.hotelorga.foundation.iface.datatype.enums.ROLE;
import com.hotelorga.foundation.iface.dto.UserDTO;

public class LoginStateModel extends AbstrObservable<Void> {

	private UserDTO loggedInUser = null;

	public UserDTO getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(UserDTO loggedInUser) {
		this.loggedInUser = loggedInUser;
		hasChanged();
	}

	public boolean userIsAdmin() {
		return getLoggedInUser() != null
				&& getLoggedInUser().getRoles().contains(ROLE.ADMIN);

	}

	// SINGLETON

	private LoginStateModel() {

	}

	private static LoginStateModel instance;

	public static LoginStateModel i() {
		if (instance == null) {
			instance = new LoginStateModel();
		}
		return instance;
	}

}
