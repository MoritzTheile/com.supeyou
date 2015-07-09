package com.supeyou.core.web.client.view.heropage.supporter;

import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.view.supportertree.SupporterTreeWidget;

public class SupporterWidget extends WidgetView {

	public SupporterWidget(SupporterDTO supporterDTO) {

		root.add(new SupporterTreeWidget(null, supporterDTO));

	}

}
