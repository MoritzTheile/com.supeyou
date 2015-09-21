package com.supeyou.actor.web.server;

import java.lang.reflect.Method;

import javax.servlet.annotation.WebServlet;

import com.supeyou.actor.iface.dto.EventDTO;
import com.supeyou.actor.iface.dto.Session2EventDTO;
import com.supeyou.actor.iface.dto.Session2EventFetchQuery;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.impl.Session2EventCRUDServiceImpl;
import com.supeyou.actor.impl.SessionCRUDServiceImpl;
import com.supeyou.actor.web.client.rpc.session2event.RPCCRUDService;
import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.web.server.RPCAbstrCRUDServiceImpl;

@WebServlet("/RPCSession2EventCRUDServiceImpl")
public class RPCSession2EventCRUDServiceImpl extends RPCAbstrCRUDServiceImpl<Session2EventDTO, Session2EventFetchQuery> implements RPCCRUDService {

	private static final long serialVersionUID = 893032349766110L;

	@SuppressWarnings("unchecked")
	@Override
	public CRUDService<Session2EventDTO, Session2EventFetchQuery> getCRUDService() {

		// this reflection approach prevents compile-dependency to module com.supeyou.crudie.impl
		// it could be replaced by spring eventually
		try {
			Class<?> clazz = Class.forName("com.supeyou.actor.impl.Session2EventCRUDServiceImpl");
			Class<?>[] params = new Class<?>[0];
			Method method_i = clazz.getMethod("i", params);
			Object[] objects = new Object[0];
			return (CRUDService<Session2EventDTO, Session2EventFetchQuery>) method_i.invoke(null, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addEventToSession(String category, String action, String value) throws CRUDException {

		EventDTO eventDTO = new EventDTO();
		eventDTO.setCategory(new SingleLineString256Type(category));
		eventDTO.setAction(new SingleLineString256Type(action));
		eventDTO.setValue(new SingleLineString256Type(value));

		SessionDTO sessionDTO = SessionCRUDServiceImpl.i().getBySessionId(null, this.getThreadLocalRequest().getSession().getId());

		Session2EventCRUDServiceImpl.i().addEventToSession(getActor(), sessionDTO.getId(), eventDTO);

	}
}
