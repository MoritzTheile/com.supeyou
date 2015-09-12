package com.supeyou.crudie.web.client.model;

import com.supeyou.crudie.iface.common.HELPER;
import com.supeyou.crudie.iface.datatype.enums.ROLE;
import com.supeyou.crudie.iface.dto.UserDTO;

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

	public static boolean isAnonymous(UserDTO userDTO) {
		return userDTO.getLoginId().value().startsWith(HELPER.ANONYMMARKER) && userDTO.getName() == null;
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
