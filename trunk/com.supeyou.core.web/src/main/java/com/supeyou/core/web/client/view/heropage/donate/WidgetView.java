package com.supeyou.core.web.client.view.heropage.donate;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class WidgetView extends Composite {

	private static ThisUiBinder uiBinder = GWT.create(ThisUiBinder.class);

	@UiField
	FlowPanel root;

	@UiField
	FlowPanel donate1MonthlyButton;

	@UiField
	FlowPanel donate5OnceButton;

	@UiField
	FlowPanel donate25OnceButton;

	@UiField
	FlowPanel donate50OnceButton;

	@UiField
	FlowPanel donate100OnceButton;

	@UiField
	FlowPanel donate5MonthlyButton;

	@UiField
	FlowPanel donate25MonthlyButton;

	@UiField
	FlowPanel donate100MonthlyButton;

	interface ThisUiBinder extends UiBinder<Widget, WidgetView> {
	}

	public WidgetView() {

		initWidget(uiBinder.createAndBindUi(this));

	}

}
