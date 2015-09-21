package com.supeyou.actor.iface;

import com.supeyou.actor.iface.dto.EventDTO;
import com.supeyou.actor.iface.dto.EventFetchQuery;
import com.supeyou.crudie.iface.CRUDService;

public interface EventCRUDService extends CRUDService<EventDTO, EventFetchQuery> {

}