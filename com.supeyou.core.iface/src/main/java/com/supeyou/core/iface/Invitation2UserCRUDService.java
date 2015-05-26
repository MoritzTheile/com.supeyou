package com.supeyou.core.iface;

import com.supeyou.core.iface.dto.Invitation2UserFetchQuery;
import com.supeyou.core.iface.dto.Invitation2UserIDType;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.iface.dto.AbstrAssoDTO;
import com.supeyou.crudie.iface.dto.UserDTO;

public interface Invitation2UserCRUDService extends CRUDAssoService<InvitationDTO, UserDTO, Invitation2UserIDType, AbstrAssoDTO<InvitationDTO, UserDTO, Invitation2UserIDType>, Invitation2UserFetchQuery> {

}