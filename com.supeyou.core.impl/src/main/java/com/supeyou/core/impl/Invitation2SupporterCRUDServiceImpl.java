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
import com.supeyou.core.impl.entity.Supporter2InvitationEntity;
import com.supeyou.core.impl.entity.SupporterEntity;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.CRUDException.CODE;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.dto.DTOFetchList;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.dtohelper.AbstrDTOHelper;
import com.supeyou.crudie.impl.entity.UserEntity;
import com.supeyou.crudie.impl.util.STATICS;
import com.supeyou.crudie.impl.util.TransactionTemplate;

public class Invitation2SupporterCRUDServiceImpl extends AbstrCRUDServiceImpl<Invitation2SupporterDTO, Invitation2SupporterEntity, Invitation2SupporterFetchQuery> implements CRUDAssoService<InvitationDTO, SupporterDTO, Invitation2SupporterIDType, Invitation2SupporterDTO, Invitation2SupporterFetchQuery> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private AbstrDTOHelper<InvitationEntity, InvitationDTO> aHelper;
	private AbstrDTOHelper<SupporterEntity, SupporterDTO> bHelper;

	@Override
	public DTOFetchList<Invitation2SupporterDTO> fetchList(UserDTO actorDTO, Page page, final Invitation2SupporterFetchQuery dtoQuery) throws CRUDException {

		if (dtoQuery.getInvitor() == null) {

			return super.fetchList(actorDTO, page, dtoQuery);

		} else {

			if (page.getPageSize() < Integer.MAX_VALUE) {

				throw new CRUDException(CODE.INVALID_PAGESIZE, "Paging is not supported when fetching children.");

			}

			return new TransactionTemplate<DTOFetchList<Invitation2SupporterDTO>>(actorDTO, STATICS.getEntityManager()) {

				public void checkPermissions(UserEntity actor) throws CRUDException {

					// STATICS.checkActorNotNull(actor);
					// STATICS.checkActorIsAdmin(actor);

				}

				protected DTOFetchList<Invitation2SupporterDTO> transactionBody() throws Exception {

					DTOFetchList<Invitation2SupporterDTO> fetchList = new DTOFetchList<Invitation2SupporterDTO>();

					SupporterEntity parent = em.find(SupporterEntity.class, dtoQuery.getInvitor().getId().value());

					for (Supporter2InvitationEntity entity : parent.getSupporter2invitationCollection()) {

						for (Invitation2SupporterEntity invitation2SupporterEntity : entity.getB().getInvitation2SupporterCollection()) {

							Invitation2SupporterDTO dto = helper.entity2DTO(invitation2SupporterEntity);

							postprocessEntity2DTO(em, invitation2SupporterEntity, dto);

							SupporterCRUDServiceImpl.i().postprocessEntity2DTO(em, invitation2SupporterEntity.getB(), dto.getDtoB());

							fetchList.add(dto);

						}

					}

					return fetchList;
				}
			}.execute();

		}

	}

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

				SupporterDTO oldSupporterDTO = getDTOFromEntity(em, supporterEntity);

				supporterEntity.setDecendentCount(sum);

				em.flush();

				SupporterDTO newSupporterDTO = getDTOFromEntity(em, em.find(SupporterEntity.class, supporterEntity.getId().value()));

				SupporterCRUDServiceImpl.i().wasUpdated(newSupporterDTO, oldSupporterDTO);

			}

			private SupporterDTO getDTOFromEntity(EntityManager em, SupporterEntity supporterEntity) {

				try {
					SupporterDTO supporterDTO = SupporterCRUDServiceImpl.i().helper.entity2DTO(supporterEntity);
					SupporterCRUDServiceImpl.i().postprocessEntity2DTO(em, supporterEntity, supporterDTO);
					return supporterDTO;
				} catch (Exception e) {
					log.log(Level.WARNING, "Exception during dto mapping:", e);
				}

				return null;

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
