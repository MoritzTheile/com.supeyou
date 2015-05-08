package com.hotelorga.core.web.client.rpc.payer.choosersmall.item;

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
	FlowPanel itemRoot;

	@UiField
	FlowPanel columnOne;

	@UiField
	FlowPanel columnTwo;

	@UiField
	FlowPanel columnThree;

	@UiField
	FlowPanel columnFour;

	interface ThisBinder extends UiBinder<Widget, WidgetView> {
	}

	public WidgetView() {
		initWidget(thisBinder.createAndBindUi(this));

	}

}
