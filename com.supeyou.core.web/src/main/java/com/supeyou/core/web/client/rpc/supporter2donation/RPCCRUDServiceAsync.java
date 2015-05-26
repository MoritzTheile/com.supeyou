package com.supeyou.core.web.client.rpc.supporter2donation;

import com.google.gwt.core.client.GWT;
import com.supeyou.core.iface.dto.Supporter2DonationDTO;
import com.supeyou.core.iface.dto.Supporter2DonationFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<Supporter2DonationDTO, Supporter2DonationFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

}