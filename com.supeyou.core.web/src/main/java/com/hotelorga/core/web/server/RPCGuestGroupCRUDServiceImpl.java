package com.hotelorga.core.web.server;

import java.lang.reflect.Method;

import javax.servlet.annotation.WebServlet;

import com.hotelorga.core.iface.dto.GuestGroupDTO;
import com.hotelorga.core.iface.dto.GuestGroupFetchQuery;
import com.hotelorga.core.web.client.rpc.guestgroup.RPCCRUDService;
import com.hotelorga.foundation.iface.CRUDService;
import com.hotelorga.foundation.web.server.RPCAbstrCRUDServiceImpl;

@WebServlet("/RPCGuestGroupCRUDServiceImpl")
public class RPCGuestGroupCRUDServiceImpl extends RPCAbstrCRUDServiceImpl<GuestGroupDTO, GuestGroupFetchQuery> implements RPCCRUDService {

	private static final long serialVersionUID = 893034576566110L;

	@SuppressWarnings("unchecked")
	@Override
	public CRUDService<GuestGroupDTO, GuestGroupFetchQuery> getCRUDService() {

		// this reflection approach prevents compile-dependency to module com.hotelorga.foundation.impl
		// it could be replaced by spring eventually
		try {
			Class<?> clazz = Class.forName("com.hotelorga.core.impl.GuestGroupCRUDServiceImpl");
			Class<?>[] params = new Class<?>[0];
			Method method_i = clazz.getMethod("i", params);
			Object[] objects = new Object[0];
			return (CRUDService<GuestGroupDTO, GuestGroupFetchQuery>) method_i.invoke(null, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
