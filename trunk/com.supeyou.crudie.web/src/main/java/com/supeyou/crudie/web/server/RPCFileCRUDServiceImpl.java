package com.supeyou.crudie.web.server;

import java.lang.reflect.Method;

import javax.servlet.annotation.WebServlet;

import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.iface.dto.FileDTO;
import com.supeyou.crudie.iface.dto.FileFetchQuery;
import com.supeyou.crudie.web.client.rpc.file.RPCCRUDService;

@WebServlet("/RPCFileCRUDServiceImpl")
public class RPCFileCRUDServiceImpl extends RPCAbstrCRUDServiceImpl<FileDTO, FileFetchQuery> implements RPCCRUDService {

	private static final long serialVersionUID = 893086966110L;

	@SuppressWarnings("unchecked")
	@Override
	public CRUDService<FileDTO, FileFetchQuery> getCRUDService() {

		// this reflection approach prevents compile-dependency to module com.supeyou.crudie.impl
		// it could be replaced by spring eventually
		try {
			Class<?> clazz = Class.forName("com.supeyou.crudie.impl.FileCRUDServiceImpl");
			Class<?>[] params = new Class<?>[0];
			Method method_i = clazz.getMethod("i", params);
			Object[] objects = new Object[0];
			return (CRUDService<FileDTO, FileFetchQuery>) method_i.invoke(null, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
