package com.supeyou.actor.web.client.rpc.actinguser;

import com.google.gwt.core.client.GWT;
import com.supeyou.actor.iface.dto.ActingUserDTO;
import com.supeyou.actor.iface.dto.ActingUserFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<ActingUserDTO, ActingUserFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}