package com.hotelorga.foundation.web.server;

import java.lang.reflect.Method;

import javax.servlet.annotation.WebServlet;

import com.hotelorga.foundation.iface.CRUDService;
import com.hotelorga.foundation.iface.dto.FileDTO;
import com.hotelorga.foundation.iface.dto.FileFetchQuery;
import com.hotelorga.foundation.web.client.rpc.file.RPCCRUDService;

@WebServlet("/RPCFileCRUDServiceImpl")
public class RPCFileCRUDServiceImpl extends RPCAbstrCRUDServiceImpl<FileDTO, FileFetchQuery> implements RPCCRUDService {

	private static final long serialVersionUID = 893086966110L;

	@SuppressWarnings("unchecked")
	@Override
	public CRUDService<FileDTO, FileFetchQuery> getCRUDService() {

		// this reflection approach prevents compile-dependency to module com.hotelorga.foundation.impl
		// it could be replaced by spring eventually
		try {
			Class<?> clazz = Class.forName("com.hotelorga.foundation.impl.FileCRUDServiceImpl");
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
