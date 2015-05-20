package com.supeyou.actor.web.client.rpc.actinguser;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.actor.iface.dto.ActingUserDTO;
import com.supeyou.actor.iface.dto.ActingUserFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCActingUserCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<ActingUserDTO, ActingUserFetchQuery> {

}