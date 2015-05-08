package com.supeyou.core.web.client.rpc.payer;

import com.google.gwt.core.client.GWT;
import com.supeyou.core.iface.dto.PayerDTO;
import com.supeyou.core.iface.dto.PayerFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<PayerDTO, PayerFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}