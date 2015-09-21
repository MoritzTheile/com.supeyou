package com.supeyou.actor.web.client.rpc.session2event;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.actor.iface.dto.Session2EventDTO;
import com.supeyou.actor.iface.dto.Session2EventFetchQuery;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCSession2EventCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<Session2EventDTO, Session2EventFetchQuery> {

	void addEventToSession(String category, String action, String value) throws CRUDException;

}