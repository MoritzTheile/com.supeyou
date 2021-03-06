package com.supeyou.core.web.client.view.heropage.supportertree;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class WidgetView extends Composite {

	private static ThisUiBinder uiBinder = GWT.create(ThisUiBinder.class);

	@UiField
	FlowPanel root;

	@UiField
	Label amountLabel;
	@UiField
	Label authTokenLabel;
	@UiField
	FlowPanel imageSlot;
	@UiField
	HTML nameLabel;
	@UiField
	FlowPanel expandButtonSlot;
	@UiField
	FlowPanel edgeSlot;
	@UiField
	FlowPanel childrenSlot;

	interface ThisUiBinder extends UiBinder<Widget, WidgetView> {
	}

	public WidgetView() {

		initWidget(uiBinder.createAndBindUi(this));

	}

}
