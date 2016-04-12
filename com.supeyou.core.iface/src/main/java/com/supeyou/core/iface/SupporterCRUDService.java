package com.supeyou.core.iface;

import java.util.List;

import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterFetchQuery;
import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.dto.UserDTO;

public interface SupporterCRUDService extends CRUDService<SupporterDTO, SupporterFetchQuery> {

	/**
	 * returns all supporter from given supporter to root supporter. Including given supporter, including root supporter.
	 */
	public List<SupporterDTO> getSupporterInPathToRoot(UserDTO actorDTO, SupporterDTO supporterDTO) throws CRUDException;

	/**
	 * Returns the Supporter between the given user and hero. Throws Exception if multiple Supporter exist.
	 */
	SupporterDTO get(UserDTO actorDTO, UserDTO userDTO, HeroDTO heroDTO) throws CRUDException;

	/**
	 * If no Supporter exists between given user and hero a new one is created. Otherwise the existing is returned.
	 */
	SupporterDTO getOrCreateRootSupporter(UserDTO actorDTO, HeroDTO heroDTO) throws CRUDException;

}