package com.supeyou.core.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.dto.Guest2GuestGroupDTO;
import com.supeyou.core.iface.dto.Guest2GuestGroupFetchQuery;
import com.supeyou.core.iface.dto.Guest2GuestGroupIDType;
import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.iface.dto.GuestGroupDTO;
import com.supeyou.core.impl.entity.Guest2GuestGroupEntity;
import com.supeyou.core.impl.entity.GuestEntity;
import com.supeyou.core.impl.entity.GuestGroupEntity;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.dtohelper.AbstrDTOHelper;
import com.supeyou.crudie.impl.entity.UserEntity;

public class Guest2GuestGroupCRUDServiceImpl extends AbstrCRUDServiceImpl<Guest2GuestGroupDTO, Guest2GuestGroupEntity, Guest2GuestGroupFetchQuery> implements CRUDAssoService<GuestDTO, GuestGroupDTO, Guest2GuestGroupIDType, Guest2GuestGroupDTO, Guest2GuestGroupFetchQuery> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private AbstrDTOHelper<GuestEntity, GuestDTO> aHelper;
	private AbstrDTOHelper<GuestGroupEntity, GuestGroupDTO> bHelper;

	@Override
	protected void postprocessDTO2Entity(EntityManager em, Guest2GuestGroupDTO dto, Guest2GuestGroupEntity entity) {

		if (dto.getDtoA() != null && dto.getDtoA().getId() != null) {
			entity.setA(em.find(GuestEntity.class, dto.getDtoA().getId().value()));
		}

		if (dto.getDtoB() != null && dto.getDtoB().getId() != null) {
			entity.setB(em.find(GuestGroupEntity.class, dto.getDtoB().getId().value()));
		}

	}

	@Override
	protected void postprocessEntity2DTO(EntityManager em, Guest2GuestGroupEntity entity, Guest2GuestGroupDTO dto) throws Exception {

		if (entity.getA() != null) {
			dto.setDtoA(aHelper.entity2DTO(entity.getA()));
		}
		if (entity.getB() != null) {
			dto.setDtoB(bHelper.entity2DTO(entity.getB()));
		}

	}

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, Guest2GuestGroupFetchQuery query) {

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

	@Override
	protected void beforeUpdadd(EntityManager em, UserEntity actor, Guest2GuestGroupDTO dto) {

		if (dto.getId() == null) {
			log.log(Level.INFO, "Guest2GuestGroup assoziation will be created");
			GuestEntity aEntity = em.find(GuestEntity.class, dto.getDtoA().getId().value());

			// It is only one Payer to Acceptance association allowed. So deleting others. (there should only be one)
			for (Guest2GuestGroupEntity acceptance2PayerEntity : aEntity.getGuest2guestGroupCollection()) {
				log.log(Level.INFO, "Deleting existent Assoziation to enforce 1-to-n relationship");
				em.remove(acceptance2PayerEntity);

			}
			em.flush();
		}

		if (dto.getGroupLeader() != null && dto.getGroupLeader()) {
			GuestGroupEntity guestGroupEntity = em.find(GuestGroupEntity.class, dto.getDtoB().getId().value());
			for (Guest2GuestGroupEntity guest2GuestGroupEntity : guestGroupEntity.getGuest2guestGroupCollection()) {
				guest2GuestGroupEntity.setGroupLeader(false);
			}
			em.flush();
		}

	}

	// Singleton

	private static Guest2GuestGroupCRUDServiceImpl service;

	private Guest2GuestGroupCRUDServiceImpl() {
		super(Guest2GuestGroupDTO.class, Guest2GuestGroupEntity.class);
		aHelper = new AbstrDTOHelper<>(GuestDTO.class, GuestEntity.class);
		bHelper = new AbstrDTOHelper<>(GuestGroupDTO.class, GuestGroupEntity.class);

	}

	public static Guest2GuestGroupCRUDServiceImpl i() {
		if (service == null) {
			service = new Guest2GuestGroupCRUDServiceImpl();
		}
		return service;
	}

}
