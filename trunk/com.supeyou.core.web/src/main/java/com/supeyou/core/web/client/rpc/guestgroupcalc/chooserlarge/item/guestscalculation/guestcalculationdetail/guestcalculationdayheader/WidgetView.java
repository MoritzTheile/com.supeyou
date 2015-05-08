package com.supeyou.core.web.client.rpc.guestgroupcalc.chooserlarge.item.guestscalculation.guestcalculationdetail.guestcalculationdayheader;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class WidgetView extends Composite {

	private static ThisBinder thisBinder = GWT
			.create(ThisBinder.class);

	@UiField
	FlowPanel rootPanel;

	@UiField
	FlowPanel column1;

	@UiField
	FlowPanel column2;

	@UiField
	FlowPanel column3;

	@UiField
	FlowPanel column4;

	interface ThisBinder extends UiBinder<Widget, WidgetView> {
	}

	public WidgetView() {
		initWidget(thisBinder.createAndBindUi(this));

	}

}
