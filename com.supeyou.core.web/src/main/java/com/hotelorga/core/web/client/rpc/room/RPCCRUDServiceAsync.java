package com.hotelorga.core.web.client.rpc.room;

import com.google.gwt.core.client.GWT;
import com.hotelorga.core.iface.dto.RoomDTO;
import com.hotelorga.core.iface.dto.RoomFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<RoomDTO, RoomFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}