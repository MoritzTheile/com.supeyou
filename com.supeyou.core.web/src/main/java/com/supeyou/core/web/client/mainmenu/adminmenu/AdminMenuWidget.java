package com.supeyou.core.web.client.mainmenu.adminmenu;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.crudie.web.client.uiorga.menuanddisplay.MenuAndDisplay;

public class AdminMenuWidget extends WidgetView {

	public AdminMenuWidget() {

		final Label menuItem0 = new Label("Dateien");
		final Label menuItem1 = new Label("Benutzer");
		final Label menuItem2 = new Label("<- Assos ->");
		final Label menuItem3 = new Label("Gruppen");
		final Label menuItem4 = new Label("Heroes");

		MenuAndDisplay menuAndDisplay = new MenuAndDisplay(menu, display) {

			@Override
			public Widget getWidgetFor(Widget menuItem) {
				if (menuItem0 == menuItem) {
					return new com.supeyou.crudie.web.client.rpc.file.chooserlarge.ChooserLargeWidget();
				}
				if (menuItem1 == menuItem) {
					return new com.supeyou.crudie.web.client.rpc.user.chooserlarge.ChooserLargeWidget();
				}
				if (menuItem2 == menuItem) {
					return new com.supeyou.crudie.web.client.rpc.user2group.chooserlarge.ChooserLargeWidget();
				}
				if (menuItem3 == menuItem) {
					return new com.supeyou.crudie.web.client.rpc.group.chooserlarge.ChooserLargeWidget();
				}
				if (menuItem4 == menuItem) {
					return new com.supeyou.core.web.client.rpc.hero.chooserlarge.ChooserLargeWidget();
				}

				return null;
			}
		};

		menuAndDisplay.addItem(menuItem0);
		menuAndDisplay.addItem(menuItem1);
		menuAndDisplay.addItem(menuItem2);
		menuAndDisplay.addItem(menuItem3);
		menuAndDisplay.addItem(menuItem4);

		menuAndDisplay.selectMenuItem(menuItem3);

	}

}
