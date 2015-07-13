package com.supeyou.core.impl;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.SupporterCRUDService;
import com.supeyou.core.iface.dto.Hero2SupporterDTO;
import com.supeyou.core.iface.dto.Hero2SupporterFetchQuery;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterFetchQuery;
import com.supeyou.core.iface.dto.User2SupporterDTO;
import com.supeyou.core.iface.dto.User2SupporterFetchQuery;
import com.supeyou.core.impl.entity.Invitation2SupporterEntity;
import com.supeyou.core.impl.entity.Supporter2InvitationEntity;
import com.supeyou.core.impl.entity.SupporterEntity;
import com.supeyou.core.impl.entity.User2SupporterEntity;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.CRUDException.CODE;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.dto.DTOFetchList;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.UserCRUDServiceImpl;
import com.supeyou.crudie.impl.entity.UserEntity;
import com.supeyou.crudie.impl.util.STATICS;
import com.supeyou.crudie.impl.util.TransactionTemplate;

public class SupporterCRUDServiceImpl extends AbstrCRUDServiceImpl<SupporterDTO, SupporterEntity, SupporterFetchQuery> implements SupporterCRUDService {

	@Override
	public DTOFetchList<SupporterDTO> fetchList(UserDTO actorDTO, final Page page, final SupporterFetchQuery dtoQuery) throws CRUDException {

		if (dtoQuery.getInvitor() == null) {

			return super.fetchList(actorDTO, page, dtoQuery);

		} else {

			if (page.getPageSize() < Integer.MAX_VALUE) {

				throw new CRUDException(CODE.INVALID_PAGESIZE, "Paging is not yet supported when fetching children.");

			}

			return new TransactionTemplate<DTOFetchList<SupporterDTO>>(actorDTO, STATICS.getEntityManager()) {

				public void checkPermissions(UserEntity actor) throws CRUDException {

					// STATICS.checkActorNotNull(actor);
					// STATICS.checkActorIsAdmin(actor);

				}

				protected DTOFetchList<SupporterDTO> transactionBody() throws Exception {

					DTOFetchList<SupporterDTO> fetchList = new DTOFetchList<SupporterDTO>();

					SupporterEntity parent = em.find(SupporterEntity.class, dtoQuery.getInvitor().getId().value());

					for (Supporter2InvitationEntity entity : parent.getSupporter2invitationCollection()) {

						for (Invitation2SupporterEntity invitation2SupporterEntity : entity.getB().getInvitation2SupporterEntity()) {

							SupporterDTO dto = helper.entity2DTO(invitation2SupporterEntity.getB());
							postprocessEntity2DTO(em, invitation2SupporterEntity.getB(), dto);
							fetchList.add(dto);

						}

					}

					return fetchList;
				}
			}.execute();

		}

	}

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
	public SupporterDTO getOrCreate(final UserDTO actorDTO, final UserDTO userDTO, final HeroDTO heroDTO) throws CRUDException {

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

		super.postprocessEntity2DTO(em, entity, dto);
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
