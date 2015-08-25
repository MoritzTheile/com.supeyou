package com.supeyou.core.web.client.view.heropage.donate;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.view.heropage.donate.paypal.PayPalWidget;
import com.supeyou.crudie.web.client.resources.URLHelper;

public class DonateWidget extends WidgetView {

	public DonateWidget(final SupporterDTO supporterDTO) {

		donate5OnceButton.add(new PayPalWidget(
				"_donations", // _xclick-subscriptions _donations
				"5",
				URLHelper.getCurrentURL() + "#" + History.getToken(), //
				GWT.getHostPageBaseURL() + "IPNServlet", //
				supporterDTO.getHeroDTO().getPaypalAccount().value(),
				"Support for " + supporterDTO.getHeroDTO().getName().value(),
				"" + supporterDTO.getId().value(),
				Text.i.DONATE_OPTION_Donate5Once())
				);
		donate25OnceButton.add(new PayPalWidget(
				"_donations", // _xclick-subscriptions _donations
				"25",
				URLHelper.getCurrentURL() + "#" + History.getToken(), //
				GWT.getHostPageBaseURL() + "IPNServlet", //
				supporterDTO.getHeroDTO().getPaypalAccount().value(),
				"Support for " + supporterDTO.getHeroDTO().getName().value(),
				"" + supporterDTO.getId().value(),
				Text.i.DONATE_OPTION_Donate25Once())
				);

		donate50OnceButton.add(new PayPalWidget(
				"_donations", // _xclick-subscriptions _donations
				"50",
				URLHelper.getCurrentURL() + "#" + History.getToken(), //
				GWT.getHostPageBaseURL() + "IPNServlet", //
				supporterDTO.getHeroDTO().getPaypalAccount().value(),
				"Support for " + supporterDTO.getHeroDTO().getName().value(),
				"" + supporterDTO.getId().value(),
				Text.i.DONATE_OPTION_Donate50Once())
				);

		donate100OnceButton.add(new PayPalWidget(
				"_donations", // _xclick-subscriptions _donations
				"100",
				URLHelper.getCurrentURL() + "#" + History.getToken(), //
				GWT.getHostPageBaseURL() + "IPNServlet", //
				supporterDTO.getHeroDTO().getPaypalAccount().value(),
				"Support for " + supporterDTO.getHeroDTO().getName().value(),
				"" + supporterDTO.getId().value(),
				Text.i.DONATE_OPTION_Donate100Once())
				);

		donate1MonthlyButton.add(new PayPalWidget(
				"_xclick-subscriptions", // _xclick-subscriptions _donations
				"1",
				URLHelper.getCurrentURL() + "#" + History.getToken(), //
				GWT.getHostPageBaseURL() + "IPNServlet", //
				supporterDTO.getHeroDTO().getPaypalAccount().value(),
				"Support for " + supporterDTO.getHeroDTO().getName().value(),
				"" + supporterDTO.getId().value(),
				Text.i.DONATE_OPTION_Donate1Monthly()));

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

		render(supporterDTO);
	}

	private void render(final SupporterDTO supporterDTO) {

	}

}
