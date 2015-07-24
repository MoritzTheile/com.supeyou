package com.supeyou.core.web.client.rpc.hero.chooserlarge.item.rootinfo;

import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;

public class RootInfoWidget extends WidgetView {

	public RootInfoWidget(SupporterDTO supporterDTO) {
		generatedResultLabel.setText(supporterDTO.getDecendentCount() + Text.i.EUROSYMBOL());
	}

}
