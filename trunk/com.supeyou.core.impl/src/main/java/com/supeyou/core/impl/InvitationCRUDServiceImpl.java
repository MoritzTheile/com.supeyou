package com.supeyou.core.impl;

import com.supeyou.core.iface.InvitationCRUDService;
import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.InvitationFetchQuery;
import com.supeyou.core.iface.dto.Supporter2InvitationDTO;
import com.supeyou.core.iface.dto.Supporter2InvitationFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.impl.entity.InvitationEntity;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.CRUDException.CODE;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.entity.UserEntity;
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

				InvitationDTO invitationDTO = privateUpdadd(actorDTO, new InvitationDTO());

				// all supporter need this association
				Supporter2InvitationDTO user2InvitationDTO = new Supporter2InvitationDTO();
				user2InvitationDTO.setDtoA(supporterDTO);
				user2InvitationDTO.setDtoB(invitationDTO);
				Supporter2InvitationCRUDServiceImpl.i().updadd(actorDTO, user2InvitationDTO);

				return invitationDTO;
			}

		}.execute();

	}

	@Override
	public void acceptInvitation(UserDTO actorDTO, InvitationDTO invitationDTO, SupporterDTO supporterDTO) throws CRUDException {

		Invitation2SupporterDTO invitation2SupporterDTO = new Invitation2SupporterDTO();
		invitation2SupporterDTO.setDtoA(invitationDTO);
		invitation2SupporterDTO.setDtoB(supporterDTO);
		Invitation2SupporterCRUDServiceImpl.i().updadd(actorDTO, invitation2SupporterDTO);

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
