package com.hotelorga.foundation.web.client.rpc.group;

import com.google.gwt.core.client.GWT;
import com.hotelorga.foundation.iface.dto.GroupDTO;
import com.hotelorga.foundation.iface.dto.GroupFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<GroupDTO, GroupFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}