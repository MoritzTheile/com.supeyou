package com.hotelorga.core.web.server;

import java.lang.reflect.Method;

import javax.servlet.annotation.WebServlet;

import com.hotelorga.core.iface.dto.Guest2AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2AcceptanceFetchQuery;
import com.hotelorga.core.web.client.rpc.guest2acceptance.RPCCRUDService;
import com.hotelorga.foundation.iface.CRUDService;
import com.hotelorga.foundation.web.server.RPCAbstrCRUDServiceImpl;

@WebServlet("/RPCGuest2AcceptanceCRUDServiceImpl")
public class RPCGuest2AcceptanceCRUDServiceImpl extends RPCAbstrCRUDServiceImpl<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery> implements RPCCRUDService {

	private static final long serialVersionUID = 893034576566110L;

	@SuppressWarnings("unchecked")
	@Override
	public CRUDService<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery> getCRUDService() {

		// this reflection approach prevents compile-dependency to module com.hotelorga.foundation.impl
		// it could be replaced by spring eventually
		try {
			Class<?> clazz = Class.forName("com.hotelorga.core.impl.Guest2AcceptanceCRUDServiceImpl");
			Class<?>[] params = new Class<?>[0];
			Method method_i = clazz.getMethod("i", params);
			Object[] objects = new Object[0];
			return (CRUDService<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery>) method_i.invoke(null, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
