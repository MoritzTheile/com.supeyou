package com.hotelorga.auth.web.client.login.loginbutton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.foundation.web.client.uiorga.linkbutton.LinkButtonWidget;

public class WidgetView extends Composite {

	private static ThisUiBinder uiBinder = GWT.create(ThisUiBinder.class);

	@UiField
	LinkButtonWidget logInOutButton;

	interface ThisUiBinder extends UiBinder<Widget, WidgetView> {
	}

	public WidgetView() {

		initWidget(uiBinder.createAndBindUi(this));

this.addStyleName(this.getClass().getName().replaceAll("\\.", "-"));
	}

}
