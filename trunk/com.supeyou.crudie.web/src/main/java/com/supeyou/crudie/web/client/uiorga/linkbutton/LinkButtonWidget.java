package com.supeyou.crudie.web.client.uiorga.linkbutton;

import com.google.gwt.event.dom.client.ClickHandler;

public class LinkButtonWidget extends WidgetView {

	public LinkButtonWidget(String text) {
		setText(text);
	}

	public LinkButtonWidget() {
		setText("");
	}

	public void addClickHandler(ClickHandler clickHandler) {
		button.addClickHandler(clickHandler);
	}

	public void setText(String text) {
		button.setText(text);
	}

}
