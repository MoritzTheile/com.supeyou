package com.supeyou.core.web.client.view.heropage.invite.howtoinvite;

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
	FlowPanel emailButton;

	@UiField
	FlowPanel whatsAppButton;

	@UiField
	FlowPanel googlePlusButton;

	@UiField
	FlowPanel facebookButton;

	@UiField
	FlowPanel twitterButton;

	@UiField
	FlowPanel copyAndPasteButton;

	interface ThisUiBinder extends UiBinder<Widget, WidgetView> {
	}

	public WidgetView() {

		initWidget(uiBinder.createAndBindUi(this));

	}

}
