package com.hotelorga.foundation.web.client.rpc.user2group;

import com.google.gwt.core.client.GWT;
import com.hotelorga.foundation.iface.dto.User2GroupDTO;
import com.hotelorga.foundation.iface.dto.User2GroupFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<User2GroupDTO, User2GroupFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}