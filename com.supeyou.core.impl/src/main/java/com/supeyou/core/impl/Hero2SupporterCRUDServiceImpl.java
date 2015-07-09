package com.supeyou.core.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.dto.Hero2SupporterDTO;
import com.supeyou.core.iface.dto.Hero2SupporterFetchQuery;
import com.supeyou.core.iface.dto.Hero2SupporterIDType;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.impl.entity.Hero2SupporterEntity;
import com.supeyou.core.impl.entity.HeroEntity;
import com.supeyou.core.impl.entity.SupporterEntity;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.dtohelper.AbstrDTOHelper;
import com.supeyou.crudie.impl.entity.UserEntity;

public class Hero2SupporterCRUDServiceImpl extends AbstrCRUDServiceImpl<Hero2SupporterDTO, Hero2SupporterEntity, Hero2SupporterFetchQuery> implements CRUDAssoService<HeroDTO, SupporterDTO, Hero2SupporterIDType, Hero2SupporterDTO, Hero2SupporterFetchQuery> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private AbstrDTOHelper<HeroEntity, HeroDTO> aHelper;
	private AbstrDTOHelper<SupporterEntity, SupporterDTO> bHelper;

	@Override
	protected void postprocessDTO2Entity(EntityManager em, Hero2SupporterDTO dto, Hero2SupporterEntity entity) {

		log.log(Level.INFO, "dto.getDtoA().getId()=" + dto.getDtoA().getId());

		if (dto.getDtoA() != null && dto.getDtoA().getId() != null) {
			entity.setA(em.find(HeroEntity.class, dto.getDtoA().getId().value()));
		}

		log.log(Level.INFO, "dto.getDtoB().getId()=" + dto.getDtoB().getId());
		if (dto.getDtoB() != null && dto.getDtoB().getId() != null) {
			entity.setB(em.find(SupporterEntity.class, dto.getDtoB().getId().value()));
		}

	}

	@Override
	protected void postprocessEntity2DTO(EntityManager em, Hero2SupporterEntity entity, Hero2SupporterDTO dto) throws Exception {

		if (entity.getA() != null) {
			dto.setDtoA(aHelper.entity2DTO(entity.getA()));
		}
		if (entity.getB() != null) {
			dto.setDtoB(bHelper.entity2DTO(entity.getB()));
		}

	}

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, Hero2SupporterFetchQuery query) {

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

	private static Hero2SupporterCRUDServiceImpl service;

	private Hero2SupporterCRUDServiceImpl() {
		super(Hero2SupporterDTO.class, Hero2SupporterEntity.class);
		aHelper = new AbstrDTOHelper<>(HeroDTO.class, HeroEntity.class);
		bHelper = new AbstrDTOHelper<>(SupporterDTO.class, SupporterEntity.class);

	}

	public static Hero2SupporterCRUDServiceImpl i() {
		if (service == null) {
			service = new Hero2SupporterCRUDServiceImpl();
		}
		return service;
	}

}
