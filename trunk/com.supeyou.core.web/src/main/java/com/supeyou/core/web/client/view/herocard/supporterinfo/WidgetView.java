package com.supeyou.core.web.client.view.herocard.supporterinfo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class WidgetView extends Composite {

	private static ThisUiBinder uiBinder = GWT.create(ThisUiBinder.class);

	@UiField
	FlowPanel root;

	@UiField
	FlowPanel leftColumn;

	@UiField
	FlowPanel youImage;

	@UiField
	Label youLabel;

	@UiField
	FlowPanel rightColumn;

	@UiField
	Label yourGeneratedResult;

	@UiField
	Label yourGeneratedResultLabel;

	@UiField
	Label yourResult;

	@UiField
	Label yourResultLabel;

	interface ThisUiBinder extends UiBinder<Widget, WidgetView> {
	}

	public WidgetView() {

		initWidget(uiBinder.createAndBindUi(this));

	}

}
