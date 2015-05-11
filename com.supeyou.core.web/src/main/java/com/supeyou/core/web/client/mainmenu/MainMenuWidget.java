package com.supeyou.core.web.client.mainmenu;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.web.client.mainmenu.othermenu.OtherMenuWidget;
import com.supeyou.crudie.web.client.model.LoginStateModel;
import com.supeyou.crudie.web.client.uiorga.menuanddisplay.MenuAndDisplay;

public class MainMenuWidget extends WidgetView {

	public MainMenuWidget() {

		final Label menuItem1 = new Label("Gäste");
		final Label menuItem2 = new Label("Zimmerbelegungen");
		final Label menuItem3 = new Label("Kostenübernahmen");
		final Label menuItem4 = new Label("Abrechnung");

		final Label menuItem5 = new Label("Anderes");

		MenuAndDisplay menuAndDisplay = new MenuAndDisplay(menu, display) {

			@Override
			public Widget getWidgetFor(Widget menuItem) {
				if (menuItem5 == menuItem) {
					return new OtherMenuWidget();
				}
				return null;
			}
		};

		menuAndDisplay.addItem(menuItem1);
		menuAndDisplay.addItem(menuItem2);
		menuAndDisplay.addItem(menuItem3);
		menuAndDisplay.addItem(menuItem4);

		if (LoginStateModel.i().userIsAdmin()) {

			menuAndDisplay.addItem(menuItem5);
		}

		menuAndDisplay.selectMenuItem(menuItem4);

	}

}
