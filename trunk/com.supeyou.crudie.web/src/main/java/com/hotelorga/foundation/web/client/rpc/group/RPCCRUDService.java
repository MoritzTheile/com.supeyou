package com.hotelorga.foundation.web.client.rpc.group;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hotelorga.foundation.iface.dto.GroupDTO;
import com.hotelorga.foundation.iface.dto.GroupFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCGroupCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<GroupDTO, GroupFetchQuery> {

}