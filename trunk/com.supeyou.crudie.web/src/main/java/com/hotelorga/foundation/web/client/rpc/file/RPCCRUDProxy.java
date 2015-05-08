package com.hotelorga.foundation.web.client.rpc.file;

import com.hotelorga.foundation.iface.dto.FileDTO;
import com.hotelorga.foundation.iface.dto.FileFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public class RPCCRUDProxy extends RPCAbstractCRUDProxy<FileDTO, FileFetchQuery> {

	@Override
	public RPCAbstractCRUDServiceAsync<FileDTO, FileFetchQuery> getAbstractCRUDService() {
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
