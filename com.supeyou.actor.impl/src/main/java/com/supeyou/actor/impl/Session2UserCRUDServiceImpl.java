package com.supeyou.actor.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.supeyou.actor.iface.dto.Session2UserDTO;
import com.supeyou.actor.iface.dto.Session2UserFetchQuery;
import com.supeyou.actor.iface.dto.Session2UserIDType;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.impl.entity.Session2UserEntity;
import com.supeyou.actor.impl.entity.SessionEntity;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.dtohelper.AbstrDTOHelper;
import com.supeyou.crudie.impl.entity.UserEntity;

public class Session2UserCRUDServiceImpl extends AbstrCRUDServiceImpl<Session2UserDTO, Session2UserEntity, Session2UserFetchQuery> implements CRUDAssoService<SessionDTO, UserDTO, Session2UserIDType, Session2UserDTO, Session2UserFetchQuery> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private AbstrDTOHelper<SessionEntity, SessionDTO> aHelper;
	private AbstrDTOHelper<UserEntity, UserDTO> bHelper;

	@Override
	protected void postprocessDTO2Entity(EntityManager em, Session2UserDTO dto, Session2UserEntity entity) {

		log.log(Level.INFO, "dto.getDtoA().getId()=" + dto.getDtoA().getId());

		if (dto.getDtoA() != null && dto.getDtoA().getId() != null) {
			entity.setA(em.find(SessionEntity.class, dto.getDtoA().getId().value()));
		}

		log.log(Level.INFO, "dto.getDtoB().getId()=" + dto.getDtoB().getId());
		if (dto.getDtoB() != null && dto.getDtoB().getId() != null) {
			entity.setB(em.find(UserEntity.class, dto.getDtoB().getId().value()));
		}

	}

	@Override
	protected void postprocessEntity2DTO(EntityManager em, Session2UserEntity entity, Session2UserDTO dto) throws Exception {

		if (entity.getA() != null) {
			dto.setDtoA(aHelper.entity2DTO(entity.getA()));
		}
		if (entity.getB() != null) {
			dto.setDtoB(bHelper.entity2DTO(entity.getB()));
		}

	}

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, Session2UserFetchQuery query) {

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

	private static Session2UserCRUDServiceImpl service;

	private Session2UserCRUDServiceImpl() {
		super(Session2UserDTO.class, Session2UserEntity.class);
		aHelper = new AbstrDTOHelper<>(SessionDTO.class, SessionEntity.class);
		bHelper = new AbstrDTOHelper<>(UserDTO.class, UserEntity.class);

	}

	public static Session2UserCRUDServiceImpl i() {
		if (service == null) {
			service = new Session2UserCRUDServiceImpl();
		}
		return service;
	}

}
