package com.hotelorga.core.web.client.rpc.acceptance2payer;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hotelorga.core.iface.dto.Acceptance2PayerDTO;
import com.hotelorga.core.iface.dto.Acceptance2PayerFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCAcceptance2PayerCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<Acceptance2PayerDTO, Acceptance2PayerFetchQuery> {

}