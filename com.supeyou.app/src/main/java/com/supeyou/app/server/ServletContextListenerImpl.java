package com.supeyou.app.server;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.supeyou.core.iface.SupporterCRUDService;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.impl.SupporterCRUDServiceImpl;
import com.supeyou.core.impl.initialdata.InitialCoreData;
import com.supeyou.crudie.iface.CRUDObserver;
import com.supeyou.crudie.iface.common.HELPER;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.datatype.types.AbstrType;
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
		} catch (Exception e) {
			log.log(Level.SEVERE, "ERROR during data initializing", e);
		}

		try {
			registerMailNotificator();
		} catch (Exception e) {
			log.log(Level.SEVERE, "ERROR during registering mail notificator", e);
		}
	}

	private void registerMailNotificator() {

		SupporterCRUDService supporterCRUDService = SupporterCRUDServiceImpl.i();

		supporterCRUDService.addCRUDObserver(new CRUDObserver<SupporterDTO>() {

			@Override
			public void wasUpdated(SupporterDTO dto, SupporterDTO oldDTO) {

				if (oldDTO != null) {

					String link = "http://supeyou.com/?auth=" + dto.getUserDTO().getAuthToken().value() + "#HERO_" + dto.getHeroDTO().getId().value();

					if (dto.getDecendentCount() != oldDTO.getDecendentCount()) {
						SendEmail.sendEmail(dto.getUserDTO().getLoginId().value(), "bcc@supeyou.com", "Your SupeYou invitation was successful",
								""
										+ "Great! <br>"
										+ "<br>"
										+ " You have now " + dto.getDecendentCount() + " supporter for " + dto.getHeroDTO().getName().value() + " invited.  <br>"
										+ "<br>"
										+ "You find your updated hero card here: <a href=\"" + link + "\">" + link + "</a>  <br>"
										+ "<br>"
										+ link
										+ "<br>"
										+ "<br>"
										+ "(If you don't want notifications concerning " + dto.getHeroDTO().getName().value() + " respond to this email with 'unsubscribe'.)<br>"
										+ "<br>"
										+ "<br>", true);

					}

					if (dto.getDecendantAmount().value() > oldDTO.getDecendantAmount().value()) {

						SendEmail.sendEmail(dto.getUserDTO().getLoginId().value(), "bcc@supeyou.com", "You raised " + HELPER.amount2eurostring(dto.getDecendantAmount()) + " Euro for  " + dto.getHeroDTO().getName().value() + " on SupeYou",
								""
										+ "Thanks so much! If everybody raises as much money as you did we would have very powerful heroes!  <br>"
										+ "<br>"
										+ "You find your updated hero card here:  <a href=\"" + link + "\">" + link + "</a> <br>"
										+ "<br>"
										+ "<br>"
										+ "<br>"
										+ "(If you don't want notifications concerning " + dto.getHeroDTO().getName().value() + " respond to this email with 'unsubscribe'.)<br>"
										+ "<br>"
										+ "<br>", true);

					}
				}

			}

			@Override
			public void wasRead(SupporterDTO dto) {

			}

			@Override
			public void wasDeleted(AbstrType<Long> dtoId) {

			}

			@Override
			public void wasCreated(SupporterDTO dto) {

			}

		});
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