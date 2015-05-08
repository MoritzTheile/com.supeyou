package com.supeyou.core.web.client.rpc.acceptance;

import com.google.gwt.core.client.GWT;
import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.AcceptanceFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<AcceptanceDTO, AcceptanceFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}