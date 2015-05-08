package com.supeyou.core.web.client.rpc.acceptance2payer;

import com.supeyou.core.iface.dto.Acceptance2PayerDTO;
import com.supeyou.core.iface.dto.Acceptance2PayerFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public class RPCCRUDProxy extends RPCAbstractCRUDProxy<Acceptance2PayerDTO, Acceptance2PayerFetchQuery> {

	@Override
	public RPCAbstractCRUDServiceAsync<Acceptance2PayerDTO, Acceptance2PayerFetchQuery> getAbstractCRUDService() {
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
