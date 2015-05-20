package com.supeyou.crudie.web.server;

import javax.servlet.http.HttpSession;

import com.supeyou.crudie.iface.dto.UserDTO;

/**
 * This session store can be used by any MONA-web-component.
 * 
 * @author MoritzTheile
 * 
 */
public class SessionStore {

	// a secret key, only known to this class
	public static final String SESSIONKEY_FOR_AUTHENTICATED_ACTOR = "SESSIONKEY_FOR_AUTHENTICATED_ACTOR_e8t7w4t59";

	public static UserDTO getActor(final HttpSession httpSession) {

		Object userDTOObject = httpSession.getAttribute(SESSIONKEY_FOR_AUTHENTICATED_ACTOR);

		if (userDTOObject == null) {
			return null;
		}
		// we know that it has to be an CustomerUserDTO:
		return (UserDTO) userDTOObject;
	}

	public static void setActor(final HttpSession httpSession, UserDTO authenticateActor) {

		httpSession.setAttribute(SESSIONKEY_FOR_AUTHENTICATED_ACTOR, authenticateActor);

	}

}
