package com.supeyou.actor.iface;

import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.iface.dto.SessionFetchQuery;
import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.iface.dto.UserDTO;

public interface SessionCRUDService extends CRUDService<SessionDTO, SessionFetchQuery> {

	SessionDTO getBySessionId(UserDTO actor, String sessionID);

	SessionDTO getNewestSessionOnBrowserButNotCurrent(UserDTO actor, String browserMark, SessionDTO sessionDTO);

}