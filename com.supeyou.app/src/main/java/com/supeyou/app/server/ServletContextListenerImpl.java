package com.supeyou.app.server;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.supeyou.core.impl.initialdata.InitialCoreData;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.dto.DTOFetchList;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.iface.dto.UserFetchQuery;
import com.supeyou.crudie.impl.UserCRUDServiceImpl;
import com.supeyou.crudie.impl.initialdata.InitialData;

public class ServletContextListenerImpl implements ServletContextListener {
	private static final Logger log = Logger.getLogger(ServletContextListenerImpl.class.getName());

	public void contextInitialized(ServletContextEvent event) {

		log.info("servlet context initialized (codemarker=e9rg9a0)");
		try {
			UserDTO admin = UserCRUDServiceImpl.i().getInitialAdmin();
			DTOFetchList<UserDTO> fetchList = UserCRUDServiceImpl.i().fetchList(admin, new Page(), new UserFetchQuery());
			if (fetchList.size() == 1) { // the one user is the initial admin
				InitialData.i();
				InitialCoreData.i();
			} else {
				log.info("found more than one user (" + fetchList.size() + "), that means db is not fresh");
			}
		} catch (CRUDException e) {
			log.log(Level.SEVERE, "ERROR during data initializing", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		log.info("servlet context destroyed (codemarker=qe0gj0q)");

		// makes only sense if embedded derby is used. Important for being able to redeploy to Tomcat or other App-Container.
		try {
			DriverManager.getConnection("jdbc:derby:;shutdown=true");
		} catch (SQLException e) {
			log.log(Level.INFO, "exception expected to be thrown = 'Derby system shutdown'. Thrown exception='" + e.getMessage() + "'");
		}

	}
}