package com.supeyou.auth.web.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.crudie.iface.dto.UserDTO;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface RPCAuthServiceAsync {

	public static final RPCAuthServiceAsync i = GWT.create(RPCAuthService.class);

	void authenticateActor(String loginName, String password, AsyncCallback<UserDTO> callback);

	void sessionLogout(AsyncCallback<Void> callback);

}
