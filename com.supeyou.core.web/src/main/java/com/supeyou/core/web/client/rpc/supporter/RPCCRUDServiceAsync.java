package com.supeyou.core.web.client.rpc.supporter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterFetchQuery;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<SupporterDTO, SupporterFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

	void get(UserDTO userDTO, HeroDTO heroDTO, AsyncCallback<SupporterDTO> callback);

	void getOrCreateRootSupporter(HeroDTO heroDTO, AsyncCallback<SupporterDTO> callback);

}