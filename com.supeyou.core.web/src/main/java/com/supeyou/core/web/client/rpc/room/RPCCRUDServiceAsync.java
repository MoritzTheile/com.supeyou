package com.supeyou.core.web.client.rpc.room;

import com.google.gwt.core.client.GWT;
import com.supeyou.core.iface.dto.RoomDTO;
import com.supeyou.core.iface.dto.RoomFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<RoomDTO, RoomFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}