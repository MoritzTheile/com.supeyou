package com.supeyou.core.web.client.view.heropage.donate;

import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.view.heropage.donate.paypal.PaypalWidget;

public class DonateWidget extends WidgetView {

	public DonateWidget(final SupporterDTO supporterDTO) {

		paypalButtonSlot.add(new PaypalWidget("paypal.com-facilitator@moritztheile.de", "Hero Support", "" + supporterDTO.getId().value()));

		render(supporterDTO);
	}

	private void render(final SupporterDTO supporterDTO) {

	}

}
