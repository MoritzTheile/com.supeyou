package com.supeyou.core.web.client.rpc.acceptance;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.AcceptanceFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCAcceptanceCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<AcceptanceDTO, AcceptanceFetchQuery> {

}