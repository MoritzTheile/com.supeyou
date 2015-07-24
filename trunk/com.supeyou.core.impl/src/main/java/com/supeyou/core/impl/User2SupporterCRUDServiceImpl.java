package com.supeyou.core.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.User2SupporterDTO;
import com.supeyou.core.iface.dto.User2SupporterFetchQuery;
import com.supeyou.core.iface.dto.User2SupporterIDType;
import com.supeyou.core.impl.entity.SupporterEntity;
import com.supeyou.core.impl.entity.User2SupporterEntity;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.dtohelper.AbstrDTOHelper;
import com.supeyou.crudie.impl.entity.UserEntity;

public class User2SupporterCRUDServiceImpl extends AbstrCRUDServiceImpl<User2SupporterDTO, User2SupporterEntity, User2SupporterFetchQuery> implements CRUDAssoService<UserDTO, SupporterDTO, User2SupporterIDType, User2SupporterDTO, User2SupporterFetchQuery> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private AbstrDTOHelper<UserEntity, UserDTO> aHelper;
	private AbstrDTOHelper<SupporterEntity, SupporterDTO> bHelper;

	@Override
	protected void postprocessDTO2Entity(EntityManager em, User2SupporterDTO dto, User2SupporterEntity entity) {

		log.log(Level.INFO, "dto.getDtoA().getId()=" + dto.getDtoA().getId());

		if (dto.getDtoA() != null && dto.getDtoA().getId() != null) {
			entity.setA(em.find(UserEntity.class, dto.getDtoA().getId().value()));
		}

		log.log(Level.INFO, "dto.getDtoB().getId()=" + dto.getDtoB().getId());
		if (dto.getDtoB() != null && dto.getDtoB().getId() != null) {
			entity.setB(em.find(SupporterEntity.class, dto.getDtoB().getId().value()));
		}

	}

	@Override
	protected void postprocessEntity2DTO(EntityManager em, User2SupporterEntity entity, User2SupporterDTO dto) throws Exception {

		if (entity.getA() != null) {
			dto.setDtoA(aHelper.entity2DTO(entity.getA()));
		}

		if (entity.getB() != null) {
			SupporterDTO supporterDTO = bHelper.entity2DTO(entity.getB());
			SupporterCRUDServiceImpl.i().postprocessEntity2DTO(em, entity.getB(), supporterDTO);
			dto.setDtoB(supporterDTO);
		}

	}

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, User2SupporterFetchQuery query) {

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

	private static User2SupporterCRUDServiceImpl service;

	private User2SupporterCRUDServiceImpl() {
		super(User2SupporterDTO.class, User2SupporterEntity.class);
		aHelper = new AbstrDTOHelper<>(UserDTO.class, UserEntity.class);
		bHelper = new AbstrDTOHelper<>(SupporterDTO.class, SupporterEntity.class);

	}

	public static User2SupporterCRUDServiceImpl i() {
		if (service == null) {
			service = new User2SupporterCRUDServiceImpl();
		}
		return service;
	}

}
