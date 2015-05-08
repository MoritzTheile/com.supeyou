package com.hotelorga.core.web.client.rpc.guestgroupcalc.createguestgroupforall.createmultiguest2roomform;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.foundation.web.client.uiorga.flatbutton.FlatButtonWidget;

public class WidgetView extends Composite {

	private static ThisBinder thisBinder = GWT.create(ThisBinder.class);

	@UiField
	FlowPanel formSlot;
	@UiField
	FlatButtonWidget cancelButton;
	@UiField
	FlatButtonWidget saveButton;

	@UiField
	FlowPanel actionbar;
	@UiField
	FlowPanel buttonsSlot;

	interface ThisBinder extends UiBinder<Widget, WidgetView> {
	}

	public WidgetView() {
		initWidget(thisBinder.createAndBindUi(this));

	}

}
