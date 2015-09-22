package com.supeyou.actor.web.client.rpc.session2event;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.actor.iface.dto.Session2EventDTO;
import com.supeyou.actor.iface.dto.Session2EventFetchQuery;
import com.supeyou.crudie.iface.datatype.types.PositivIntegerType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<Session2EventDTO, Session2EventFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

	void addEventToSession(SingleLineString256Type category, SingleLineString256Type action, SingleLineString256Type value, PositivIntegerType pageAgeSeconds, AsyncCallback<Void> callback);

}