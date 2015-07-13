package com.supeyou.core.web.client.rpc.supporter;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterFetchQuery;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCSupporterCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<SupporterDTO, SupporterFetchQuery> {

	SupporterDTO get(UserDTO userDTO, HeroDTO heroDTO) throws CRUDException;

	SupporterDTO getOrCreate(UserDTO userDTO, HeroDTO heroDTO) throws CRUDException;

}