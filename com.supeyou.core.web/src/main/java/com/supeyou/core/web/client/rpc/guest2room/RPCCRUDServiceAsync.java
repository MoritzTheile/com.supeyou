package com.supeyou.core.web.client.rpc.guest2room;

import com.google.gwt.core.client.GWT;
import com.supeyou.core.iface.dto.Guest2RoomDTO;
import com.supeyou.core.iface.dto.Guest2RoomFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<Guest2RoomDTO, Guest2RoomFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}