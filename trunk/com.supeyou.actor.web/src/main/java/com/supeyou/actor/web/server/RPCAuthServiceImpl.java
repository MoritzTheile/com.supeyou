package com.supeyou.actor.web.server;

import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.supeyou.actor.web.client.rpc.RPCAuthService;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.iface.dto.UserFetchQuery;
import com.supeyou.crudie.impl.UserCRUDServiceImpl;
import com.supeyou.crudie.web.server.SessionStore;

/**
 * The server side implementation of the RPC service.
 */
@WebServlet("/auth")
public class RPCAuthServiceImpl extends RemoteServiceServlet implements
		RPCAuthService {

	private static final long serialVersionUID = 3458798535412038630L;

	private static transient Logger log = Logger.getLogger(RPCAuthServiceImpl.class.getName());

	public RPCAuthServiceImpl() {
		log.info("RPCAuthServiceImpl class is being initialized");
	}

	@Override
	public UserDTO authenticateActor(String loginName, String password) throws CRUDException {

		if (loginName.equals("MT")) {

			if (password.equals("mutzmutz")) {

				return sessionLogin(loginName);

			}

		}
		if (loginName.equals("MLH")) {

			if (password.equals("Emilia11")) {

				return sessionLogin(loginName);

			}

		}
		if (loginName.equals("DK")) { // Dagmar K�hler

			if (password.equals("35u8")) {

				return sessionLogin(loginName);

			}

		}

		return null;
	}

	@Override
	public void sessionLogout() {

		SessionStore.setActor(this.getThreadLocalRequest().getSession(), null);
		this.getThreadLocalRequest().getSession().invalidate();

	}

	private UserDTO sessionLogin(String loginId) throws CRUDException {

		UserDTO authenticatedActor = null;
		UserDTO initialAdmin = UserCRUDServiceImpl.i().getInitialAdmin();

		// TODO: Query for searching by email
		for (UserDTO userDTO : UserCRUDServiceImpl.i().fetchList(UserCRUDServiceImpl.i().getInitialAdmin(), new Page(), new UserFetchQuery())) {
			if (loginId.equals(userDTO.getLoginId().value())) {
				authenticatedActor = userDTO;
			}
		}

		if (authenticatedActor == null) {
			authenticatedActor = new UserDTO();
			authenticatedActor.setLoginId(new SingleLineString256Type(loginId));
			// authenticatedActor.setEmailAddress(new EmailAddressType(email));
			authenticatedActor = UserCRUDServiceImpl.i().updadd(initialAdmin, authenticatedActor);
		}

		SessionStore.setActor(this.getThreadLocalRequest().getSession(), authenticatedActor);

		return authenticatedActor;
	}

}
