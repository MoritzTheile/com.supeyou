package com.hotelorga.foundation.web.client.uiorga.popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class WidgetView extends Composite {

	private static ThisBinder thisBinder = GWT
			.create(ThisBinder.class);

	interface ThisBinder extends UiBinder<Widget, WidgetView> {
	}

	@UiField
	FlowPanel glass;

	@UiField
	FlowPanel contentSlot;

	public WidgetView() {
		initWidget(thisBinder.createAndBindUi(this));

	}

}
