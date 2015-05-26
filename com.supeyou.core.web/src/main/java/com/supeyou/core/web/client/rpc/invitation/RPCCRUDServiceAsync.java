package com.supeyou.core.web.client.rpc.invitation;

import com.google.gwt.core.client.GWT;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.InvitationFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<InvitationDTO, InvitationFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}