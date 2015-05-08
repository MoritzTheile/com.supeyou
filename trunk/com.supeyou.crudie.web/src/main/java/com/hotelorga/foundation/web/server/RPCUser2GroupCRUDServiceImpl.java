package com.hotelorga.foundation.web.server;

import java.lang.reflect.Method;

import javax.servlet.annotation.WebServlet;

import com.hotelorga.foundation.iface.CRUDService;
import com.hotelorga.foundation.iface.dto.User2GroupDTO;
import com.hotelorga.foundation.iface.dto.User2GroupFetchQuery;
import com.hotelorga.foundation.web.client.rpc.user2group.RPCCRUDService;

@WebServlet("/RPCUser2GroupCRUDServiceImpl")
public class RPCUser2GroupCRUDServiceImpl extends RPCAbstrCRUDServiceImpl<User2GroupDTO, User2GroupFetchQuery> implements RPCCRUDService {

	private static final long serialVersionUID = 893034576566110L;

	@SuppressWarnings("unchecked")
	@Override
	public CRUDService<User2GroupDTO, User2GroupFetchQuery> getCRUDService() {

		// this reflection approach prevents compile-dependency to module com.hotelorga.foundation.impl
		// it could be replaced by spring eventually
		try {
			Class<?> clazz = Class.forName("com.hotelorga.foundation.impl.User2GroupCRUDServiceImpl");
			Class<?>[] params = new Class<?>[0];
			Method method_i = clazz.getMethod("i", params);
			Object[] objects = new Object[0];
			return (CRUDService<User2GroupDTO, User2GroupFetchQuery>) method_i.invoke(null, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
