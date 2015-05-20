package com.supeyou.actor.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.supeyou.actor.iface.dto.ActingUser2SessionDTO;
import com.supeyou.actor.iface.dto.ActingUser2SessionFetchQuery;
import com.supeyou.actor.iface.dto.ActingUser2SessionIDType;
import com.supeyou.actor.iface.dto.ActingUserDTO;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.impl.entity.ActingUser2SessionEntity;
import com.supeyou.actor.impl.entity.ActingUserEntity;
import com.supeyou.actor.impl.entity.SessionEntity;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.dtohelper.AbstrDTOHelper;
import com.supeyou.crudie.impl.entity.UserEntity;

public class ActingUser2SessionCRUDServiceImpl extends AbstrCRUDServiceImpl<ActingUser2SessionDTO, ActingUser2SessionEntity, ActingUser2SessionFetchQuery> implements CRUDAssoService<ActingUserDTO, SessionDTO, ActingUser2SessionIDType, ActingUser2SessionDTO, ActingUser2SessionFetchQuery> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private AbstrDTOHelper<ActingUserEntity, ActingUserDTO> aHelper;
	private AbstrDTOHelper<SessionEntity, SessionDTO> bHelper;

	@Override
	protected void postprocessDTO2Entity(EntityManager em, ActingUser2SessionDTO dto, ActingUser2SessionEntity entity) {

		log.log(Level.INFO, "dto.getDtoA().getId()=" + dto.getDtoA().getId());

		if (dto.getDtoA() != null && dto.getDtoA().getId() != null) {
			entity.setA(em.find(ActingUserEntity.class, dto.getDtoA().getId().value()));
		}

		log.log(Level.INFO, "dto.getDtoB().getId()=" + dto.getDtoB().getId());
		if (dto.getDtoB() != null && dto.getDtoB().getId() != null) {
			entity.setB(em.find(SessionEntity.class, dto.getDtoB().getId().value()));
		}

	}

	@Override
	protected void postprocessEntity2DTO(EntityManager em, ActingUser2SessionEntity entity, ActingUser2SessionDTO dto) throws Exception {

		if (entity.getA() != null) {
			dto.setDtoA(aHelper.entity2DTO(entity.getA()));
		}
		if (entity.getB() != null) {
			dto.setDtoB(bHelper.entity2DTO(entity.getB()));
		}

	}

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, ActingUser2SessionFetchQuery query) {

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

	private static ActingUser2SessionCRUDServiceImpl service;

	private ActingUser2SessionCRUDServiceImpl() {
		super(ActingUser2SessionDTO.class, ActingUser2SessionEntity.class);
		aHelper = new AbstrDTOHelper<>(ActingUserDTO.class, ActingUserEntity.class);
		bHelper = new AbstrDTOHelper<>(SessionDTO.class, SessionEntity.class);

	}

	public static ActingUser2SessionCRUDServiceImpl i() {
		if (service == null) {
			service = new ActingUser2SessionCRUDServiceImpl();
		}
		return service;
	}

}
