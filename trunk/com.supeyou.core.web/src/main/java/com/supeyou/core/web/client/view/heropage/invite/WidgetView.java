package com.supeyou.core.web.client.view.heropage.invite;

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
	FlowPanel closeButton;

	@UiField
	FlowPanel reloadButton;

	@UiField
	Label linkLabel;

	// @UiField
	// FlowPanel shareButton;

	@UiField
	FlowPanel linkNameTextBoxSlot;

	@UiField
	FlowPanel shareButtonMail;

	@UiField
	FlowPanel shareButtonWhatsapp;

	@UiField
	FlowPanel shareButtonGoogle;

	@UiField
	FlowPanel shareButtonFacebook;

	@UiField
	FlowPanel shareButtonTwitter;

	interface ThisUiBinder extends UiBinder<Widget, WidgetView> {
	}

	public WidgetView() {

		initWidget(uiBinder.createAndBindUi(this));

	}

}
