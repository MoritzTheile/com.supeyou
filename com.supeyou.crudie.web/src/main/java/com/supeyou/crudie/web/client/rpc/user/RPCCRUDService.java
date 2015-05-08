package com.supeyou.crudie.web.client.rpc.user;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.iface.dto.UserFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCUserCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<UserDTO, UserFetchQuery> {

}