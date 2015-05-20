package com.supeyou.actor.web.client.rpc.session;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.iface.dto.SessionFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCSessionCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<SessionDTO, SessionFetchQuery> {

}