package com.supeyou.core.web.client.mainmenu.adminmenu.coremenu;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.crudie.web.client.uiorga.menuanddisplay.MenuAndDisplay;

public class CoreMenuWidget extends WidgetView {

	public CoreMenuWidget() {

		final Label menuItem0 = new Label("Donation");

		MenuAndDisplay menuAndDisplay = new MenuAndDisplay(menu, display) {

			@Override
			public Widget getWidgetFor(Widget menuItem) {

				if (menuItem0 == menuItem) {
					return new com.supeyou.core.web.client.rpc.donation.chooserlarge.ChooserLargeWidget();
				}

				return null;
			}
		};

		menuAndDisplay.addItem(menuItem0);

		menuAndDisplay.selectMenuItem(menuItem0);

	}

}
