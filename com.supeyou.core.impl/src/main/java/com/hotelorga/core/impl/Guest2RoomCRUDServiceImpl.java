package com.hotelorga.core.impl;

import javax.persistence.EntityManager;

import com.hotelorga.core.iface.dto.Guest2RoomDTO;
import com.hotelorga.core.iface.dto.Guest2RoomFetchQuery;
import com.hotelorga.core.iface.dto.Guest2RoomIDType;
import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.iface.dto.RoomDTO;
import com.hotelorga.core.impl.entity.Guest2RoomEntity;
import com.hotelorga.core.impl.entity.GuestEntity;
import com.hotelorga.core.impl.entity.RoomEntity;
import com.hotelorga.foundation.iface.CRUDAssoService;
import com.hotelorga.foundation.impl.AbstrCRUDServiceImpl;
import com.hotelorga.foundation.impl.dtohelper.AbstrDTOHelper;
import com.hotelorga.foundation.impl.entity.UserEntity;

public class Guest2RoomCRUDServiceImpl extends AbstrCRUDServiceImpl<Guest2RoomDTO, Guest2RoomEntity, Guest2RoomFetchQuery> implements CRUDAssoService<GuestDTO, RoomDTO, Guest2RoomIDType, Guest2RoomDTO, Guest2RoomFetchQuery> {

	private AbstrDTOHelper<GuestEntity, GuestDTO> aHelper;
	private AbstrDTOHelper<RoomEntity, RoomDTO> bHelper;

	@Override
	protected void postprocessDTO2Entity(EntityManager em, Guest2RoomDTO dto, Guest2RoomEntity entity) {

		if (dto.getDtoA() != null && dto.getDtoA().getId() != null) {
			entity.setA(em.find(GuestEntity.class, dto.getDtoA().getId().value()));
		}

		if (dto.getDtoB() != null && dto.getDtoB().getId() != null) {
			entity.setB(em.find(RoomEntity.class, dto.getDtoB().getId().value()));
		}

	}

	@Override
	protected void postprocessEntity2DTO(EntityManager em, Guest2RoomEntity entity, Guest2RoomDTO dto) throws Exception {

		if (entity.getA() != null) {
			dto.setDtoA(aHelper.entity2DTO(entity.getA()));
		}
		if (entity.getB() != null) {
			dto.setDtoB(bHelper.entity2DTO(entity.getB()));
		}

	}

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, Guest2RoomFetchQuery query) {

		String whereClause = "";

		if (query.getIdA() != null) {
			whereClause += "a=" + query.getIdA().value() + " AND ";

		}
		if (query.getIdB() != null) {
			whereClause += "b=" + query.getIdB().value() + " AND ";
		}
		if (query.getFrom() != null) {
			whereClause += "toDate >='" + query.getFrom() + "' AND ";
		}
		if (query.getTo() != null) {
			whereClause += "fromDate <='" + query.getTo() + "' AND ";
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

	private static Guest2RoomCRUDServiceImpl service;

	private Guest2RoomCRUDServiceImpl() {
		super(Guest2RoomDTO.class, Guest2RoomEntity.class);
		aHelper = new AbstrDTOHelper<>(GuestDTO.class, GuestEntity.class);
		bHelper = new AbstrDTOHelper<>(RoomDTO.class, RoomEntity.class);

	}

	public static Guest2RoomCRUDServiceImpl i() {
		if (service == null) {
			service = new Guest2RoomCRUDServiceImpl();
		}
		return service;
	}

}
