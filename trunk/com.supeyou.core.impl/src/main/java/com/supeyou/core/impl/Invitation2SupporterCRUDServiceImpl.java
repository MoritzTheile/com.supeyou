package com.supeyou.core.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterFetchQuery;
import com.supeyou.core.iface.dto.Invitation2SupporterIDType;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.impl.SupporterCRUDServiceImpl.SupporterAction;
import com.supeyou.core.impl.entity.Invitation2SupporterEntity;
import com.supeyou.core.impl.entity.InvitationEntity;
import com.supeyou.core.impl.entity.SupporterEntity;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.dtohelper.AbstrDTOHelper;
import com.supeyou.crudie.impl.entity.UserEntity;

public class Invitation2SupporterCRUDServiceImpl extends AbstrCRUDServiceImpl<Invitation2SupporterDTO, Invitation2SupporterEntity, Invitation2SupporterFetchQuery> implements CRUDAssoService<InvitationDTO, SupporterDTO, Invitation2SupporterIDType, Invitation2SupporterDTO, Invitation2SupporterFetchQuery> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private AbstrDTOHelper<InvitationEntity, InvitationDTO> aHelper;
	private AbstrDTOHelper<SupporterEntity, SupporterDTO> bHelper;

	protected void afterUpdadd(EntityManager em, UserEntity actor, Invitation2SupporterDTO dto) {

		SupporterEntity supporterEntityEdited = InvitationCRUDServiceImpl.i().getSupporter(em, em.find(InvitationEntity.class, dto.getDtoA().getId().value()));

		// flush is necessary so the just followed invitation is considered
		em.flush();

		SupporterCRUDServiceImpl.executeActionAndRecurseOnAncestors(em, supporterEntityEdited, true, new SupporterAction() {

			@Override
			public void execute(EntityManager em, SupporterEntity supporterEntity) {

				Integer sum = 0;

				for (SupporterEntity descendantSupporterEntity : SupporterCRUDServiceImpl.getDirectDecendants(em, supporterEntity, true)) {

					sum += descendantSupporterEntity.getDecendentCount() + 1;

				}
				System.out.println("asdfuuu setting sum of " + SupporterCRUDServiceImpl.getUserEntity(em, supporterEntity).getLoginId() + " too " + sum);

				supporterEntity.setDecendentCount(sum);

			}

		});

	}

	@Override
	protected void postprocessDTO2Entity(EntityManager em, Invitation2SupporterDTO dto, Invitation2SupporterEntity entity) {

		log.log(Level.INFO, "dto.getDtoA().getId()=" + dto.getDtoA().getId());

		if (dto.getDtoA() != null && dto.getDtoA().getId() != null) {
			entity.setA(em.find(InvitationEntity.class, dto.getDtoA().getId().value()));
		}

		log.log(Level.INFO, "dto.getDtoB().getId()=" + dto.getDtoB().getId());
		if (dto.getDtoB() != null && dto.getDtoB().getId() != null) {
			entity.setB(em.find(SupporterEntity.class, dto.getDtoB().getId().value()));
		}

	}

	@Override
	protected void postprocessEntity2DTO(EntityManager em, Invitation2SupporterEntity entity, Invitation2SupporterDTO dto) throws Exception {

		if (entity.getA() != null) {
			dto.setDtoA(aHelper.entity2DTO(entity.getA()));
		}
		if (entity.getB() != null) {
			dto.setDtoB(bHelper.entity2DTO(entity.getB()));
		}

	}

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, Invitation2SupporterFetchQuery query) {

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

	private static Invitation2SupporterCRUDServiceImpl service;

	private Invitation2SupporterCRUDServiceImpl() {
		super(Invitation2SupporterDTO.class, Invitation2SupporterEntity.class);
		aHelper = new AbstrDTOHelper<>(InvitationDTO.class, InvitationEntity.class);
		bHelper = new AbstrDTOHelper<>(SupporterDTO.class, SupporterEntity.class);

	}

	public static Invitation2SupporterCRUDServiceImpl i() {
		if (service == null) {
			service = new Invitation2SupporterCRUDServiceImpl();
		}
		return service;
	}

}
