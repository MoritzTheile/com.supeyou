package com.supeyou.core.web.client.rpc.hero;

import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.HeroFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public class RPCCRUDProxy extends RPCAbstractCRUDProxy<HeroDTO, HeroFetchQuery> {

	@Override
	public RPCAbstractCRUDServiceAsync<HeroDTO, HeroFetchQuery> getAbstractCRUDService() {
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
