package com.hotelorga.foundation.web.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface RPCServiceAsync {

	public static final RPCServiceAsync i = GWT.create(RPCService.class);

	void getVersion(AsyncCallback<String> callback);

}
