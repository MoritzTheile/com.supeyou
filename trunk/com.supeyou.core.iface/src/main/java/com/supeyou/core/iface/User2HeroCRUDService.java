package com.supeyou.core.iface;

import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.User2HeroFetchQuery;
import com.supeyou.core.iface.dto.User2HeroIDType;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.iface.dto.AbstrAssoDTO;
import com.supeyou.crudie.iface.dto.UserDTO;

public interface User2HeroCRUDService extends CRUDAssoService<UserDTO, HeroDTO, User2HeroIDType, AbstrAssoDTO<UserDTO, HeroDTO, User2HeroIDType>, User2HeroFetchQuery> {

}