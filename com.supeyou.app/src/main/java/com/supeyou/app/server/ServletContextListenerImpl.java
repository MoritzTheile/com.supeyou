package com.supeyou.app.server;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.supeyou.actor.iface.common.ActorStatics;
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

				if (HELPER.isAnonymous(dto.getUserDTO())) {
					// no mails if user is anonymous
					return;
				}

				if (oldDTO != null) {

					if (dto.getDecendentCount() != oldDTO.getDecendentCount()) {
						SendEmail.sendEmail(dto.getUserDTO().getLoginId().value(), "Your SupeYou invitation was successful",
								""
										+ "<html>"
										+ "<head></head>"
										+ "<body>"
										+ "Great! <br>"
										+ "<br>"
										+ " You have now " + dto.getDecendentCount() + " supporter for " + dto.getHeroDTO().getName().value() + " invited.  <br>"
										+ "<br>"
										+ "You find your updated hero card here: <br>"
										+ "<br>"
										+ "<a href=\"" + getLink(dto, false) + "\">" + getLink(dto, false) + "</a>  <br>"
										+ "<br>"
										+ "<br>"
										+ "<font size=\"-2\">(If you don't want notifications concerning " + dto.getHeroDTO().getName().value() + " click <a href=\"" + getLink(dto, true) + "\">unsubscribe</a>.)</font><br>"
										+ "<br>"
										+ "<br>"
										+ "</body>"
										+ "</html>", true);

					}

					if (dto.getDecendantAmount().value() != null && oldDTO.getDecendantAmount() != null && dto.getDecendantAmount().value() > oldDTO.getDecendantAmount().value()) {

						SendEmail.sendEmail(dto.getUserDTO().getLoginId().value(), "You raised " + HELPER.amount2eurostring(dto.getDecendantAmount()) + " Euro for  " + dto.getHeroDTO().getName().value() + " on SupeYou",
								""
										+ "<html>"
										+ "<head></head>"
										+ "<body>"
										+ "Thanks so much! If everybody raises as much money as you did we would have very powerful heroes!  <br>"
										+ "<br>"
										+ "You find your updated hero card here: <br>"
										+ "<br>"
										+ "<a href=\"" + getLink(dto, false) + "\">" + getLink(dto, false) + "</a> <br>"
										+ "<br>"
										+ "<br>"
										+ "<br>"
										+ "<font size=\"-2\">(If you don't want notifications concerning " + dto.getHeroDTO().getName().value() + " click <a href=\"" + getLink(dto, true) + "\">unsubscribe</a>.)</font><br>"
										+ "<br>"
										+ "<br>"
										+ "</body>"
										+ "</html>", true);

					}
				}

			}

			private String getLink(SupporterDTO dto, boolean unsubscribe) {
				String link = "http://supeyou.com/?auth=" + dto.getUserDTO().getAuthToken().value();

				if (unsubscribe) {
					link += "&" + ActorStatics.UNSUBSCRIBE_TOKEN;
				}

				return link + "#HERO_" + dto.getHeroDTO().getId().value();
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