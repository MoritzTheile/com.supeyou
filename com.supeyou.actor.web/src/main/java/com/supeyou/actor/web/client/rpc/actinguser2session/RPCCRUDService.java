package com.supeyou.actor.web.client.rpc.actinguser2session;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.actor.iface.dto.ActingUser2SessionDTO;
import com.supeyou.actor.iface.dto.ActingUser2SessionFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCActingUser2SessionCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<ActingUser2SessionDTO, ActingUser2SessionFetchQuery> {

}