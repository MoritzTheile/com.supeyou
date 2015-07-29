package com.supeyou.core.iface;

import com.supeyou.core.iface.dto.DonationDTO;
import com.supeyou.core.iface.dto.DonationFetchQuery;
import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.iface.dto.UserDTO;

public interface DonationCRUDService extends CRUDService<DonationDTO, DonationFetchQuery> {

	void save(UserDTO actorDTO, DonationDTO donationDTO) throws Exception;

}