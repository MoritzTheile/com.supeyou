package com.supeyou.core.web.client.view.heropage.donate;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.view.heropage.donate.paypal.PayPalWidget;
import com.supeyou.crudie.web.client.resources.URLHelper;

public class DonateWidget extends WidgetView {

	public DonateWidget(final SupporterDTO supporterDTO) {

		paypalButtonSlot.add(new PayPalWidget(
				"_xclick-subscriptions", // _xclick-subscriptions _donations
				"1",
				URLHelper.getCurrentURL() + "#" + History.getToken(), //
				GWT.getHostPageBaseURL() + "IPNServlet", //
				supporterDTO.getHeroDTO().getPaypalAccount().value(),
				"Support for " + supporterDTO.getHeroDTO().getName().value(),
				"" + supporterDTO.getId().value())
				);

		donate5OnceButton.add(new PayPalWidget(
				"_donations", // _xclick-subscriptions _donations
				"5",
				URLHelper.getCurrentURL() + "#" + History.getToken(), //
				GWT.getHostPageBaseURL() + "IPNServlet", //
				supporterDTO.getHeroDTO().getPaypalAccount().value(),
				"Support for " + supporterDTO.getHeroDTO().getName().value(),
				"" + supporterDTO.getId().value())
				);

		render(supporterDTO);
	}

	private void render(final SupporterDTO supporterDTO) {

	}

}
