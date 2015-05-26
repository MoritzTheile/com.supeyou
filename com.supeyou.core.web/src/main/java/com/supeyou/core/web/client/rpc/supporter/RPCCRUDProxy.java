package com.supeyou.core.web.client.rpc.supporter;

import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public class RPCCRUDProxy extends RPCAbstractCRUDProxy<SupporterDTO, SupporterFetchQuery> {

	@Override
	public RPCAbstractCRUDServiceAsync<SupporterDTO, SupporterFetchQuery> getAbstractCRUDService() {
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
