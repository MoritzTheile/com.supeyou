package com.supeyou.core.iface;

import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.InvitationFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.UserDTO;

public interface InvitationCRUDService extends CRUDService<InvitationDTO, InvitationFetchQuery> {

	InvitationDTO get(UserDTO actorDTO, SupporterDTO heroDTO) throws CRUDException;

	InvitationDTO create(UserDTO actorDTO, SupporterDTO supporterDTO) throws CRUDException;

	void acceptInvitation(UserDTO actorDTO, InvitationDTO invitationDTO, SupporterDTO supporterDTO) throws CRUDException;

	void acceptInvitation(UserDTO actorDTO, UserDTO userDTO, SingleLineString256Type token) throws CRUDException;

}