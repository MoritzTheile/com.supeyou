package com.supeyou.actor.web.server;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.supeyou.crudie.iface.datatype.types.FormattedTimeType;
import com.supeyou.crudie.iface.datatype.types.PositivIntegerType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.UserDTO;
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
	public void addEventToSession(SingleLineString256Type category, SingleLineString256Type action, SingleLineString256Type value, PositivIntegerType pageAgeSeconds) throws CRUDException {
		UserDTO actorDTO = getActor();

		SessionDTO sessionDTO = SessionCRUDServiceImpl.i().getBySessionId(null, this.getThreadLocalRequest().getSession().getId());

		EventDTO eventDTO = new EventDTO();

		eventDTO.setCategory(category);
		eventDTO.setAction(action);
		eventDTO.setValue(value);
		eventDTO.setPageAgeSeconds(pageAgeSeconds);
		eventDTO.setFormattedTimestamp(new FormattedTimeType(new SimpleDateFormat(FormattedTimeType.dateFormat).format(new Date())));

		eventDTO.setUserId(new SingleLineString256Type(actorDTO.getId() + ""));
		eventDTO.setUserLoginId(new SingleLineString256Type(actorDTO.getLoginId() + ""));
		eventDTO.setUserName(new SingleLineString256Type(actorDTO.getName() + ""));
		eventDTO.setSessionId(new SingleLineString256Type(sessionDTO.getId() + ""));

		Session2EventCRUDServiceImpl.i().addEventToSession(actorDTO, sessionDTO.getId(), eventDTO);

	}
}
