package com.supeyou.core.web.client.rpc.guest2acceptance;

import com.supeyou.core.iface.dto.Guest2AcceptanceDTO;
import com.supeyou.core.iface.dto.Guest2AcceptanceFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public class RPCCRUDProxy extends RPCAbstractCRUDProxy<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery> {

	@Override
	public RPCAbstractCRUDServiceAsync<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery> getAbstractCRUDService() {
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
