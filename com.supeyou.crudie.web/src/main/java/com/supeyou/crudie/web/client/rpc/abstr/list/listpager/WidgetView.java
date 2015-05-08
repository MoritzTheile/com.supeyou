package com.supeyou.crudie.web.client.rpc.abstr.list.listpager;

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
	FlowPanel allBackButton;
	@UiField
	FlowPanel oneBackButton;
	@UiField
	Label label;
	@UiField
	FlowPanel oneForwardButton;
	@UiField
	FlowPanel allForwardButton;

	interface ThisUiBinder extends UiBinder<Widget, WidgetView> {
	}

	public WidgetView() {

		initWidget(uiBinder.createAndBindUi(this));

		com.supeyou.crudie.web.client.resources.GWTSTATICS.setJClassAsCSSClass(this);
	}

}
