package com.supeyou.crudie.web.client.uiorga.popup.contentwrapper;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

public class ContentWrapperWidget extends WidgetView {

	public ContentWrapperWidget(String headerText, final Widget content) {

		Window.scrollTo(0, 0);

		headerLabel.setText(headerText);

		widgetSlot.add(content);

		closeButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				History.back();

			}

		}, ClickEvent.getType());

	}
}
