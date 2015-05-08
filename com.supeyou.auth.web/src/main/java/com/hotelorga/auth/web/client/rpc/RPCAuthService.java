package com.hotelorga.auth.web.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hotelorga.foundation.iface.datatype.CRUDException;
import com.hotelorga.foundation.iface.dto.UserDTO;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("../RPCAuthServiceImpl")
public interface RPCAuthService extends RemoteService {

	UserDTO authenticateActor(String loginName, String password) throws CRUDException;

	void sessionLogout();

}
