package com.supeyou.core.web.client.view.heropage.donate;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.view.heropage.donate.paypal.PayPalWidget;
import com.supeyou.crudie.web.client.resources.URLHelper;

public class DonateWidget extends WidgetView {

	public DonateWidget(final SupporterDTO supporterDTO) {

		paypalButtonSlot.add(new PayPalWidget(
				"_donations", // _xclick-subscriptions _donations
				"1.00",
				URLHelper.getCurrentURL() + "#" + History.getToken(), //
				GWT.getHostPageBaseURL() + "IPNServlet", //
				supporterDTO.getHeroDTO().getPaypalAccount().value(),
				"Support for " + supporterDTO.getHeroDTO().getName().value(),
				"" + supporterDTO.getId().value())
				);

		donate5OnceButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Window.alert("not yet implemented\n\ncodemarker=84et");

			}
		}, ClickEvent.getType());

		render(supporterDTO);
	}

	private void render(final SupporterDTO supporterDTO) {

	}

}
