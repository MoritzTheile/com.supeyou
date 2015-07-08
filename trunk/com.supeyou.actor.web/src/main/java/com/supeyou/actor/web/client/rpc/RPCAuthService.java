package com.supeyou.actor.web.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.dto.UserDTO;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("../RPCAuthServiceImpl")
public interface RPCAuthService extends RemoteService {

	UserDTO authenticateActor(String loginName, String password) throws CRUDException;

	void sessionLogout();

	UserDTO getActorOfSession();

}
