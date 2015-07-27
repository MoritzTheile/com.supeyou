package com.supeyou.core.web.client.view.heropage.donate;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.view.heropage.donate.paypal.PaypalWidget;

public class DonateWidget extends WidgetView {

	public DonateWidget(final SupporterDTO supporterDTO) {

		Window.scrollTo(0, 0);

		closeButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				History.back();

			}
		}, ClickEvent.getType());

		paypalButtonSlot.add(new PaypalWidget("paypal.com@moritztheile.de", "Hero Support", "hero_" + supporterDTO.getHeroDTO().getId().value()));

		render(supporterDTO);
	}

	private void render(final SupporterDTO supporterDTO) {

	}

}
