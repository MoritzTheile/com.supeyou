package com.supeyou.core.web.client.rpc.invitation2supporter;

import com.google.gwt.core.client.GWT;
import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<Invitation2SupporterDTO, Invitation2SupporterFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}