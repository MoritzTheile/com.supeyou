package com.hotelorga.foundation.web.client.rpc.user2group;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hotelorga.foundation.iface.dto.User2GroupDTO;
import com.hotelorga.foundation.iface.dto.User2GroupFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCUser2GroupCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<User2GroupDTO, User2GroupFetchQuery> {

}