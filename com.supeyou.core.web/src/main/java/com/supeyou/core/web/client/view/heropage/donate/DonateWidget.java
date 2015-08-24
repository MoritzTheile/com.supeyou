package com.supeyou.core.web.client.view.heropage.donate;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.view.heropage.donate.paypal.PayPalWidget;

public class DonateWidget extends WidgetView {

	public DonateWidget(final SupporterDTO supporterDTO) {

		paypalButtonSlot.add(new PayPalWidget("paypal.com-facilitator@moritztheile.de", "Hero Support", "" + supporterDTO.getId().value()));

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
