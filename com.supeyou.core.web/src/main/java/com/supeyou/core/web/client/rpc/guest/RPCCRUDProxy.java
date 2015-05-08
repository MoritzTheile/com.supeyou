package com.supeyou.core.web.client.rpc.guest;

import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.iface.dto.GuestFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public class RPCCRUDProxy extends RPCAbstractCRUDProxy<GuestDTO, GuestFetchQuery> {

	@Override
	public RPCAbstractCRUDServiceAsync<GuestDTO, GuestFetchQuery> getAbstractCRUDService() {
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
