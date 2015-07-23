package com.supeyou.core.iface;

import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.HeroFetchQuery;
import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.dto.UserDTO;

public interface HeroCRUDService extends CRUDService<HeroDTO, HeroFetchQuery> {

	HeroDTO getOrCreate(UserDTO admin, UserDTO user) throws CRUDException;

	HeroDTO get(UserDTO actorDTO, UserDTO userDTO) throws CRUDException;

	UserDTO getUser(UserDTO actorDTO, HeroDTO heroDTO) throws CRUDException;
}