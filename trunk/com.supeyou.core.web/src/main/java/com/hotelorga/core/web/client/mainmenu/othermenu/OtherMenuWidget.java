package com.hotelorga.core.web.client.mainmenu.othermenu;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.core.web.client.mainmenu.adminmenu.AdminMenuWidget;
import com.hotelorga.foundation.web.client.uiorga.menuanddisplay.MenuAndDisplay;

public class OtherMenuWidget extends WidgetView {

	public OtherMenuWidget() {

		final Label menuItem3 = new Label("Admin");

		MenuAndDisplay menuAndDisplay = new MenuAndDisplay(menu, display) {

			@Override
			public Widget getWidgetFor(Widget menuItem) {
				if (menuItem3 == menuItem) {
					return new AdminMenuWidget();
				}

				return null;
			}
		};
		menuAndDisplay.addItem(menuItem3);

		menuAndDisplay.selectMenuItem(menuItem3);

	}

}
