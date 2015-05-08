package com.hotelorga.foundation.web.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.hotelorga.foundation.web.client.rpc.RPCService;

/**
 * The server side implementation of the RPC service.
 */
@WebServlet("/cruduser")
public class RPCServiceImpl extends RemoteServiceServlet implements
		RPCService {

	private static final long serialVersionUID = 3458798535412038630L;

	private static transient Logger log = Logger.getLogger(RPCServiceImpl.class.getName());

	public RPCServiceImpl() {
		log.info("RPCServiceImpl class is being initialized");
	}

	@Override
	public String getVersion() {

		log.info("getVersion()");

		Properties properties = new Properties();
		InputStream inputStream = RPCServiceImpl.class.getResourceAsStream("/foundation.properties");

		try {

			properties.load(inputStream);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				inputStream.close();

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

		return properties.getProperty("version.major") + "." + properties.getProperty("version.minor") + "." + properties.getProperty("version.buildnumber") + "." + properties.getProperty("version.coderevision");

	}

}
