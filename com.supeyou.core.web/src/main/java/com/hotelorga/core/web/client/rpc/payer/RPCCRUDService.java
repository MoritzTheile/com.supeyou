package com.hotelorga.core.web.client.rpc.payer;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hotelorga.core.iface.dto.PayerDTO;
import com.hotelorga.core.iface.dto.PayerFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCPayerCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<PayerDTO, PayerFetchQuery> {

}