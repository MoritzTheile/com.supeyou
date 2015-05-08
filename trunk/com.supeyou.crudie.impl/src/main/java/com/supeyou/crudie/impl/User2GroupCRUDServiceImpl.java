package com.supeyou.crudie.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.iface.datatype.types.User2GroupIDType;
import com.supeyou.crudie.iface.dto.GroupDTO;
import com.supeyou.crudie.iface.dto.User2GroupDTO;
import com.supeyou.crudie.iface.dto.User2GroupFetchQuery;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.dtohelper.AbstrDTOHelper;
import com.supeyou.crudie.impl.entity.GroupEntity;
import com.supeyou.crudie.impl.entity.User2GroupEntity;
import com.supeyou.crudie.impl.entity.UserEntity;

public class User2GroupCRUDServiceImpl extends AbstrCRUDServiceImpl<User2GroupDTO, User2GroupEntity, User2GroupFetchQuery> implements CRUDAssoService<UserDTO, GroupDTO, User2GroupIDType, User2GroupDTO, User2GroupFetchQuery> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private AbstrDTOHelper<UserEntity, UserDTO> aHelper;
	private AbstrDTOHelper<GroupEntity, GroupDTO> bHelper;

	@Override
	protected void postprocessDTO2Entity(EntityManager em, User2GroupDTO dto, User2GroupEntity entity) {

		log.log(Level.INFO, "dto.getDtoA().getId()=" + dto.getDtoA().getId());

		if (dto.getDtoA() != null && dto.getDtoA().getId() != null) {
			entity.setA(em.find(UserEntity.class, dto.getDtoA().getId().value()));
		}

		log.log(Level.INFO, "dto.getDtoB().getId()=" + dto.getDtoB().getId());
		if (dto.getDtoB() != null && dto.getDtoB().getId() != null) {
			entity.setB(em.find(GroupEntity.class, dto.getDtoB().getId().value()));
		}

	}

	@Override
	protected void postprocessEntity2DTO(EntityManager em, User2GroupEntity entity, User2GroupDTO dto) throws Exception {

		if (entity.getA() != null) {
			dto.setDtoA(aHelper.entity2DTO(entity.getA()));
		}
		if (entity.getB() != null) {
			dto.setDtoB(bHelper.entity2DTO(entity.getB()));
		}

	}

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, User2GroupFetchQuery query) {

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

	private static User2GroupCRUDServiceImpl service;

	private User2GroupCRUDServiceImpl() {
		super(User2GroupDTO.class, User2GroupEntity.class);
		aHelper = new AbstrDTOHelper<>(UserDTO.class, UserEntity.class);
		bHelper = new AbstrDTOHelper<>(GroupDTO.class, GroupEntity.class);

	}

	public static User2GroupCRUDServiceImpl i() {
		if (service == null) {
			service = new User2GroupCRUDServiceImpl();
		}
		return service;
	}

}
