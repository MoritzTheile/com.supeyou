package com.supeyou.core.impl;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.HeroCRUDService;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.HeroFetchQuery;
import com.supeyou.core.iface.dto.User2HeroDTO;
import com.supeyou.core.iface.dto.User2HeroFetchQuery;
import com.supeyou.core.impl.entity.HeroEntity;
import com.supeyou.core.impl.entity.User2HeroEntity;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.CRUDException.CODE;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.UserCRUDServiceImpl;
import com.supeyou.crudie.impl.entity.UserEntity;
import com.supeyou.crudie.impl.util.STATICS;
import com.supeyou.crudie.impl.util.TransactionTemplate;

public class HeroCRUDServiceImpl extends AbstrCRUDServiceImpl<HeroDTO, HeroEntity, HeroFetchQuery> implements HeroCRUDService {

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, HeroFetchQuery query) {

		String whereClause = "";

		if (query.getSearchString() != null && !query.getSearchString().isEmpty()) {

			whereClause += " " + "lower(name)" + " like '%" + query.getSearchString().toLowerCase() + "%' AND ";

		}

		if (query.showActiveOnly()) {

			whereClause += " " + "active='true' AND ";

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
	public HeroDTO updadd(UserDTO actorDTO, HeroDTO dto) throws CRUDException {
		if (dto.getId() == null) {
			throw new CRUDException(CODE.FUNCTION_NOT_AVAILABLE, "Method only supports updating objects. Use other methods for adding to ensure data integrity.");
		}
		return super.updadd(actorDTO, dto);
	}

	private HeroDTO privateUpdadd(UserDTO actorDTO, HeroDTO dto) throws CRUDException {

		return super.updadd(actorDTO, dto);
	}

	/**
	 * Implementing a query by iterating and searching is not performant, but shouldn't be a problem here. There is only a couple Supporter per User and only one Hero per Supporter.
	 */
	@Override
	public HeroDTO get(UserDTO actorDTO, UserDTO userDTO) throws CRUDException {

		User2HeroFetchQuery user2HeroDTOQuery = new User2HeroFetchQuery();

		user2HeroDTOQuery.setIdA(userDTO.getId());

		for (User2HeroDTO user2Hero : User2HeroCRUDServiceImpl.i().fetchList(actorDTO, new Page(), user2HeroDTOQuery)) {

			// max one user2hero asso is allowed:
			return user2Hero.getDtoB();

		}

		return null;

	}

	@Override
	public HeroDTO getOrCreate(final UserDTO actorDTO, final UserDTO userDTO) throws CRUDException {

		return new TransactionTemplate<HeroDTO>(actorDTO, STATICS.getEntityManager()) {

			public void checkPermissions(UserEntity actor) throws CRUDException {

				// STATICS.checkActorNotNull(actor);
				// STATICS.checkActorIsAdmin(actor);

			}

			protected HeroDTO transactionBody() throws Exception {

				HeroDTO hero = get(actorDTO, userDTO);

				if (hero == null) {

					hero = privateUpdadd(actorDTO, new HeroDTO());

					// all supporter need this association
					User2HeroDTO user2HeroDTO = new User2HeroDTO();
					user2HeroDTO.setDtoA(userDTO);
					user2HeroDTO.setDtoB(hero);
					User2HeroCRUDServiceImpl.i().updadd(actorDTO, user2HeroDTO);

				}

				return hero;
			}

		}.execute();

	}

	@Override
	public UserDTO getUser(UserDTO actorDTO, final HeroDTO heroDTO) throws CRUDException {

		return new TransactionTemplate<UserDTO>(actorDTO, STATICS.getEntityManager()) {

			public void checkPermissions(UserEntity actor) throws CRUDException {

				// STATICS.checkActorNotNull(actor);
				// STATICS.checkActorIsAdmin(actor);

			}

			protected UserDTO transactionBody() throws Exception {
				HeroEntity heroEntity = em.find(HeroEntity.class, heroDTO.getId().value());
				for (User2HeroEntity user2HeroEntity : heroEntity.getUser2HeroCollection()) {
					return UserCRUDServiceImpl.i().helper.entity2DTO(user2HeroEntity.getA());
				}
				return null;
			}

		}.execute();
	}

	// Singleton

	private static HeroCRUDServiceImpl service;

	private HeroCRUDServiceImpl() {
		super(HeroDTO.class, HeroEntity.class);
	}

	public static HeroCRUDServiceImpl i() {
		if (service == null) {
			service = new HeroCRUDServiceImpl();
		}
		return service;
	}

}
