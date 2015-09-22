package com.supeyou.actor.web.client.rpc.event.chooserlarge.item;

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

	@UiField
	FlowPanel columnFive;

	@UiField
	FlowPanel columnSix;

	@UiField
	FlowPanel columnSeven;

	@UiField
	FlowPanel columnEight;

	@UiField
	FlowPanel columnNine;

	@UiField
	FlowPanel columnTen;

	interface ThisBinder extends UiBinder<Widget, WidgetView> {
	}

	public WidgetView() {
		initWidget(thisBinder.createAndBindUi(this));

	}

}
