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
	FlowPanel emailButton1;
	@UiField
	FlowPanel emailButton2;

	@UiField
	FlowPanel whatsAppButton;
	@UiField
	FlowPanel whatsAppButton1;
	@UiField
	FlowPanel whatsAppButton2;

	@UiField
	FlowPanel googlePlusButton;
	@UiField
	FlowPanel googlePlusButton1;
	@UiField
	FlowPanel googlePlusButton2;

	@UiField
	FlowPanel facebookButton;
	@UiField
	FlowPanel facebookButton1;
	@UiField
	FlowPanel facebookButton2;

	@UiField
	FlowPanel twitterButton;
	@UiField
	FlowPanel twitterButton1;
	@UiField
	FlowPanel twitterButton2;

	@UiField
	FlowPanel copyAndPasteButton;

	interface ThisUiBinder extends UiBinder<Widget, WidgetView> {
	}

	public WidgetView() {

		initWidget(uiBinder.createAndBindUi(this));

	}

}
