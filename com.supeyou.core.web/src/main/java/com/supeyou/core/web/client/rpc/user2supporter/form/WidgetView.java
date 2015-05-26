package com.supeyou.core.web.client.rpc.user2supporter.form;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.crudie.web.client.uiorga.flatbutton.FlatButtonWidget;

public class WidgetView extends Composite {

	private static ThisBinder thisBinder = GWT.create(ThisBinder.class);

	@UiField
	FlowPanel formSlot;
	@UiField
	FlatButtonWidget deleteButton;
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
