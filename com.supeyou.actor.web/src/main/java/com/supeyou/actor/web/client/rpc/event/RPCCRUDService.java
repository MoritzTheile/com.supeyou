package com.supeyou.actor.web.client.rpc.event;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.actor.iface.dto.EventDTO;
import com.supeyou.actor.iface.dto.EventFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCEventCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<EventDTO, EventFetchQuery> {

}