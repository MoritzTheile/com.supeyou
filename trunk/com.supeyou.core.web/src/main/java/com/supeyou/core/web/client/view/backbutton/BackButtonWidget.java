package com.supeyou.core.web.client.view.backbutton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.supeyou.core.web.client.HistoryController;

public class BackButtonWidget extends WidgetView {

	public BackButtonWidget() {

		supeYouLogo.setUrl(GWT.getModuleBaseForStaticFiles() + "images/SupeYou-Logo.png");

		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				render();

			}
		});

		this.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				if (History.getToken() == null) {
					History.newItem(HistoryController.ANCHOR.LP.name());
				} else if (History.getToken().startsWith(HistoryController.ANCHOR.HERO.name() + "_")) {
					History.newItem(HistoryController.ANCHOR.HEROES.name());
				} else {
					History.newItem(HistoryController.ANCHOR.LP.name());
				}

			}
		}, ClickEvent.getType());

		render();
	}

	private void render() {
		if (HistoryController.ANCHOR.LP.name().equals(History.getToken())) {
			iconSlot.getElement().getStyle().setVisibility(Visibility.HIDDEN);
		} else {
			iconSlot.getElement().getStyle().setVisibility(Visibility.VISIBLE);
		}
	}
}
