package com.supeyou.core.web.client.rpc.room;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.core.iface.dto.RoomDTO;
import com.supeyou.core.iface.dto.RoomFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCRoomCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<RoomDTO, RoomFetchQuery> {

}