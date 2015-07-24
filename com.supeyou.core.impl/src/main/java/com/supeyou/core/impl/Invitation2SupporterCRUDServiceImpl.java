package com.supeyou.core.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterFetchQuery;
import com.supeyou.core.iface.dto.Invitation2SupporterIDType;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.impl.entity.Invitation2SupporterEntity;
import com.supeyou.core.impl.entity.InvitationEntity;
import com.supeyou.core.impl.entity.Supporter2InvitationEntity;
import com.supeyou.core.impl.entity.SupporterEntity;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.dtohelper.AbstrDTOHelper;
import com.supeyou.crudie.impl.entity.UserEntity;

public class Invitation2SupporterCRUDServiceImpl extends AbstrCRUDServiceImpl<Invitation2SupporterDTO, Invitation2SupporterEntity, Invitation2SupporterFetchQuery> implements CRUDAssoService<InvitationDTO, SupporterDTO, Invitation2SupporterIDType, Invitation2SupporterDTO, Invitation2SupporterFetchQuery> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private AbstrDTOHelper<InvitationEntity, InvitationDTO> aHelper;
	private AbstrDTOHelper<SupporterEntity, SupporterDTO> bHelper;

	protected void beforeUpdadd(EntityManager em, UserEntity actor, Invitation2SupporterDTO dto) {

		if (dto.getId() == null) { // a new edge will be created so ancestors have to be updated
			SupporterEntity supporterEntityFrom = InvitationCRUDServiceImpl.i().getSupporter(em, em.find(InvitationEntity.class, dto.getDtoA().getId().value()));

			// asdf3t4 SupporterEntity supporterEntityTo = em.find(SupporterEntity.class, dto.getDtoB().getId().value());
			// asdf3t4 System.out.println("creating edge from " + SupporterCRUDServiceImpl.i().getUserEntity(em, supporterEntityFrom).getLoginId().value() + " to " + SupporterCRUDServiceImpl.i().getUserEntity(em, supporterEntityTo).getLoginId().value());

			Collection<SupporterEntity> collectedAncestorSupporters = new ArrayList<>();

			handleAncestor(em, supporterEntityFrom, collectedAncestorSupporters);

		}

	}

	private void handleAncestor(EntityManager em, SupporterEntity aSupporterEntity, Collection<SupporterEntity> collectedAncestorSupporters) {

		if (!collectedAncestorSupporters.contains(aSupporterEntity)) {// operation only if not already executed via alternative path to root

			aSupporterEntity.setDecendentCount(aSupporterEntity.getDecendentCount() + 1);

			// asdf3t4 System.out.println("    decendantCount of " + SupporterCRUDServiceImpl.i().getUserEntity(em, aSupporterEntity).getLoginId().value() + " Supporter was incremented to " + aSupporterEntity.getDecendentCount());
			// em.persist(aSupporterEntity);

		}

		collectedAncestorSupporters.add(aSupporterEntity);

		{// recursion

			Collection<SupporterEntity> directAncestorSupporters = getDirectAncestors(em, aSupporterEntity);

			for (SupporterEntity supporterEntity : directAncestorSupporters) {

				handleAncestor(em, supporterEntity, collectedAncestorSupporters);

			}

		}

	}

	private Collection<SupporterEntity> getDirectAncestors(EntityManager em, SupporterEntity supporterEntity) {

		Collection<SupporterEntity> directAncestorSupporters = new ArrayList<>();

		for (Invitation2SupporterEntity invitation2SupporterEntity : supporterEntity.getInvitation2SupporterCollection()) {

			// asdf3t4 System.out.println("    found an invitation2SupporterEntities");

			for (Supporter2InvitationEntity supporter2InvitationEntity : invitation2SupporterEntity.getA().getSupporter2InvitationCollection()) {

				// asdf3t4 System.out.println("    found an supporter2InvitationEntity");

				directAncestorSupporters.add(supporter2InvitationEntity.getA());

			}

		}

		return directAncestorSupporters;

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
