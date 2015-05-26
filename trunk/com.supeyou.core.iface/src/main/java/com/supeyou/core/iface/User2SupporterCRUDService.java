package com.supeyou.core.iface;

import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.User2SupporterFetchQuery;
import com.supeyou.core.iface.dto.User2SupporterIDType;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.iface.dto.AbstrAssoDTO;
import com.supeyou.crudie.iface.dto.UserDTO;

public interface User2SupporterCRUDService extends CRUDAssoService<UserDTO, SupporterDTO, User2SupporterIDType, AbstrAssoDTO<UserDTO, SupporterDTO, User2SupporterIDType>, User2SupporterFetchQuery> {

}