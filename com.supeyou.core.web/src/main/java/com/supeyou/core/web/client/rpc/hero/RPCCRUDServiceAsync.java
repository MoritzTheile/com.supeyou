package com.supeyou.core.web.client.rpc.hero;

import com.google.gwt.core.client.GWT;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.HeroFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<HeroDTO, HeroFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}