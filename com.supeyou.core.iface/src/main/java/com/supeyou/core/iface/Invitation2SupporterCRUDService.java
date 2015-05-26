package com.supeyou.core.iface;

import com.supeyou.core.iface.dto.Invitation2SupporterFetchQuery;
import com.supeyou.core.iface.dto.Invitation2SupporterIDType;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.iface.dto.AbstrAssoDTO;

public interface Invitation2SupporterCRUDService extends CRUDAssoService<InvitationDTO, SupporterDTO, Invitation2SupporterIDType, AbstrAssoDTO<InvitationDTO, SupporterDTO, Invitation2SupporterIDType>, Invitation2SupporterFetchQuery> {

}