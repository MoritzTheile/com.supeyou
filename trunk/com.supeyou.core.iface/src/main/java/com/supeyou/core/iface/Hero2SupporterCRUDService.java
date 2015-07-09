package com.supeyou.core.iface;

import com.supeyou.core.iface.dto.Hero2SupporterFetchQuery;
import com.supeyou.core.iface.dto.Hero2SupporterIDType;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.crudie.iface.CRUDAssoService;
import com.supeyou.crudie.iface.dto.AbstrAssoDTO;

public interface Hero2SupporterCRUDService extends CRUDAssoService<HeroDTO, SupporterDTO, Hero2SupporterIDType, AbstrAssoDTO<HeroDTO, SupporterDTO, Hero2SupporterIDType>, Hero2SupporterFetchQuery> {

}