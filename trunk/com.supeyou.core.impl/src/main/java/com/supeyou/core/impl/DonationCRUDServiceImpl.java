package com.supeyou.core.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.supeyou.core.iface.DonationCRUDService;
import com.supeyou.core.iface.dto.DonationDTO;
import com.supeyou.core.iface.dto.DonationFetchQuery;
import com.supeyou.core.iface.dto.Supporter2DonationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.impl.entity.DonationEntity;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;
import com.supeyou.crudie.impl.entity.UserEntity;

public class DonationCRUDServiceImpl extends AbstrCRUDServiceImpl<DonationDTO, DonationEntity, DonationFetchQuery> implements DonationCRUDService {

	private Logger log = Logger.getLogger(DonationCRUDServiceImpl.class.getName());

	@Override
	public DonationDTO save(UserDTO actorDTO, SupporterDTO supporterDTO, DonationDTO donationDTO) throws CRUDException {

		log.log(Level.INFO, "donationDTO = " + donationDTO);

		if (supporterDTO.getId() == null) {

			log.log(Level.WARNING, "supporter has no id");

			return donationDTO;

		}

		donationDTO = DonationCRUDServiceImpl.i().updadd(actorDTO, donationDTO);

		Supporter2DonationDTO supporter2DonationDTO = new Supporter2DonationDTO();
		supporter2DonationDTO.setDtoA(supporterDTO);
		supporter2DonationDTO.setDtoB(donationDTO);
		Supporter2DonationCRUDServiceImpl.i().updadd(actorDTO, supporter2DonationDTO);

		return donationDTO;

	}

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, DonationFetchQuery query) {

		String whereClause = "";

		if (query.getTxnId() != null) {

			whereClause += "where txnId='" + query.getTxnId().value() + "' ";

		}

		return whereClause;

	}

	// Singleton

	private static DonationCRUDServiceImpl service;

	private DonationCRUDServiceImpl() {
		super(DonationDTO.class, DonationEntity.class);
	}

	public static DonationCRUDServiceImpl i() {
		if (service == null) {
			service = new DonationCRUDServiceImpl();
		}
		return service;
	}

}
