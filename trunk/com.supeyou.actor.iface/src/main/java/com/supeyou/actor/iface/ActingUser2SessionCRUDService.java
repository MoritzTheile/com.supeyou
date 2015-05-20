package com.supeyou.actor.iface;

import com.supeyou.actor.iface.dto.ActingUser2SessionFetchQuery;
import com.supeyou.actor.iface.dto.ActingUser2SessionIDType;
import com.supeyou.actor.iface.dto.ActingUserDTO;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.iface.dto.AbstrAssoDTO;

public interface ActingUser2SessionCRUDService extends CRUDAssoService<ActingUserDTO, SessionDTO, ActingUser2SessionIDType, AbstrAssoDTO<ActingUserDTO, SessionDTO, ActingUser2SessionIDType>, ActingUser2SessionFetchQuery> {

}