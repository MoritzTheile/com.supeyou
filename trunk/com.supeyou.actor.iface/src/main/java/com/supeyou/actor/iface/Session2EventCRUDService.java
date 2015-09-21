package com.supeyou.actor.iface;

import com.supeyou.actor.iface.dto.EventDTO;
import com.supeyou.actor.iface.dto.Session2EventDTO;
import com.supeyou.actor.iface.dto.Session2EventFetchQuery;
import com.supeyou.actor.iface.dto.Session2EventIDType;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.iface.dto.SessionIDType;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.dto.UserDTO;

public interface Session2EventCRUDService extends CRUDAssoService<SessionDTO, EventDTO, Session2EventIDType, Session2EventDTO, Session2EventFetchQuery> {

	Session2EventDTO addEventToSession(UserDTO actor, SessionIDType sessionID, EventDTO eventDTO) throws CRUDException;

}