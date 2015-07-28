package com.supeyou.core.web.server.paypal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IPNServlet extends HttpServlet {

	private static final long serialVersionUID = -2862582088198546520L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			IpnHandler ipnHandler = new IpnHandler();

			IpnInfo ipnInfo = ipnHandler.handleIpn(req);

			ipnInfo.getPaymentStatus();

		} catch (IpnException e) {
			e.printStackTrace();
			throw new ServletException("paypal ipn caused error", e);
		}
		super.doPost(req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
