package com.supeyou.actor.web.client.menu;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.crudie.web.client.uiorga.menuanddisplay.MenuAndDisplay;

public class ActorMenuWidget extends WidgetView {

	public ActorMenuWidget() {

		final Label menuItem0 = new Label("Event");
		final Label menuItem1 = new Label("Session2Event");

		MenuAndDisplay menuAndDisplay = new MenuAndDisplay(menu, display) {

			@Override
			public Widget getWidgetFor(Widget menuItem) {

				if (menuItem0 == menuItem) {
					return new com.supeyou.actor.web.client.rpc.event.chooserlarge.ChooserLargeWidget();
				}

				if (menuItem1 == menuItem) {

					return new com.supeyou.actor.web.client.rpc.session2event.chooserlarge.ChooserLargeWidget();
				}

				return null;

			}

		};

		menuAndDisplay.addItem(menuItem0);
		menuAndDisplay.addItem(menuItem1);

		menuAndDisplay.selectMenuItem(menuItem0);

	}
}
