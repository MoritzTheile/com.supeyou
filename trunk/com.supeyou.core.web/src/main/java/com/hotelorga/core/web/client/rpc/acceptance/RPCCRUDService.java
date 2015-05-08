package com.hotelorga.core.web.client.rpc.acceptance;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hotelorga.core.iface.dto.AcceptanceDTO;
import com.hotelorga.core.iface.dto.AcceptanceFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCAcceptanceCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<AcceptanceDTO, AcceptanceFetchQuery> {

}