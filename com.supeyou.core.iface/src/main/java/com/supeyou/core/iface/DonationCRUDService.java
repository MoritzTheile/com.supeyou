package com.supeyou.core.iface;

import com.supeyou.core.iface.dto.DonationDTO;
import com.supeyou.core.iface.dto.DonationFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.dto.UserDTO;

public interface DonationCRUDService extends CRUDService<DonationDTO, DonationFetchQuery> {

	DonationDTO save(UserDTO actorDTO, SupporterDTO supporterDTO, DonationDTO donationDTO) throws CRUDException;

}