package com.supeyou.core.iface;

import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterFetchQuery;
import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.types.AmountType;
import com.supeyou.crudie.iface.dto.UserDTO;

public interface SupporterCRUDService extends CRUDService<SupporterDTO, SupporterFetchQuery> {

	/**
	 * Returns the Supporter between the given user and hero. Throws Exception if multiple Supporter exist.
	 */
	SupporterDTO get(UserDTO actorDTO, UserDTO userDTO, HeroDTO heroDTO) throws CRUDException;

	/**
	 * If no Supporter exists between given user and hero a new one is created. Otherwise the existing is returned.
	 */
	SupporterDTO getOrCreateRootSupporter(UserDTO actorDTO, HeroDTO heroDTO) throws CRUDException;

	AmountType calculateDonationAmount(UserDTO admin, SupporterDTO supporter_Hermann) throws CRUDException;

}