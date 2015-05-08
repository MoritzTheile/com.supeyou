package com.supeyou.core.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.dto.Acceptance2PayerDTO;
import com.supeyou.core.iface.dto.Acceptance2PayerFetchQuery;
import com.supeyou.core.iface.dto.Acceptance2PayerIDType;
import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.PayerDTO;
import com.supeyou.core.impl.entity.Acceptance2PayerEntity;
import com.supeyou.core.impl.entity.AcceptanceEntity;
import com.supeyou.core.impl.entity.PayerEntity;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.dtohelper.AbstrDTOHelper;
import com.supeyou.crudie.impl.entity.UserEntity;

public class Acceptance2PayerCRUDServiceImpl extends AbstrCRUDServiceImpl<Acceptance2PayerDTO, Acceptance2PayerEntity, Acceptance2PayerFetchQuery> implements CRUDAssoService<AcceptanceDTO, PayerDTO, Acceptance2PayerIDType, Acceptance2PayerDTO, Acceptance2PayerFetchQuery> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private AbstrDTOHelper<AcceptanceEntity, AcceptanceDTO> aHelper;
	private AbstrDTOHelper<PayerEntity, PayerDTO> bHelper;

	@Override
	protected void postprocessDTO2Entity(EntityManager em, Acceptance2PayerDTO dto, Acceptance2PayerEntity entity) {

		if (dto.getDtoA() != null && dto.getDtoA().getId() != null) {
			entity.setA(em.find(AcceptanceEntity.class, dto.getDtoA().getId().value()));
		}

		if (dto.getDtoB() != null && dto.getDtoB().getId() != null) {
			entity.setB(em.find(PayerEntity.class, dto.getDtoB().getId().value()));
		}

	}

	@Override
	protected void postprocessEntity2DTO(EntityManager em, Acceptance2PayerEntity entity, Acceptance2PayerDTO dto) throws Exception {

		if (entity.getA() != null) {
			dto.setDtoA(aHelper.entity2DTO(entity.getA()));
		}
		if (entity.getB() != null) {
			dto.setDtoB(bHelper.entity2DTO(entity.getB()));
		}

	}

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, Acceptance2PayerFetchQuery query) {

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
	protected void beforeUpdadd(EntityManager em, UserEntity actor, Acceptance2PayerDTO dto) {

		if (dto.getId() == null) {
			log.log(Level.INFO, "Acceptance2Payer assoziation will be created");
			AcceptanceEntity acceptanceEntity = em.find(AcceptanceEntity.class, dto.getDtoA().getId().value());

			// It is only one Payer to Acceptance association allowed. So deleting others. (there should only be one)
			for (Acceptance2PayerEntity acceptance2PayerEntity : acceptanceEntity.getAcceptance2payerCollection()) {
				log.log(Level.INFO, "Deleting existent Assoziation to enforce 1-to-n relationship");
				em.remove(acceptance2PayerEntity);

			}
			em.flush();
		}

	}

	// Singleton

	private static Acceptance2PayerCRUDServiceImpl service;

	private Acceptance2PayerCRUDServiceImpl() {
		super(Acceptance2PayerDTO.class, Acceptance2PayerEntity.class);
		aHelper = new AbstrDTOHelper<>(AcceptanceDTO.class, AcceptanceEntity.class);
		bHelper = new AbstrDTOHelper<>(PayerDTO.class, PayerEntity.class);

	}

	public static Acceptance2PayerCRUDServiceImpl i() {
		if (service == null) {
			service = new Acceptance2PayerCRUDServiceImpl();
		}
		return service;
	}

}
