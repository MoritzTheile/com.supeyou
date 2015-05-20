package com.supeyou.actor.web.server;

import java.lang.reflect.Method;

import javax.servlet.annotation.WebServlet;

import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.iface.dto.SessionFetchQuery;
import com.supeyou.actor.web.client.rpc.session.RPCCRUDService;
import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.web.server.RPCAbstrCRUDServiceImpl;

@WebServlet("/RPCSessionCRUDServiceImpl")
public class RPCSessionCRUDServiceImpl extends RPCAbstrCRUDServiceImpl<SessionDTO, SessionFetchQuery> implements RPCCRUDService {

	private static final long serialVersionUID = 893034576566110L;

	@SuppressWarnings("unchecked")
	@Override
	public CRUDService<SessionDTO, SessionFetchQuery> getCRUDService() {

		// this reflection approach prevents compile-dependency to module com.supeyou.crudie.impl
		// it could be replaced by spring eventually
		try {
			Class<?> clazz = Class.forName("com.supeyou.actor.impl.SessionCRUDServiceImpl");
			Class<?>[] params = new Class<?>[0];
			Method method_i = clazz.getMethod("i", params);
			Object[] objects = new Object[0];
			return (CRUDService<SessionDTO, SessionFetchQuery>) method_i.invoke(null, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
