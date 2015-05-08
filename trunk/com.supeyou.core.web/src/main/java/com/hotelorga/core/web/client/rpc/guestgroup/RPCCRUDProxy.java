package com.hotelorga.core.web.client.rpc.guestgroup;

import com.hotelorga.core.iface.dto.GuestGroupDTO;
import com.hotelorga.core.iface.dto.GuestGroupFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public class RPCCRUDProxy extends RPCAbstractCRUDProxy<GuestGroupDTO, GuestGroupFetchQuery> {

	@Override
	public RPCAbstractCRUDServiceAsync<GuestGroupDTO, GuestGroupFetchQuery> getAbstractCRUDService() {
		return RPCCRUDServiceAsync.i;
	}

	// SINGLETON
	private static RPCCRUDProxy instance;

	private RPCCRUDProxy() {

	}

	public static RPCCRUDProxy i() {
		if (instance == null) {
			instance = new RPCCRUDProxy();
		}
		return instance;
	}
}
