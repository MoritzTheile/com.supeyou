package com.supeyou.core.impl;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.SupporterCRUDService;
import com.supeyou.core.iface.dto.Hero2SupporterDTO;
import com.supeyou.core.iface.dto.Hero2SupporterFetchQuery;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterFetchQuery;
import com.supeyou.core.iface.dto.Supporter2InvitationDTO;
import com.supeyou.core.iface.dto.Supporter2InvitationFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterFetchQuery;
import com.supeyou.core.iface.dto.User2SupporterDTO;
import com.supeyou.core.iface.dto.User2SupporterFetchQuery;
import com.supeyou.core.impl.entity.SupporterEntity;
import com.supeyou.core.impl.entity.User2SupporterEntity;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.CRUDException.CODE;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.DTOFetchList;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.entity.UserEntity;
import com.supeyou.crudie.impl.util.STATICS;
import com.supeyou.crudie.impl.util.TransactionTemplate;

public class SupporterCRUDServiceImpl extends AbstrCRUDServiceImpl<SupporterDTO, SupporterEntity, SupporterFetchQuery> implements SupporterCRUDService {

	@Override
	public DTOFetchList<SupporterDTO> fetchList(UserDTO actorDTO, Page page, SupporterFetchQuery dtoQuery) throws CRUDException {
		if (dtoQuery.getInvitor() == null) {

			return super.fetchList(actorDTO, page, dtoQuery);

		} else {

			if (page.getPageSize() < Integer.MAX_VALUE) {

				throw new CRUDException(CODE.INVALID_PAGESIZE, "Paging is not yet supported when fetching children.");

			}

			DTOFetchList<SupporterDTO> fetchList = new DTOFetchList<>();

			Supporter2InvitationFetchQuery supporter2InvitationFetchQuery = new Supporter2InvitationFetchQuery();

			supporter2InvitationFetchQuery.setIdA(dtoQuery.getInvitor().getId());

			DTOFetchList<Supporter2InvitationDTO> supporter2Invitations = Supporter2InvitationCRUDServiceImpl.i().fetchList(actorDTO, new Page(), supporter2InvitationFetchQuery);

			for (Supporter2InvitationDTO supporter2InvitationDTO : supporter2Invitations) {

				Invitation2SupporterFetchQuery query = new Invitation2SupporterFetchQuery();

				query.setIdA(supporter2InvitationDTO.getDtoB().getId());

				DTOFetchList<Invitation2SupporterDTO> invitation2Supporters = Invitation2SupporterCRUDServiceImpl.i().fetchList(actorDTO, new Page(), query);

				for (Invitation2SupporterDTO invitation2SupporterDTO : invitation2Supporters) {

					fetchList.add(invitation2SupporterDTO.getDtoB());

				}

			}

			return fetchList;

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

				return supporter;
			}

		}.execute();

	}

	@Override
	protected void postprocessEntity2DTO(EntityManager em, SupporterEntity entity, SupporterDTO dto) throws Exception {

		for (User2SupporterEntity user2SupporterEntity : entity.getSupporter2UserCollection()) {
			dto.setTmpHeroName(user2SupporterEntity.getA().getLoginId());
			// there is only one user2supporter allowed but breaking anyway:
			break;
		}

		if (dto.getTmpHeroName() == null) {
			dto.setTmpHeroName(new SingleLineString256Type("unknown name"));
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
