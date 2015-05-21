package com.supeyou.actor.iface;

import com.supeyou.actor.iface.dto.Session2UserFetchQuery;
import com.supeyou.actor.iface.dto.Session2UserIDType;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.iface.dto.AbstrAssoDTO;
import com.supeyou.crudie.iface.dto.UserDTO;

public interface Session2UserCRUDService extends CRUDAssoService<SessionDTO, UserDTO, Session2UserIDType, AbstrAssoDTO<SessionDTO, UserDTO, Session2UserIDType>, Session2UserFetchQuery> {

}