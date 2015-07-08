package com.supeyou.core.web.client.view.heropage.supporter;

import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.view.supportertree.SupporterTreeWidget;

public class SupporterWidget extends WidgetView {

	public SupporterWidget(HeroDTO heroDTO, SupporterDTO supporterDTO) {

		root.add(new SupporterTreeWidget(null, supporterDTO));

	}

}
