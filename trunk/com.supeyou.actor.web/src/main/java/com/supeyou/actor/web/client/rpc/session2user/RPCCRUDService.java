package com.supeyou.actor.web.client.rpc.session2user;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.actor.iface.dto.Session2UserDTO;
import com.supeyou.actor.iface.dto.Session2UserFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCSession2UserCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<Session2UserDTO, Session2UserFetchQuery> {

}