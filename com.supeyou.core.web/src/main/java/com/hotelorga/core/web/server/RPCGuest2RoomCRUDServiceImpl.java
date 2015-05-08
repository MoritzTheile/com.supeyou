package com.hotelorga.core.web.server;

import java.lang.reflect.Method;

import javax.servlet.annotation.WebServlet;

import com.hotelorga.core.iface.dto.Guest2RoomDTO;
import com.hotelorga.core.iface.dto.Guest2RoomFetchQuery;
import com.hotelorga.core.web.client.rpc.guest2room.RPCCRUDService;
import com.hotelorga.foundation.iface.CRUDService;
import com.hotelorga.foundation.web.server.RPCAbstrCRUDServiceImpl;

@WebServlet("/RPCGuest2RoomCRUDServiceImpl")
public class RPCGuest2RoomCRUDServiceImpl extends RPCAbstrCRUDServiceImpl<Guest2RoomDTO, Guest2RoomFetchQuery> implements RPCCRUDService {

	private static final long serialVersionUID = 893034576566110L;

	@SuppressWarnings("unchecked")
	@Override
	public CRUDService<Guest2RoomDTO, Guest2RoomFetchQuery> getCRUDService() {

		// this reflection approach prevents compile-dependency to module com.hotelorga.foundation.impl
		// it could be replaced by spring eventually
		try {
			Class<?> clazz = Class.forName("com.hotelorga.core.impl.Guest2RoomCRUDServiceImpl");
			Class<?>[] params = new Class<?>[0];
			Method method_i = clazz.getMethod("i", params);
			Object[] objects = new Object[0];
			return (CRUDService<Guest2RoomDTO, Guest2RoomFetchQuery>) method_i.invoke(null, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
