package com.hotelorga.core.web.client.rpc.guestgroupcalc.chooserlarge.item.guestscalculation.guestcalculation;

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
	FlowPanel columnA;
	@UiField
	FlowPanel columnB;
	@UiField
	FlowPanel columnC;
	@UiField
	FlowPanel columnD;
	@UiField
	FlowPanel columnE;
	@UiField
	FlowPanel columnF;
	@UiField
	FlowPanel columnG;
	@UiField
	FlowPanel columnH;
	@UiField
	FlowPanel columnI;
	@UiField
	FlowPanel columnJ;
	@UiField
	FlowPanel columnK;
	@UiField
	FlowPanel columnL;
	@UiField
	FlowPanel columnM;
	@UiField
	FlowPanel columnN;

	interface ThisBinder extends UiBinder<Widget, WidgetView> {
	}

	public WidgetView() {
		initWidget(thisBinder.createAndBindUi(this));

	}

}
