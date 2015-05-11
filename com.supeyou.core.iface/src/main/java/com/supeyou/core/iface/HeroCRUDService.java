package com.supeyou.core.iface;

import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.HeroFetchQuery;
import com.supeyou.crudie.iface.CRUDService;

public interface HeroCRUDService extends CRUDService<HeroDTO, HeroFetchQuery> {

}