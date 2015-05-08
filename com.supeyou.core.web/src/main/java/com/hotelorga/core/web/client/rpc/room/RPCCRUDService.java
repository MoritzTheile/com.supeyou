package com.hotelorga.core.web.client.rpc.room;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hotelorga.core.iface.dto.RoomDTO;
import com.hotelorga.core.iface.dto.RoomFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCRoomCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<RoomDTO, RoomFetchQuery> {

}