package com.hotelorga.foundation.web.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("../RPCServiceImpl")
public interface RPCService extends RemoteService {

	String getVersion();

}
