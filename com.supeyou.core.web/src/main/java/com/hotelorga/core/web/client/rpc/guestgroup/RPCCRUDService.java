package com.hotelorga.core.web.client.rpc.guestgroup;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hotelorga.core.iface.dto.GuestGroupDTO;
import com.hotelorga.core.iface.dto.GuestGroupFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCGuestGroupCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<GuestGroupDTO, GuestGroupFetchQuery> {

}