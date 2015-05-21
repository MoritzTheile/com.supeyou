package com.supeyou.actor.web.client.rpc.session2user;

import com.google.gwt.core.client.GWT;
import com.supeyou.actor.iface.dto.Session2UserDTO;
import com.supeyou.actor.iface.dto.Session2UserFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<Session2UserDTO, Session2UserFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}