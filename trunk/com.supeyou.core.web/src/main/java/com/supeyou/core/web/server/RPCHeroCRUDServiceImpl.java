package com.supeyou.core.web.server;

import java.lang.reflect.Method;

import javax.servlet.annotation.WebServlet;

import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.HeroFetchQuery;
import com.supeyou.core.web.client.rpc.hero.RPCCRUDService;
import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.web.server.RPCAbstrCRUDServiceImpl;

@WebServlet("/RPCHeroCRUDServiceImpl")
public class RPCHeroCRUDServiceImpl extends RPCAbstrCRUDServiceImpl<HeroDTO, HeroFetchQuery> implements RPCCRUDService {

	private static final long serialVersionUID = 893034576566110L;

	@SuppressWarnings("unchecked")
	@Override
	public CRUDService<HeroDTO, HeroFetchQuery> getCRUDService() {

		// this reflection approach prevents compile-dependency to module com.supeyou.crudie.impl
		// it could be replaced by spring eventually
		try {
			Class<?> clazz = Class.forName("com.supeyou.core.impl.HeroCRUDServiceImpl");
			Class<?>[] params = new Class<?>[0];
			Method method_i = clazz.getMethod("i", params);
			Object[] objects = new Object[0];
			return (CRUDService<HeroDTO, HeroFetchQuery>) method_i.invoke(null, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
