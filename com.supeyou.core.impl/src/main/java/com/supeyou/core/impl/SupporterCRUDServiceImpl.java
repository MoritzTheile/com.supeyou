package com.supeyou.core.impl;

import com.supeyou.core.iface.SupporterCRUDService;
import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterFetchQuery;
import com.supeyou.core.iface.dto.Supporter2InvitationDTO;
import com.supeyou.core.iface.dto.Supporter2InvitationFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterFetchQuery;
import com.supeyou.core.impl.entity.SupporterEntity;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.CRUDException.CODE;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.dto.DTOFetchList;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;

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
