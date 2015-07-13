package com.supeyou.core.web.server;

import java.lang.reflect.Method;

import javax.servlet.annotation.WebServlet;

import com.supeyou.core.iface.InvitationCRUDService;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.InvitationFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDService;
import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.web.server.RPCAbstrCRUDServiceImpl;

@WebServlet("/RPCInvitationCRUDServiceImpl")
public class RPCInvitationCRUDServiceImpl extends RPCAbstrCRUDServiceImpl<InvitationDTO, InvitationFetchQuery> implements RPCCRUDService {

	private static final long serialVersionUID = 893034576566110L;

	@Override
	public InvitationDTO create(SupporterDTO supporterDTO) throws CRUDException {
		return ((InvitationCRUDService) getCRUDService()).create(getActor(), supporterDTO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CRUDService<InvitationDTO, InvitationFetchQuery> getCRUDService() {

		// this reflection approach prevents compile-dependency to module com.supeyou.crudie.impl
		// it could be replaced by spring eventually
		try {
			Class<?> clazz = Class.forName("com.supeyou.core.impl.InvitationCRUDServiceImpl");
			Class<?>[] params = new Class<?>[0];
			Method method_i = clazz.getMethod("i", params);
			Object[] objects = new Object[0];
			return (CRUDService<InvitationDTO, InvitationFetchQuery>) method_i.invoke(null, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
