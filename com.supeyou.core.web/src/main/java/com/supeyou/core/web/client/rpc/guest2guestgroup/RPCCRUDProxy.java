package com.supeyou.core.web.client.rpc.guest2guestgroup;

import com.supeyou.core.iface.dto.Guest2GuestGroupDTO;
import com.supeyou.core.iface.dto.Guest2GuestGroupFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public class RPCCRUDProxy extends RPCAbstractCRUDProxy<Guest2GuestGroupDTO, Guest2GuestGroupFetchQuery> {

	@Override
	public RPCAbstractCRUDServiceAsync<Guest2GuestGroupDTO, Guest2GuestGroupFetchQuery> getAbstractCRUDService() {
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
