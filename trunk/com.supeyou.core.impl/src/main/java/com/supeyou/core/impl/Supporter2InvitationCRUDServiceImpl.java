package com.supeyou.core.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.Supporter2InvitationDTO;
import com.supeyou.core.iface.dto.Supporter2InvitationFetchQuery;
import com.supeyou.core.iface.dto.Supporter2InvitationIDType;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.impl.entity.InvitationEntity;
import com.supeyou.core.impl.entity.Supporter2InvitationEntity;
import com.supeyou.core.impl.entity.SupporterEntity;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.dtohelper.AbstrDTOHelper;
import com.supeyou.crudie.impl.entity.UserEntity;

public class Supporter2InvitationCRUDServiceImpl extends AbstrCRUDServiceImpl<Supporter2InvitationDTO, Supporter2InvitationEntity, Supporter2InvitationFetchQuery> implements CRUDAssoService<SupporterDTO, InvitationDTO, Supporter2InvitationIDType, Supporter2InvitationDTO, Supporter2InvitationFetchQuery> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private AbstrDTOHelper<SupporterEntity, SupporterDTO> aHelper;
	private AbstrDTOHelper<InvitationEntity, InvitationDTO> bHelper;

	@Override
	protected void postprocessDTO2Entity(EntityManager em, Supporter2InvitationDTO dto, Supporter2InvitationEntity entity) {

		log.log(Level.INFO, "dto.getDtoA().getId()=" + dto.getDtoA().getId());

		if (dto.getDtoA() != null && dto.getDtoA().getId() != null) {
			entity.setA(em.find(SupporterEntity.class, dto.getDtoA().getId().value()));
		}

		log.log(Level.INFO, "dto.getDtoB().getId()=" + dto.getDtoB().getId());
		if (dto.getDtoB() != null && dto.getDtoB().getId() != null) {
			entity.setB(em.find(InvitationEntity.class, dto.getDtoB().getId().value()));
		}

	}

	@Override
	protected void postprocessEntity2DTO(EntityManager em, Supporter2InvitationEntity entity, Supporter2InvitationDTO dto) throws Exception {

		if (entity.getA() != null) {
			dto.setDtoA(aHelper.entity2DTO(entity.getA()));
		}
		if (entity.getB() != null) {
			dto.setDtoB(bHelper.entity2DTO(entity.getB()));
		}

	}

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, Supporter2InvitationFetchQuery query) {

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

	private static Supporter2InvitationCRUDServiceImpl service;

	private Supporter2InvitationCRUDServiceImpl() {
		super(Supporter2InvitationDTO.class, Supporter2InvitationEntity.class);
		aHelper = new AbstrDTOHelper<>(SupporterDTO.class, SupporterEntity.class);
		bHelper = new AbstrDTOHelper<>(InvitationDTO.class, InvitationEntity.class);

	}

	public static Supporter2InvitationCRUDServiceImpl i() {
		if (service == null) {
			service = new Supporter2InvitationCRUDServiceImpl();
		}
		return service;
	}

}
