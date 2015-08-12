package com.supeyou.core.web.client.view.supportercard.supporter;

import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.crudie.iface.common.HELPER;

public class SupporterWidget extends WidgetView {

	public SupporterWidget(SupporterDTO supporterDTO) {

		yourResult.setText(HELPER.cent2euro(supporterDTO.getOwnAmount().value()) + " " + Text.i.EUROSYMBOL());
		yourGeneratedResult.setText(HELPER.cent2euro(supporterDTO.getDecendantAmount().value()) + " " + Text.i.EUROSYMBOL());

	}

}
