package com.supeyou.actor.iface;

import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.iface.dto.SessionFetchQuery;
import com.supeyou.crudie.iface.CRUDService;

public interface SessionCRUDService extends CRUDService<SessionDTO, SessionFetchQuery> {

}