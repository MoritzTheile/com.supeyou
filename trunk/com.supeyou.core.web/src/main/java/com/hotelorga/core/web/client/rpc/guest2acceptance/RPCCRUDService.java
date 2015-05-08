package com.hotelorga.core.web.client.rpc.guest2acceptance;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hotelorga.core.iface.dto.Guest2AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2AcceptanceFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCGuest2AcceptanceCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery> {

}