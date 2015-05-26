package com.supeyou.core.web.client.rpc.supporter2invitation;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.core.iface.dto.Supporter2InvitationDTO;
import com.supeyou.core.iface.dto.Supporter2InvitationFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCSupporter2InvitationCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<Supporter2InvitationDTO, Supporter2InvitationFetchQuery> {

}