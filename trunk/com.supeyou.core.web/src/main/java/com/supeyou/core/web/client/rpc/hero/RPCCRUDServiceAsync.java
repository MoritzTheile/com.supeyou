package com.supeyou.core.web.client.rpc.hero;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.HeroFetchQuery;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<HeroDTO, HeroFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

	void getUser(HeroDTO heroDTO, AsyncCallback<UserDTO> callback);

}