package com.supeyou.core.web.client.rpc.donation;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.core.iface.dto.DonationDTO;
import com.supeyou.core.iface.dto.DonationFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCDonationCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<DonationDTO, DonationFetchQuery> {

}