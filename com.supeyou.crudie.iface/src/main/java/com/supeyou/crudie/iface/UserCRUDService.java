package com.supeyou.crudie.iface;

import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.iface.dto.UserFetchQuery;

public interface UserCRUDService extends CRUDService<UserDTO, UserFetchQuery> {

	/**
	 * This method is necessary to solve the chicken egg problem.
	 * 
	 * This service return an admin if none exists one will be created.
	 * 
	 * This service shall never be exposed to the outside world for security reasons.
	 * 
	 * @throws CRUDException
	 * 
	 */
	public UserDTO getInitialAdmin() throws CRUDException;
}