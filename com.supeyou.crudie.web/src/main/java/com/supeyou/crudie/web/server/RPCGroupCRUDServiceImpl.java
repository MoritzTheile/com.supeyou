package com.supeyou.crudie.web.server;

import java.lang.reflect.Method;

import javax.servlet.annotation.WebServlet;

import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.iface.dto.GroupDTO;
import com.supeyou.crudie.iface.dto.GroupFetchQuery;
import com.supeyou.crudie.web.client.rpc.group.RPCCRUDService;

@WebServlet("/RPCGroupCRUDServiceImpl")
public class RPCGroupCRUDServiceImpl extends RPCAbstrCRUDServiceImpl<GroupDTO, GroupFetchQuery> implements RPCCRUDService {

	private static final long serialVersionUID = 893034576566110L;

	@SuppressWarnings("unchecked")
	@Override
	public CRUDService<GroupDTO, GroupFetchQuery> getCRUDService() {

		// this reflection approach prevents compile-dependency to module com.supeyou.crudie.impl
		// it could be replaced by spring eventually
		try {
			Class<?> clazz = Class.forName("com.supeyou.crudie.impl.GroupCRUDServiceImpl");
			Class<?>[] params = new Class<?>[0];
			Method method_i = clazz.getMethod("i", params);
			Object[] objects = new Object[0];
			return (CRUDService<GroupDTO, GroupFetchQuery>) method_i.invoke(null, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
