package com.supeyou.core.web.client.rpc.guest2room;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.core.iface.dto.Guest2RoomDTO;
import com.supeyou.core.iface.dto.Guest2RoomFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCGuest2RoomCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<Guest2RoomDTO, Guest2RoomFetchQuery> {

}