package com.supeyou.core.web.client.view.supportercard;

import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.rpc.hero.chooserlarge.item.ItemWidget;
import com.supeyou.core.web.client.view.supportercard.supporter.SupporterWidget;

public class SupporterCardWidget extends WidgetView {

	public SupporterCardWidget(SupporterDTO supporterDTO) {

		heroSlot.add(new ItemWidget(supporterDTO.getHeroDTO()));
		supporterSlot.add(new SupporterWidget(supporterDTO));

	}

}
