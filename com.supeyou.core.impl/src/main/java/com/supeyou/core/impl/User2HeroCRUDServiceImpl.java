package com.supeyou.core.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.User2HeroDTO;
import com.supeyou.core.iface.dto.User2HeroFetchQuery;
import com.supeyou.core.iface.dto.User2HeroIDType;
import com.supeyou.core.impl.entity.HeroEntity;
import com.supeyou.core.impl.entity.User2HeroEntity;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.dtohelper.AbstrDTOHelper;
import com.supeyou.crudie.impl.entity.UserEntity;

public class User2HeroCRUDServiceImpl extends AbstrCRUDServiceImpl<User2HeroDTO, User2HeroEntity, User2HeroFetchQuery> implements CRUDAssoService<UserDTO, HeroDTO, User2HeroIDType, User2HeroDTO, User2HeroFetchQuery> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private AbstrDTOHelper<UserEntity, UserDTO> aHelper;
	private AbstrDTOHelper<HeroEntity, HeroDTO> bHelper;

	@Override
	protected void postprocessDTO2Entity(EntityManager em, User2HeroDTO dto, User2HeroEntity entity) {

		log.log(Level.INFO, "dto.getDtoA().getId()=" + dto.getDtoA().getId());

		if (dto.getDtoA() != null && dto.getDtoA().getId() != null) {
			entity.setA(em.find(UserEntity.class, dto.getDtoA().getId().value()));
		}

		log.log(Level.INFO, "dto.getDtoB().getId()=" + dto.getDtoB().getId());
		if (dto.getDtoB() != null && dto.getDtoB().getId() != null) {
			entity.setB(em.find(HeroEntity.class, dto.getDtoB().getId().value()));
		}

	}

	@Override
	protected void postprocessEntity2DTO(EntityManager em, User2HeroEntity entity, User2HeroDTO dto) throws Exception {

		if (entity.getA() != null) {
			dto.setDtoA(aHelper.entity2DTO(entity.getA()));
		}
		if (entity.getB() != null) {
			dto.setDtoB(bHelper.entity2DTO(entity.getB()));
		}

	}

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, User2HeroFetchQuery query) {

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

	private static User2HeroCRUDServiceImpl service;

	private User2HeroCRUDServiceImpl() {
		super(User2HeroDTO.class, User2HeroEntity.class);
		aHelper = new AbstrDTOHelper<>(UserDTO.class, UserEntity.class);
		bHelper = new AbstrDTOHelper<>(HeroDTO.class, HeroEntity.class);

	}

	public static User2HeroCRUDServiceImpl i() {
		if (service == null) {
			service = new User2HeroCRUDServiceImpl();
		}
		return service;
	}

}
