package com.hotelorga.core.web.server;

import java.lang.reflect.Method;

import javax.servlet.annotation.WebServlet;

import com.hotelorga.core.iface.dto.PayerDTO;
import com.hotelorga.core.iface.dto.PayerFetchQuery;
import com.hotelorga.core.web.client.rpc.payer.RPCCRUDService;
import com.hotelorga.foundation.iface.CRUDService;
import com.hotelorga.foundation.web.server.RPCAbstrCRUDServiceImpl;

@WebServlet("/RPCPayerCRUDServiceImpl")
public class RPCPayerCRUDServiceImpl extends RPCAbstrCRUDServiceImpl<PayerDTO, PayerFetchQuery> implements RPCCRUDService {

	private static final long serialVersionUID = 893034576566110L;

	@SuppressWarnings("unchecked")
	@Override
	public CRUDService<PayerDTO, PayerFetchQuery> getCRUDService() {

		// this reflection approach prevents compile-dependency to module com.hotelorga.foundation.impl
		// it could be replaced by spring eventually
		try {
			Class<?> clazz = Class.forName("com.hotelorga.core.impl.PayerCRUDServiceImpl");
			Class<?>[] params = new Class<?>[0];
			Method method_i = clazz.getMethod("i", params);
			Object[] objects = new Object[0];
			return (CRUDService<PayerDTO, PayerFetchQuery>) method_i.invoke(null, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
