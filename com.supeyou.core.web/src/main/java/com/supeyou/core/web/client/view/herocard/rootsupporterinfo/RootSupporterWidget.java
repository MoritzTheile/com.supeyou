package com.supeyou.core.web.client.view.herocard.rootsupporterinfo;

import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.view.heropage.supportertree.SupporterTreeWidget;
import com.supeyou.crudie.iface.common.HELPER;

public class RootSupporterWidget extends WidgetView {

	public RootSupporterWidget(SupporterDTO supporterDTO) {

		overallGeneratedResult.setText(HELPER.cent2euro(SupporterTreeWidget.getAmountValueNullsave(supporterDTO.getDecendantAmount()) + SupporterTreeWidget.getAmountValueNullsave(supporterDTO.getOwnAmount())) + " " + Text.i.EUROSYMBOL());

	}

}
