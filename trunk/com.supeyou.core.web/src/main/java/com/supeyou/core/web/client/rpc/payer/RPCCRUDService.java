package com.supeyou.core.web.client.rpc.payer;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.core.iface.dto.PayerDTO;
import com.supeyou.core.iface.dto.PayerFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCPayerCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<PayerDTO, PayerFetchQuery> {

}