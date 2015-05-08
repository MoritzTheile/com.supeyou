package com.hotelorga.core.web.client.rpc.payer;

import com.google.gwt.core.client.GWT;
import com.hotelorga.core.iface.dto.PayerDTO;
import com.hotelorga.core.iface.dto.PayerFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<PayerDTO, PayerFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}