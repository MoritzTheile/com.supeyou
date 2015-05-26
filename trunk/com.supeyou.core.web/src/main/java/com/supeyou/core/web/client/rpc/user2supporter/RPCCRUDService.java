package com.supeyou.core.web.client.rpc.user2supporter;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.core.iface.dto.User2SupporterDTO;
import com.supeyou.core.iface.dto.User2SupporterFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCUser2SupporterCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<User2SupporterDTO, User2SupporterFetchQuery> {

}