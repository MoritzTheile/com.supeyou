package com.supeyou.core.iface;

import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.Supporter2InvitationFetchQuery;
import com.supeyou.core.iface.dto.Supporter2InvitationIDType;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.iface.dto.AbstrAssoDTO;

public interface Supporter2InvitationCRUDService extends CRUDAssoService<SupporterDTO, InvitationDTO, Supporter2InvitationIDType, AbstrAssoDTO<SupporterDTO, InvitationDTO, Supporter2InvitationIDType>, Supporter2InvitationFetchQuery> {

}