package com.supeyou.core.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.supeyou.core.iface.DonationCRUDService;
import com.supeyou.core.iface.dto.DonationDTO;
import com.supeyou.core.iface.dto.DonationFetchQuery;
import com.supeyou.core.iface.dto.Supporter2DonationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterIDType;
import com.supeyou.core.impl.entity.DonationEntity;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.AbstrCRUDServiceImpl;

public class DonationCRUDServiceImpl extends AbstrCRUDServiceImpl<DonationDTO, DonationEntity, DonationFetchQuery> implements DonationCRUDService {

	private Logger log = Logger.getLogger(DonationCRUDServiceImpl.class.getName());

	@Override
	public void save(UserDTO actorDTO, final DonationDTO donationDTO) throws Exception {

		log.log(Level.INFO, "donationDTO = " + donationDTO);

		DonationCRUDServiceImpl.i().updadd(actorDTO, donationDTO);

		SupporterDTO supporterDTO = SupporterCRUDServiceImpl.i().get(actorDTO, new SupporterIDType(donationDTO.getItemNumber().value()));

		if (supporterDTO == null) {

			log.log(Level.WARNING, "no supporter with id " + donationDTO.getItemNumber() + "found");

			return;

		}

		Supporter2DonationDTO supporter2DonationDTO = new Supporter2DonationDTO();
		supporter2DonationDTO.setDtoA(supporterDTO);
		supporter2DonationDTO.setDtoB(donationDTO);
		Supporter2DonationCRUDServiceImpl.i().updadd(actorDTO, supporter2DonationDTO);

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
