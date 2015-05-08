package com.supeyou.core.web.client.rpc.payer;

import com.supeyou.core.iface.dto.PayerDTO;
import com.supeyou.core.iface.dto.PayerFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public class RPCCRUDProxy extends RPCAbstractCRUDProxy<PayerDTO, PayerFetchQuery> {

	@Override
	public RPCAbstractCRUDServiceAsync<PayerDTO, PayerFetchQuery> getAbstractCRUDService() {
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
