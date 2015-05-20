package com.supeyou.actor.iface;

import com.supeyou.actor.iface.dto.HeroDTO;
import com.supeyou.actor.iface.dto.HeroFetchQuery;
import com.supeyou.crudie.iface.CRUDService;

public interface HeroCRUDService extends CRUDService<HeroDTO, HeroFetchQuery> {

}