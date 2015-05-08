package com.supeyou.crudie.web.client.uiorga.flatbutton;

import com.google.gwt.event.dom.client.ClickHandler;

public class FlatButtonWidget extends WidgetView {

	public FlatButtonWidget() {

	}

	public void addClickHandler(ClickHandler clickHandler) {
		flatButton.addClickHandler(clickHandler);
	}

	public void setText(String text) {
		flatButton.setText(text);
	}

}
