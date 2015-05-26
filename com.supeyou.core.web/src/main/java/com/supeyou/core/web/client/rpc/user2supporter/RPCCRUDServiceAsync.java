package com.supeyou.core.web.client.rpc.user2supporter;

import com.google.gwt.core.client.GWT;
import com.supeyou.core.iface.dto.User2SupporterDTO;
import com.supeyou.core.iface.dto.User2SupporterFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<User2SupporterDTO, User2SupporterFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}