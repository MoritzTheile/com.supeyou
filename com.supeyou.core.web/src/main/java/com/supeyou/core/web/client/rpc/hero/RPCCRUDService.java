package com.supeyou.core.web.client.rpc.hero;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.HeroFetchQuery;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCHeroCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<HeroDTO, HeroFetchQuery> {

	UserDTO getUser(HeroDTO heroDTO) throws CRUDException;

}