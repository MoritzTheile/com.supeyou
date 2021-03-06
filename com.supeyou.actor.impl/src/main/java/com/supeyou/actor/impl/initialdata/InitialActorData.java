package com.supeyou.actor.impl.initialdata;

import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.impl.SessionCRUDServiceImpl;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.UserCRUDServiceImpl;

public class InitialActorData {

	public UserDTO admin;

	public SessionDTO session1;
	public SessionDTO session2;

	private void init() throws CRUDException {

		admin = UserCRUDServiceImpl.i().getInitialAdmin();
		session1 = create("ajkajsdf", "78345919");
		session2 = create("erze8ak", "837862992");

	}

	private SessionDTO create(String httpSessionId, String browserMark) throws CRUDException {

		SessionDTO dto = new SessionDTO();
		dto.setHttpSessionId(new SingleLineString256Type(httpSessionId));
		dto.setBrowserMark(new SingleLineString256Type(browserMark));
		return SessionCRUDServiceImpl.i().updadd(admin, dto);

	}

	// --- SINGLETON ---
	private static InitialActorData instance = null;

	public static InitialActorData i() throws CRUDException {
		if (instance == null) {
			instance = new InitialActorData();
			instance.init();
		}
		return instance;
	}
}
