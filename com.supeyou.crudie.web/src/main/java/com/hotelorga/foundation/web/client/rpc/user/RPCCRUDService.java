package com.hotelorga.foundation.web.client.rpc.user;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hotelorga.foundation.iface.dto.UserDTO;
import com.hotelorga.foundation.iface.dto.UserFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCUserCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<UserDTO, UserFetchQuery> {

}