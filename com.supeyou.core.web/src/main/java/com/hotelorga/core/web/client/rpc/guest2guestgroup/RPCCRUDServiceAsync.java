package com.hotelorga.core.web.client.rpc.guest2guestgroup;

import com.google.gwt.core.client.GWT;
import com.hotelorga.core.iface.dto.Guest2GuestGroupDTO;
import com.hotelorga.core.iface.dto.Guest2GuestGroupFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<Guest2GuestGroupDTO, Guest2GuestGroupFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}