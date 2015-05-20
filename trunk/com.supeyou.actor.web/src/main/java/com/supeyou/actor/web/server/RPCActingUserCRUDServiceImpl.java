package com.supeyou.actor.web.server;

import java.lang.reflect.Method;

import javax.servlet.annotation.WebServlet;

import com.supeyou.actor.iface.dto.ActingUserDTO;
import com.supeyou.actor.iface.dto.ActingUserFetchQuery;
import com.supeyou.actor.web.client.rpc.actinguser.RPCCRUDService;
import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.web.server.RPCAbstrCRUDServiceImpl;

@WebServlet("/RPCActingUserCRUDServiceImpl")
public class RPCActingUserCRUDServiceImpl extends RPCAbstrCRUDServiceImpl<ActingUserDTO, ActingUserFetchQuery> implements RPCCRUDService {

	private static final long serialVersionUID = 893034576566110L;

	@SuppressWarnings("unchecked")
	@Override
	public CRUDService<ActingUserDTO, ActingUserFetchQuery> getCRUDService() {

		// this reflection approach prevents compile-dependency to module com.supeyou.crudie.impl
		// it could be replaced by spring eventually
		try {
			Class<?> clazz = Class.forName("com.supeyou.actor.impl.ActingUserCRUDServiceImpl");
			Class<?>[] params = new Class<?>[0];
			Method method_i = clazz.getMethod("i", params);
			Object[] objects = new Object[0];
			return (CRUDService<ActingUserDTO, ActingUserFetchQuery>) method_i.invoke(null, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
