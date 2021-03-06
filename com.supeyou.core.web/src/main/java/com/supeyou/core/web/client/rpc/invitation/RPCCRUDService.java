package com.supeyou.core.web.client.rpc.invitation;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.InvitationFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCInvitationCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<InvitationDTO, InvitationFetchQuery> {

	InvitationDTO create(SupporterDTO supporterDTO) throws CRUDException;

	SupporterDTO followInvitation(UserDTO userDTO, SingleLineString256Type token) throws CRUDException;

}