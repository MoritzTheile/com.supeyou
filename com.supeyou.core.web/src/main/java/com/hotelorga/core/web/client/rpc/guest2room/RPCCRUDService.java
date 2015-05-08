package com.hotelorga.core.web.client.rpc.guest2room;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hotelorga.core.iface.dto.Guest2RoomDTO;
import com.hotelorga.core.iface.dto.Guest2RoomFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCGuest2RoomCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<Guest2RoomDTO, Guest2RoomFetchQuery> {

}