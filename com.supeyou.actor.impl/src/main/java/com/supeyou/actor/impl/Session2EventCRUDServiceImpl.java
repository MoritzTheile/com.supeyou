package com.supeyou.actor.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.supeyou.actor.iface.Session2EventCRUDService;
import com.supeyou.actor.iface.dto.EventDTO;
import com.supeyou.actor.iface.dto.Session2EventDTO;
import com.supeyou.actor.iface.dto.Session2EventFetchQuery;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.iface.dto.SessionIDType;
import com.supeyou.actor.impl.entity.EventEntity;
import com.supeyou.actor.impl.entity.Session2EventEntity;
import com.supeyou.actor.impl.entity.SessionEntity;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.dtohelper.AbstrDTOHelper;
import com.supeyou.crudie.impl.entity.UserEntity;

public class Session2EventCRUDServiceImpl extends AbstrCRUDServiceImpl<Session2EventDTO, Session2EventEntity, Session2EventFetchQuery> implements Session2EventCRUDService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private AbstrDTOHelper<SessionEntity, SessionDTO> aHelper;
	private AbstrDTOHelper<EventEntity, EventDTO> bHelper;

	@Override
	protected void postprocessDTO2Entity(EntityManager em, Session2EventDTO dto, Session2EventEntity entity) {

		log.log(Level.INFO, "dto.getDtoA().getId()=" + dto.getDtoA().getId());

		if (dto.getDtoA() != null && dto.getDtoA().getId() != null) {
			entity.setA(em.find(SessionEntity.class, dto.getDtoA().getId().value()));
		}

		log.log(Level.INFO, "dto.getDtoB().getId()=" + dto.getDtoB().getId());
		if (dto.getDtoB() != null && dto.getDtoB().getId() != null) {
			entity.setB(em.find(EventEntity.class, dto.getDtoB().getId().value()));
		}

	}

	@Override
	protected void postprocessEntity2DTO(EntityManager em, Session2EventEntity entity, Session2EventDTO dto) throws Exception {

		if (entity.getA() != null) {
			dto.setDtoA(aHelper.entity2DTO(entity.getA()));
		}
		if (entity.getB() != null) {
			dto.setDtoB(bHelper.entity2DTO(entity.getB()));
		}

	}

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, Session2EventFetchQuery query) {

		String whereClause = "";

		if (query.getIdA() != null) {
			whereClause += "a=" + query.getIdA().value() + " AND ";

		}
		if (query.getIdB() != null) {
			whereClause += "b=" + query.getIdB().value() + " AND ";
		}
		if (whereClause.endsWith("AND ")) {
			whereClause = whereClause.substring(0, whereClause.length() - 4);
		}
		if (!"".equals(whereClause)) {
			whereClause = " WHERE " + whereClause;
		}
		return whereClause;
	}

	// Singleton

	private static Session2EventCRUDServiceImpl service;

	private Session2EventCRUDServiceImpl() {
		super(Session2EventDTO.class, Session2EventEntity.class);
		aHelper = new AbstrDTOHelper<>(SessionDTO.class, SessionEntity.class);
		bHelper = new AbstrDTOHelper<>(EventDTO.class, EventEntity.class);

	}

	public static Session2EventCRUDServiceImpl i() {
		if (service == null) {
			service = new Session2EventCRUDServiceImpl();
		}
		return service;
	}

	@Override
	public Session2EventDTO addEventToSession(UserDTO actorDTO, SessionIDType sessionID, EventDTO newEventDTO) throws CRUDException {

		EventDTO eventDTO = EventCRUDServiceImpl.i().updadd(actorDTO, newEventDTO);

		SessionDTO sessionDTO = SessionCRUDServiceImpl.i().get(actorDTO, sessionID);

		if (sessionDTO != null) {

			Session2EventDTO session2EventDTO = new Session2EventDTO();
			session2EventDTO.setDtoA(sessionDTO);
			session2EventDTO.setDtoB(eventDTO);

			return Session2EventCRUDServiceImpl.i().updadd(actorDTO, session2EventDTO);

		} else {

			return null;

		}

	}

}
