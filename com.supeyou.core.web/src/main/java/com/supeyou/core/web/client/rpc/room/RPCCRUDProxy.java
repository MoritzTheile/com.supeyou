package com.supeyou.core.web.client.rpc.room;

import com.supeyou.core.iface.dto.RoomDTO;
import com.supeyou.core.iface.dto.RoomFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public class RPCCRUDProxy extends RPCAbstractCRUDProxy<RoomDTO, RoomFetchQuery> {

	@Override
	public RPCAbstractCRUDServiceAsync<RoomDTO, RoomFetchQuery> getAbstractCRUDService() {
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
