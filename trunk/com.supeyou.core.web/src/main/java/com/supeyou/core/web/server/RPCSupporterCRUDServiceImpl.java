package com.supeyou.core.web.server;

import java.lang.reflect.Method;

import javax.servlet.annotation.WebServlet;

import com.supeyou.core.iface.SupporterCRUDService;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterFetchQuery;
import com.supeyou.core.web.client.rpc.supporter.RPCCRUDService;
import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.web.server.RPCAbstrCRUDServiceImpl;

@WebServlet("/RPCSupporterCRUDServiceImpl")
public class RPCSupporterCRUDServiceImpl extends RPCAbstrCRUDServiceImpl<SupporterDTO, SupporterFetchQuery> implements RPCCRUDService {

	private static final long serialVersionUID = 893034576566110L;

	@Override
	public SupporterDTO get(UserDTO userDTO, HeroDTO heroDTO) throws CRUDException {
		return ((SupporterCRUDService) getCRUDService()).get(getActor(), userDTO, heroDTO);
	}

	@Override
	public SupporterDTO getOrCreateRootSupporter(HeroDTO heroDTO) throws CRUDException {
		return ((SupporterCRUDService) getCRUDService()).getOrCreateRootSupporter(getActor(), heroDTO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CRUDService<SupporterDTO, SupporterFetchQuery> getCRUDService() {

		// this reflection approach prevents compile-dependency to module com.supeyou.crudie.impl
		// it could be replaced by spring eventually
		try {
			Class<?> clazz = Class.forName("com.supeyou.core.impl.SupporterCRUDServiceImpl");
			Class<?>[] params = new Class<?>[0];
			Method method_i = clazz.getMethod("i", params);
			Object[] objects = new Object[0];
			return (CRUDService<SupporterDTO, SupporterFetchQuery>) method_i.invoke(null, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
