package com.supeyou.core.web.client.rpc.donation;

import com.google.gwt.core.client.GWT;
import com.supeyou.core.iface.dto.DonationDTO;
import com.supeyou.core.iface.dto.DonationFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<DonationDTO, DonationFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}