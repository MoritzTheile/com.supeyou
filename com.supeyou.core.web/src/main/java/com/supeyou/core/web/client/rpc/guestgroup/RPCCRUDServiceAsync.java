package com.supeyou.core.web.client.rpc.guestgroup;

import com.google.gwt.core.client.GWT;
import com.supeyou.core.iface.dto.GuestGroupDTO;
import com.supeyou.core.iface.dto.GuestGroupFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<GuestGroupDTO, GuestGroupFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}