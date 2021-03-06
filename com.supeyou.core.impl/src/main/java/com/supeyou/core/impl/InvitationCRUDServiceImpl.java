package com.supeyou.core.impl;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.InvitationCRUDService;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterFetchQuery;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.InvitationFetchQuery;
import com.supeyou.core.iface.dto.Supporter2InvitationDTO;
import com.supeyou.core.iface.dto.Supporter2InvitationFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.impl.entity.Hero2SupporterEntity;
import com.supeyou.core.impl.entity.InvitationEntity;
import com.supeyou.core.impl.entity.Supporter2InvitationEntity;
import com.supeyou.core.impl.entity.SupporterEntity;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.CRUDException.CODE;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.entity.UserEntity;
import com.supeyou.crudie.impl.util.Random;
import com.supeyou.crudie.impl.util.STATICS;
import com.supeyou.crudie.impl.util.TransactionTemplate;

public class InvitationCRUDServiceImpl extends AbstrCRUDServiceImpl<InvitationDTO, InvitationEntity, InvitationFetchQuery> implements InvitationCRUDService {

	@Override
	public InvitationDTO updadd(UserDTO actorDTO, InvitationDTO dto) throws CRUDException {
		if (dto.getId() == null) {
			throw new CRUDException(CODE.FUNCTION_NOT_AVAILABLE, "Method only supports updating objects. Use other methods for adding to ensure data integrity.");
		}
		return super.updadd(actorDTO, dto);
	}

	private InvitationDTO privateUpdadd(UserDTO actorDTO, InvitationDTO dto) throws CRUDException {

		return super.updadd(actorDTO, dto);
	}

	/**
	 * Implementing a query by iterating and searching is not performant, but shouldn't be a problem here. There is only a couple Invitation per User and only one Hero per Invitation.
	 */
	@Override
	public InvitationDTO get(UserDTO actorDTO, SupporterDTO supporterDTO) throws CRUDException {

		Supporter2InvitationFetchQuery dtoQuery = new Supporter2InvitationFetchQuery();

		dtoQuery.setIdA(supporterDTO.getId());

		for (Supporter2InvitationDTO supporter2InvitationDTO : Supporter2InvitationCRUDServiceImpl.i().fetchList(actorDTO, new Page(), dtoQuery)) {

			if (supporter2InvitationDTO.getDtoA().equals(supporterDTO)) {

				return supporter2InvitationDTO.getDtoB();

			}

		}

		return null;

	}

	@Override
	public InvitationDTO create(final UserDTO actorDTO, final SupporterDTO supporterDTO) throws CRUDException {

		return new TransactionTemplate<InvitationDTO>(actorDTO, STATICS.getEntityManager()) {

			public void checkPermissions(UserEntity actor) throws CRUDException {

				// STATICS.checkActorNotNull(actor);
				// STATICS.checkActorIsAdmin(actor);

			}

			protected InvitationDTO transactionBody() throws Exception {
				InvitationDTO invitationDTO = new InvitationDTO();
				// dd.MM.yyyy
				// invitationDTO.setComment(new SingleLineString256Type(new SimpleDateFormat("HH:mm:ss").format(new Date())));
				invitationDTO = privateUpdadd(actorDTO, invitationDTO);

				// all supporter need this association
				Supporter2InvitationDTO user2InvitationDTO = new Supporter2InvitationDTO();
				user2InvitationDTO.setDtoA(supporterDTO);
				user2InvitationDTO.setDtoB(invitationDTO);
				Supporter2InvitationCRUDServiceImpl.i().updadd(actorDTO, user2InvitationDTO);

				return invitationDTO;
			}

		}.execute();

	}

	private void acceptInvitation(UserDTO actorDTO, InvitationDTO invitationDTO, SupporterDTO supporterDTO) throws CRUDException {

		Invitation2SupporterFetchQuery dtoQuery = new Invitation2SupporterFetchQuery();
		dtoQuery.setIdA(invitationDTO.getId());
		dtoQuery.setIdB(supporterDTO.getId());
		if (Invitation2SupporterCRUDServiceImpl.i().fetchList(actorDTO, new Page(), dtoQuery).size() > 0) {
			// there should be max one invitation2supporter element
			return;
		}

		Invitation2SupporterDTO invitation2SupporterDTO = new Invitation2SupporterDTO();
		invitation2SupporterDTO.setDtoA(invitationDTO);
		invitation2SupporterDTO.setDtoB(supporterDTO);
		invitation2SupporterDTO.setTreeDestroying(alreadySupporter(actorDTO, supporterDTO));

		Invitation2SupporterCRUDServiceImpl.i().updadd(actorDTO, invitation2SupporterDTO);

	}

	private boolean alreadySupporter(UserDTO actorDTO, SupporterDTO supporterDTO) throws CRUDException {
		Invitation2SupporterFetchQuery dtoQuery = new Invitation2SupporterFetchQuery();
		dtoQuery.setIdB(supporterDTO.getId());
		return Invitation2SupporterCRUDServiceImpl.i().fetchList(actorDTO, new Page(), dtoQuery).size() > 0;
	}

	private HeroDTO getHero(UserDTO actorDTO, final InvitationDTO invitationDTO) throws CRUDException {

		return new TransactionTemplate<HeroDTO>(actorDTO, STATICS.getEntityManager()) {

			public void checkPermissions(UserEntity actor) throws CRUDException {

				// STATICS.checkActorNotNull(actor);
				// STATICS.checkActorIsAdmin(actor);

			}

			protected HeroDTO transactionBody() throws Exception {

				InvitationEntity invitationEntity = em.find(InvitationEntity.class, invitationDTO.getId().value());

				// there has to be one supporter per invitation exactly:
				for (Supporter2InvitationEntity supporter2Invitation : invitationEntity.getSupporter2InvitationCollection()) {
					System.out.println("supporter2Invitation found");
					// there has to be one hero per supporter exactly:
					for (Hero2SupporterEntity hero2SupporterEntity : supporter2Invitation.getA().getHero2SupporterCollection()) {
						System.out.println("hero2SupporterEntity.getA().getWebsiteURL()=" + hero2SupporterEntity.getA().getWebsiteURL());

						return HeroCRUDServiceImpl.i().helper.entity2DTO(hero2SupporterEntity.getA());

					}

				}

				return null;

			}
		}.execute();

	}

	protected SupporterEntity getSupporter(EntityManager em, InvitationEntity invitationEntity) {

		for (Supporter2InvitationEntity supporter2InvitationEntity : invitationEntity.getSupporter2InvitationCollection()) {
			return supporter2InvitationEntity.getA();
		}

		return null;
	}

	@Override
	public SupporterDTO followInvitation(UserDTO actorDTO, UserDTO userDTO, SingleLineString256Type token) throws CRUDException {

		InvitationDTO invitationDTO = null;

		{// retrieving invitationDTO by token
			InvitationFetchQuery dtoQuery = new InvitationFetchQuery();
			dtoQuery.setToken(token);

			for (InvitationDTO invitationDTO_current : InvitationCRUDServiceImpl.i().fetchList(actorDTO, new Page(), dtoQuery)) {

				invitationDTO = invitationDTO_current;

			}

			if (invitationDTO == null) {
				throw new CRUDException(CODE.NO_ENTITY_FOUND_BY_GIVEN_ID, "there was no invitation with token=" + token);
			}
		}

		System.out.println("invitationDTO=" + invitationDTO);

		HeroDTO heroDTO = getHero(actorDTO, invitationDTO);

		System.out.println("heroDTO=" + heroDTO);

		SupporterDTO supporterDTO = SupporterCRUDServiceImpl.i().getOrCreate(actorDTO, heroDTO, userDTO);

		acceptInvitation(actorDTO, invitationDTO, supporterDTO);

		return supporterDTO;
	}

	@Override
	protected void beforeUpdadd(EntityManager em, UserEntity actor, InvitationDTO dto) {
		if (dto.getToken() == null) {
			dto.setToken(new SingleLineString256Type(Random.randomKey(6)));
		}
		super.beforeUpdadd(em, actor, dto);
	}

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, InvitationFetchQuery query) {

		String whereClause = "";

		if (query.getToken() != null) {

			whereClause = "where " + " token" + "= '" + query.getToken() + "'";

		} else if (query.getSearchString() != null && !query.getSearchString().isEmpty()) {

			whereClause = "where " + "comment " + " like '%" + query.getSearchString() + "%'";

		}

		return whereClause;
	}

	// Singleton

	private static InvitationCRUDServiceImpl service;

	private InvitationCRUDServiceImpl() {
		super(InvitationDTO.class, InvitationEntity.class);
	}

	public static InvitationCRUDServiceImpl i() {
		if (service == null) {
			service = new InvitationCRUDServiceImpl();
		}
		return service;
	}

}
