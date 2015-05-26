package com.supeyou.core.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.dto.DonationDTO;
import com.supeyou.core.iface.dto.Supporter2DonationDTO;
import com.supeyou.core.iface.dto.Supporter2DonationFetchQuery;
import com.supeyou.core.iface.dto.Supporter2DonationIDType;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.impl.entity.DonationEntity;
import com.supeyou.core.impl.entity.Supporter2DonationEntity;
import com.supeyou.core.impl.entity.SupporterEntity;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.dtohelper.AbstrDTOHelper;
import com.supeyou.crudie.impl.entity.UserEntity;

public class Supporter2DonationCRUDServiceImpl extends AbstrCRUDServiceImpl<Supporter2DonationDTO, Supporter2DonationEntity, Supporter2DonationFetchQuery> implements CRUDAssoService<SupporterDTO, DonationDTO, Supporter2DonationIDType, Supporter2DonationDTO, Supporter2DonationFetchQuery> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private AbstrDTOHelper<SupporterEntity, SupporterDTO> aHelper;
	private AbstrDTOHelper<DonationEntity, DonationDTO> bHelper;

	@Override
	protected void postprocessDTO2Entity(EntityManager em, Supporter2DonationDTO dto, Supporter2DonationEntity entity) {

		log.log(Level.INFO, "dto.getDtoA().getId()=" + dto.getDtoA().getId());

		if (dto.getDtoA() != null && dto.getDtoA().getId() != null) {
			entity.setA(em.find(SupporterEntity.class, dto.getDtoA().getId().value()));
		}

		log.log(Level.INFO, "dto.getDtoB().getId()=" + dto.getDtoB().getId());
		if (dto.getDtoB() != null && dto.getDtoB().getId() != null) {
			entity.setB(em.find(DonationEntity.class, dto.getDtoB().getId().value()));
		}

	}

	@Override
	protected void postprocessEntity2DTO(EntityManager em, Supporter2DonationEntity entity, Supporter2DonationDTO dto) throws Exception {

		if (entity.getA() != null) {
			dto.setDtoA(aHelper.entity2DTO(entity.getA()));
		}
		if (entity.getB() != null) {
			dto.setDtoB(bHelper.entity2DTO(entity.getB()));
		}

	}

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, Supporter2DonationFetchQuery query) {

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

	private static Supporter2DonationCRUDServiceImpl service;

	private Supporter2DonationCRUDServiceImpl() {
		super(Supporter2DonationDTO.class, Supporter2DonationEntity.class);
		aHelper = new AbstrDTOHelper<>(SupporterDTO.class, SupporterEntity.class);
		bHelper = new AbstrDTOHelper<>(DonationDTO.class, DonationEntity.class);

	}

	public static Supporter2DonationCRUDServiceImpl i() {
		if (service == null) {
			service = new Supporter2DonationCRUDServiceImpl();
		}
		return service;
	}

}
