package com.supeyou.core.impl;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.Guest2AcceptanceDTO;
import com.supeyou.core.iface.dto.Guest2AcceptanceFetchQuery;
import com.supeyou.core.iface.dto.Guest2AcceptanceIDType;
import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.impl.entity.AcceptanceEntity;
import com.supeyou.core.impl.entity.Guest2AcceptanceEntity;
import com.supeyou.core.impl.entity.GuestEntity;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.dtohelper.AbstrDTOHelper;
import com.supeyou.crudie.impl.entity.UserEntity;

public class Guest2AcceptanceCRUDServiceImpl extends AbstrCRUDServiceImpl<Guest2AcceptanceDTO, Guest2AcceptanceEntity, Guest2AcceptanceFetchQuery> implements CRUDAssoService<GuestDTO, AcceptanceDTO, Guest2AcceptanceIDType, Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery> {

	private AbstrDTOHelper<GuestEntity, GuestDTO> aHelper;
	private AbstrDTOHelper<AcceptanceEntity, AcceptanceDTO> bHelper;

	@Override
	protected void postprocessDTO2Entity(EntityManager em, Guest2AcceptanceDTO dto, Guest2AcceptanceEntity entity) {

		if (dto.getDtoA() != null && dto.getDtoA().getId() != null) {
			entity.setA(em.find(GuestEntity.class, dto.getDtoA().getId().value()));
		}

		if (dto.getDtoB() != null && dto.getDtoB().getId() != null) {
			entity.setB(em.find(AcceptanceEntity.class, dto.getDtoB().getId().value()));
		}

	}

	@Override
	protected void postprocessEntity2DTO(EntityManager em, Guest2AcceptanceEntity entity, Guest2AcceptanceDTO dto) throws Exception {

		if (entity.getA() != null) {
			dto.setDtoA(aHelper.entity2DTO(entity.getA()));
		}
		if (entity.getB() != null) {
			dto.setDtoB(bHelper.entity2DTO(entity.getB()));
			AcceptanceCRUDServiceImpl.addTmpInfosToAcceptanceDTO(entity.getB(), dto.getDtoB());
		}

	}

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, Guest2AcceptanceFetchQuery query) {

		String whereClause = "";

		if (query.getIdA() != null) {
			whereClause += "a=" + query.getIdA().value() + " AND ";

		}
		if (query.getIdB() != null) {
			whereClause += "b=" + query.getIdB().value() + " AND ";
		}
		if (query.getFrom() != null) {
			whereClause += "b.toDate >='" + query.getFrom() + "' AND ";
		}
		if (query.getTo() != null) {
			whereClause += "b.fromDate <='" + query.getTo() + "' AND ";
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

	private static Guest2AcceptanceCRUDServiceImpl service;

	private Guest2AcceptanceCRUDServiceImpl() {
		super(Guest2AcceptanceDTO.class, Guest2AcceptanceEntity.class);
		aHelper = new AbstrDTOHelper<>(GuestDTO.class, GuestEntity.class);
		bHelper = new AbstrDTOHelper<>(AcceptanceDTO.class, AcceptanceEntity.class);

	}

	public static Guest2AcceptanceCRUDServiceImpl i() {
		if (service == null) {
			service = new Guest2AcceptanceCRUDServiceImpl();
		}
		return service;
	}

}
