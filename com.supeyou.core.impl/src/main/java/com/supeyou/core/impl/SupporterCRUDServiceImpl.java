package com.supeyou.core.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.SupporterCRUDService;
import com.supeyou.core.iface.dto.Hero2SupporterDTO;
import com.supeyou.core.iface.dto.Hero2SupporterFetchQuery;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterFetchQuery;
import com.supeyou.core.iface.dto.User2SupporterDTO;
import com.supeyou.core.iface.dto.User2SupporterFetchQuery;
import com.supeyou.core.impl.entity.Hero2SupporterEntity;
import com.supeyou.core.impl.entity.Invitation2SupporterEntity;
import com.supeyou.core.impl.entity.Supporter2DonationEntity;
import com.supeyou.core.impl.entity.Supporter2InvitationEntity;
import com.supeyou.core.impl.entity.SupporterEntity;
import com.supeyou.core.impl.entity.User2SupporterEntity;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.CRUDException.CODE;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.datatype.types.AmountType;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.UserCRUDServiceImpl;
import com.supeyou.crudie.impl.entity.UserEntity;
import com.supeyou.crudie.impl.util.STATICS;
import com.supeyou.crudie.impl.util.TransactionTemplate;

public class SupporterCRUDServiceImpl extends AbstrCRUDServiceImpl<SupporterDTO, SupporterEntity, SupporterFetchQuery> implements SupporterCRUDService {

	private static final Logger log = Logger.getLogger(SupporterCRUDServiceImpl.class.getName());

	@Override
	public SupporterDTO updadd(UserDTO actorDTO, SupporterDTO dto) throws CRUDException {
		if (dto.getId() == null) {
			throw new CRUDException(CODE.FUNCTION_NOT_AVAILABLE, "Method only supports updating objects. Use other methods for adding to ensure data integrity.");
		}
		return super.updadd(actorDTO, dto);
	}

	private SupporterDTO privateUpdadd(UserDTO actorDTO, SupporterDTO dto) throws CRUDException {

		return super.updadd(actorDTO, dto);
	}

	/**
	 * Implementing a query by iterating and searching is not performant, but shouldn't be a problem here. There is only a couple Supporter per User and only one Hero per Supporter.
	 */
	@Override
	public SupporterDTO get(UserDTO actorDTO, UserDTO userDTO, HeroDTO heroDTO) throws CRUDException {

		User2SupporterFetchQuery user2SupporterDTOQuery = new User2SupporterFetchQuery();

		user2SupporterDTOQuery.setIdA(userDTO.getId());

		for (User2SupporterDTO user2Supporter : User2SupporterCRUDServiceImpl.i().fetchList(actorDTO, new Page(), user2SupporterDTOQuery)) {

			Hero2SupporterFetchQuery dtoQuery = new Hero2SupporterFetchQuery();

			dtoQuery.setIdB(user2Supporter.getDtoB().getId());

			for (Hero2SupporterDTO hero2SupporterDTO : Hero2SupporterCRUDServiceImpl.i().fetchList(actorDTO, new Page(), dtoQuery)) {

				if (hero2SupporterDTO.getDtoA().equals(heroDTO)) {

					return hero2SupporterDTO.getDtoB();

				}

			}

		}

		return null;

	}

	@Override
	public SupporterDTO getOrCreateRootSupporter(final UserDTO actorDTO, final HeroDTO heroDTO) throws CRUDException {

		return getOrCreate(actorDTO, heroDTO, HeroCRUDServiceImpl.i().getUser(actorDTO, heroDTO));

	}

	protected static UserEntity getUserEntity(EntityManager em, SupporterEntity supporterEntity) {

		for (User2SupporterEntity user2SupporterEntity : supporterEntity.getUser2SupporterCollection()) {
			// there is only one allowed:
			return user2SupporterEntity.getA();
		}

		return null;
	}

	/**
	 * This function is not part of core interface since it would make creation of multiple root supporter possible.
	 */
	protected SupporterDTO getOrCreate(final UserDTO actorDTO, final HeroDTO heroDTO, final UserDTO userDTO) throws CRUDException {

		return new TransactionTemplate<SupporterDTO>(actorDTO, STATICS.getEntityManager()) {

			public void checkPermissions(UserEntity actor) throws CRUDException {

				// STATICS.checkActorNotNull(actor);
				// STATICS.checkActorIsAdmin(actor);

			}

			protected SupporterDTO transactionBody() throws Exception {

				SupporterDTO supporter = get(actorDTO, userDTO, heroDTO);

				if (supporter == null) {

					supporter = privateUpdadd(actorDTO, new SupporterDTO());

					// all supporter need this association
					User2SupporterDTO user2SupporterDTO = new User2SupporterDTO();
					user2SupporterDTO.setDtoA(userDTO);
					user2SupporterDTO.setDtoB(supporter);
					User2SupporterCRUDServiceImpl.i().updadd(actorDTO, user2SupporterDTO);

					// all supporter need this association
					Hero2SupporterDTO hero2SupporterDTO = new Hero2SupporterDTO();
					hero2SupporterDTO.setDtoA(heroDTO);
					hero2SupporterDTO.setDtoB(supporter);
					Hero2SupporterCRUDServiceImpl.i().updadd(actorDTO, hero2SupporterDTO);

				}
				// dto has to be reloaded to consider new assoziations
				return get(actorDTO, supporter.getId());
			}

		}.execute();

	}

	@Override
	protected void postprocessEntity2DTO(EntityManager em, SupporterEntity entity, SupporterDTO dto) throws Exception {

		for (User2SupporterEntity user2SupporterEntity : entity.getUser2SupporterCollection()) {

			dto.setUserDTO(UserCRUDServiceImpl.i().helper.entity2DTO(user2SupporterEntity.getA()));

			// there is only one user2supporter allowed but breaking the loop anyway:
			break;

		}

		for (Hero2SupporterEntity user2SupporterEntity : entity.getHero2SupporterCollection()) {

			dto.setHeroDTO(HeroCRUDServiceImpl.i().helper.entity2DTO(user2SupporterEntity.getA()));

			// there is only one hero2supporter allowed but breaking the loop anyway:
			break;

		}

		for (Invitation2SupporterEntity user2SupporterEntity : entity.getInvitation2SupporterCollection()) {

			dto.getInvitationDTOs().add(InvitationCRUDServiceImpl.i().helper.entity2DTO(user2SupporterEntity.getA()));

		}

		dto.setOwnAmount(calculateOwnAmount(entity));

		super.postprocessEntity2DTO(em, entity, dto);

	}

	public static AmountType calculateOwnAmount(SupporterEntity entity) {

		int ownAmount = 0;

		for (Supporter2DonationEntity supporter2DonationEntity : entity.getSupporter2donationCollection()) {

			ownAmount += supporter2DonationEntity.getB().getPaymentAmount().value();

		}

		return new AmountType(ownAmount);

	}

	public static void executeActionAndRecurseOnAncestors(EntityManager em, SupporterEntity supporterEntityFrom, boolean onlyTreeAncestor, SupporterAction action) {

		System.out.println("\n\n---------------------------------------------------");
		System.out.println("asdfuuu handling ancestors of " + getUserEntity(em, supporterEntityFrom).getLoginId());

		Collection<SupporterEntity> collectedAncestorSupporters = new ArrayList<>();

		executeActionAndRecurseOnAncestors(em, supporterEntityFrom, collectedAncestorSupporters, onlyTreeAncestor, action);

	}

	public interface SupporterAction {
		void execute(EntityManager em, SupporterEntity supporterEntity);
	}

	private static void executeActionAndRecurseOnAncestors(EntityManager em, SupporterEntity aSupporterEntity, Collection<SupporterEntity> collectedAncestorSupporters, boolean onlyTreeAncestor, SupporterAction action) {

		Collection<SupporterEntity> directAncestorSupporters = getDirectAncestors(em, aSupporterEntity, onlyTreeAncestor);
		if (!collectedAncestorSupporters.contains(aSupporterEntity)) {// operation only if not already executed via alternative path to root

			action.execute(em, aSupporterEntity);

		}

		collectedAncestorSupporters.add(aSupporterEntity);

		for (SupporterEntity supporterEntity : directAncestorSupporters) {
			System.out.println("asdfuuu child:" + getUserEntity(em, aSupporterEntity).getLoginId() + " -> " + getUserEntity(em, supporterEntity).getLoginId());

			// recursion
			executeActionAndRecurseOnAncestors(em, supporterEntity, collectedAncestorSupporters, onlyTreeAncestor, action);

		}

	}

	private static Collection<SupporterEntity> getDirectAncestors(EntityManager em, SupporterEntity supporterEntity, boolean onlyTreeAncestor) {

		Collection<SupporterEntity> directAncestorSupporters = new ArrayList<>();

		for (Invitation2SupporterEntity invitation2SupporterEntity : supporterEntity.getInvitation2SupporterCollection()) {

			if (onlyTreeAncestor && invitation2SupporterEntity.getTreeDestroying()) {

				continue;

			}

			for (Supporter2InvitationEntity supporter2InvitationEntity : invitation2SupporterEntity.getA().getSupporter2InvitationCollection()) {

				directAncestorSupporters.add(supporter2InvitationEntity.getA());

			}

		}

		return directAncestorSupporters;

	}

	public static Collection<SupporterEntity> getDirectDecendants(EntityManager em, SupporterEntity supporterEntity, boolean treeDecendantsOnly) {

		System.out.println("    asdflasdl: " + getUserEntity(em, supporterEntity).getLoginId().value());
		Collection<SupporterEntity> directDecendantSupporters = new ArrayList<>();

		for (Supporter2InvitationEntity supporter2InvitationEntity : supporterEntity.getSupporter2invitationCollection()) {

			for (Invitation2SupporterEntity invitation2SupporterEntity : supporter2InvitationEntity.getB().getInvitation2SupporterCollection()) {

				UserEntity userEntity = getUserEntity(em, invitation2SupporterEntity.getB());
				System.out.println("    asdflasdl child: " + userEntity.getLoginId().value() + " " + invitation2SupporterEntity.getTreeDestroying());

				if (treeDecendantsOnly && invitation2SupporterEntity.getTreeDestroying()) {
					continue;
				}

				directDecendantSupporters.add(invitation2SupporterEntity.getB());

			}

		}

		return directDecendantSupporters;

	}

	public static SupporterDTO getDTOFromEntity(EntityManager em, SupporterEntity supporterEntity) {

		try {
			SupporterDTO supporterDTO = SupporterCRUDServiceImpl.i().helper.entity2DTO(supporterEntity);
			SupporterCRUDServiceImpl.i().postprocessEntity2DTO(em, supporterEntity, supporterDTO);
			return supporterDTO;
		} catch (Exception e) {
			log.log(Level.WARNING, "Exception during dto mapping:", e);
		}

		return null;

	}

	@Override
	public List<SupporterDTO> getSupporterInPathToRoot(UserDTO actorDTO, final SupporterDTO supporterDTO) throws CRUDException {

		return new TransactionTemplate<List<SupporterDTO>>(actorDTO, STATICS.getEntityManager()) {

			public void checkPermissions(UserEntity actor) throws CRUDException {

				// STATICS.checkActorNotNull(actor);
				// STATICS.checkActorIsAdmin(actor);

			}

			protected List<SupporterDTO> transactionBody() throws Exception {

				List<SupporterEntity> supporterEntityList = new ArrayList<>();

				recurseCollect(em, em.find(SupporterEntity.class, supporterDTO.getId().value()), supporterEntityList);

				List<SupporterDTO> supporterDTOList = new ArrayList<>();

				for (SupporterEntity entity : supporterEntityList) {

					SupporterDTO dto = helper.entity2DTO(entity);

					postprocessEntity2DTO(em, entity, dto);

					supporterDTOList.add(dto);

				}

				return supporterDTOList;

			}

			private void recurseCollect(EntityManager em, SupporterEntity aSupporterEntity, List<SupporterEntity> collectedAncestorSupporters) {

				Collection<SupporterEntity> directAncestorSupporters = getDirectAncestors(em, aSupporterEntity, true);

				collectedAncestorSupporters.add(aSupporterEntity);

				for (SupporterEntity supporterEntity : directAncestorSupporters) {

					// recursion
					recurseCollect(em, supporterEntity, collectedAncestorSupporters);

				}

			}

		}.execute();

	}

	// Singleton

	private static SupporterCRUDServiceImpl service;

	private SupporterCRUDServiceImpl() {
		super(SupporterDTO.class, SupporterEntity.class);
	}

	public static SupporterCRUDServiceImpl i() {
		if (service == null) {
			service = new SupporterCRUDServiceImpl();
		}
		return service;
	}

}
