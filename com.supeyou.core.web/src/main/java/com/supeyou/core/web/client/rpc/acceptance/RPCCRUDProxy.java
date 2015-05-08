package com.supeyou.core.web.client.rpc.acceptance;

import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.AcceptanceFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public class RPCCRUDProxy extends RPCAbstractCRUDProxy<AcceptanceDTO, AcceptanceFetchQuery> {

	@Override
	public RPCAbstractCRUDServiceAsync<AcceptanceDTO, AcceptanceFetchQuery> getAbstractCRUDService() {
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
