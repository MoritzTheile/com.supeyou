package com.supeyou.auth.web.client.login.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.crudie.web.client.fields.types.FieldForSingleLineString256Type;
import com.supeyou.crudie.web.client.uiorga.flatbutton.FlatButtonWidget;

public class WidgetView extends Composite {

	private static ThisUiBinder uiBinder = GWT.create(ThisUiBinder.class);

	@UiField
	Label messageLabel;

	@UiField
	FlowPanel nameRow;

	@UiField
	Label nameLabel;

	@UiField
	FieldForSingleLineString256Type nameInput;

	@UiField
	FlowPanel passwordRow;

	@UiField
	Label passwordLabel;

	@UiField
	FieldForSingleLineString256Type passwordInput;

	@UiField
	FlatButtonWidget flatButton;

	@UiField
	FlatButtonWidget cancelFlatButton;

	interface ThisUiBinder extends UiBinder<Widget, WidgetView> {
	}

	public WidgetView() {

		initWidget(uiBinder.createAndBindUi(this));

		this.addStyleName(this.getClass().getName().replaceAll("\\.", "-"));
	}

}
