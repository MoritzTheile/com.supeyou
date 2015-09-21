package com.supeyou.core.web.client.mainmenu.adminmenu;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.actor.web.client.menu.ActorMenuWidget;
import com.supeyou.core.web.client.mainmenu.adminmenu.coremenu.CoreMenuWidget;
import com.supeyou.crudie.web.client.ui.coremenu.CrudieMenuWidget;
import com.supeyou.crudie.web.client.uiorga.menuanddisplay.MenuAndDisplay;

public class AdminMenuWidget extends WidgetView {

	public AdminMenuWidget() {

		final Label menuItem0 = new Label("actor");
		final Label menuItem1 = new Label("core");
		final Label menuItem2 = new Label("crudie");

		MenuAndDisplay menuAndDisplay = new MenuAndDisplay(menu, display) {

			@Override
			public Widget getWidgetFor(Widget menuItem) {
				if (menuItem0 == menuItem) {
					return new ActorMenuWidget();
				}
				if (menuItem1 == menuItem) {
					return new CoreMenuWidget();
				}
				if (menuItem2 == menuItem) {
					return new CrudieMenuWidget();
				}

				return null;
			}
		};

		menuAndDisplay.addItem(menuItem0);
		menuAndDisplay.addItem(menuItem1);
		menuAndDisplay.addItem(menuItem2);

		menuAndDisplay.selectMenuItem(menuItem1);

	}

}
