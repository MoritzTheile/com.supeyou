package com.hotelorga.core.web.client.rpc.guest2guestgroup;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hotelorga.core.iface.dto.Guest2GuestGroupDTO;
import com.hotelorga.core.iface.dto.Guest2GuestGroupFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCGuest2GuestGroupCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<Guest2GuestGroupDTO, Guest2GuestGroupFetchQuery> {

}