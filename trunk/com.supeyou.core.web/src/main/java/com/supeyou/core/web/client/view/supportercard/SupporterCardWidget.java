package com.supeyou.core.web.client.view.supportercard;

import com.google.gwt.user.client.ui.Label;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.rpc.hero.chooserlarge.item.ItemWidget;

public class SupporterCardWidget extends WidgetView {

	public SupporterCardWidget(SupporterDTO supporterDTO) {

		heroSlot.add(new ItemWidget(supporterDTO.getHeroDTO()));
		root.add(new Label(supporterDTO.getUserDTO().getLoginId().value()));

	}

}
