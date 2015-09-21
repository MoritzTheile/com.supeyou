package com.supeyou.core.web.client.view.heropage.donate;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.supeyou.actor.web.client.login.ActorStatics;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.view.heropage.donate.paypal.PayPalWidget;
import com.supeyou.crudie.web.client.resources.URLHelper;

public class DonateWidget extends WidgetView {

	public DonateWidget(final SupporterDTO supporterDTO) {

		donateOnceButton.add(new PayPalWidget(
				"_donations", // _xclick-subscriptions _donations
				"5",
				URLHelper.getCurrentURL() + "#" + History.getToken(), //
				GWT.getHostPageBaseURL() + "IPNServlet", //
				supporterDTO.getHeroDTO().getPaypalAccount().value(),
				"Support for " + supporterDTO.getHeroDTO().getName().value(),
				"" + supporterDTO.getId().value(),
				Text.i.DONATE_OPTION_DonateOnce())
				);

		donateOnceButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				ActorStatics.fireActorEvent("click", "donateOnceButton");

			}
		}, ClickEvent.getType());

		donate1MonthlyButton.add(new PayPalWidget(
				"_xclick-subscriptions", // _xclick-subscriptions _donations
				"1",
				URLHelper.getCurrentURL() + "#" + History.getToken(), //
				GWT.getHostPageBaseURL() + "IPNServlet", //
				supporterDTO.getHeroDTO().getPaypalAccount().value(),
				"Support for " + supporterDTO.getHeroDTO().getName().value(),
				"" + supporterDTO.getId().value(),
				Text.i.DONATE_OPTION_Donate1Monthly()));

		donate1MonthlyButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				ActorStatics.fireActorEvent("click", "donate1MonthlyButton");

			}
		}, ClickEvent.getType());

		donate5MonthlyButton.add(new PayPalWidget(
				"_xclick-subscriptions", // _xclick-subscriptions _donations
				"5",
				URLHelper.getCurrentURL() + "#" + History.getToken(), //
				GWT.getHostPageBaseURL() + "IPNServlet", //
				supporterDTO.getHeroDTO().getPaypalAccount().value(),
				"Support for " + supporterDTO.getHeroDTO().getName().value(),
				"" + supporterDTO.getId().value(),
				Text.i.DONATE_OPTION_Donate5Monthly())
				);

		donate5MonthlyButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				ActorStatics.fireActorEvent("click", "donate5MonthlyButton");

			}
		}, ClickEvent.getType());

		donate25MonthlyButton.add(new PayPalWidget(
				"_xclick-subscriptions", // _xclick-subscriptions _donations
				"25",
				URLHelper.getCurrentURL() + "#" + History.getToken(), //
				GWT.getHostPageBaseURL() + "IPNServlet", //
				supporterDTO.getHeroDTO().getPaypalAccount().value(),
				"Support for " + supporterDTO.getHeroDTO().getName().value(),
				"" + supporterDTO.getId().value(),
				Text.i.DONATE_OPTION_Donate25Monthly())
				);

		donate25MonthlyButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				ActorStatics.fireActorEvent("click", "donate25MonthlyButton");

			}
		}, ClickEvent.getType());

		donate100MonthlyButton.add(new PayPalWidget(
				"_xclick-subscriptions", // _xclick-subscriptions _donations
				"100",
				URLHelper.getCurrentURL() + "#" + History.getToken(), //
				GWT.getHostPageBaseURL() + "IPNServlet", //
				supporterDTO.getHeroDTO().getPaypalAccount().value(),
				"Support for " + supporterDTO.getHeroDTO().getName().value(),
				"" + supporterDTO.getId().value(),
				Text.i.DONATE_OPTION_Donate50Monthly())
				);

		donate100MonthlyButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				ActorStatics.fireActorEvent("click", "donate100MonthlyButton");

			}
		}, ClickEvent.getType());

		render(supporterDTO);
	}

	private void render(final SupporterDTO supporterDTO) {

	}

}
