package com.supeyou.core.web.client.mainmenu;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.web.client.rpc.hero.chooserlarge.ChooserLargeWidget;
import com.supeyou.crudie.web.client.model.LoginStateModel;
import com.supeyou.crudie.web.client.uiorga.menuanddisplay.MenuAndDisplay;

public class MainMenuWidget extends WidgetView {

	public MainMenuWidget() {

		final Label menuItem1 = new Label("Search Heroes");
		final Label menuItem2 = new Label("Add Hero");

		MenuAndDisplay menuAndDisplay = new MenuAndDisplay(menu, display) {

			@Override
			public Widget getWidgetFor(Widget menuItem) {
				if (menuItem1 == menuItem) {
					return new ChooserLargeWidget();
				}
				return null;
			}
		};

		menuAndDisplay.addItem(menuItem1);
		menuAndDisplay.addItem(menuItem2);

		if (LoginStateModel.i().userIsAdmin()) {

			// menuAndDisplay.addItem(menuItem5);
		}

		menuAndDisplay.selectMenuItem(menuItem1);

	}

}
