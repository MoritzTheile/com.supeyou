package com.hotelorga.foundation.web.client.rpc.user2group;

import com.hotelorga.foundation.iface.dto.User2GroupDTO;
import com.hotelorga.foundation.iface.dto.User2GroupFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public class RPCCRUDProxy extends RPCAbstractCRUDProxy<User2GroupDTO, User2GroupFetchQuery> {

	@Override
	public RPCAbstractCRUDServiceAsync<User2GroupDTO, User2GroupFetchQuery> getAbstractCRUDService() {
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
