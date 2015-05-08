package com.hotelorga.core.web.client.mainmenu.adminmenu;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.foundation.web.client.uiorga.menuanddisplay.MenuAndDisplay;

public class AdminMenuWidget extends WidgetView {

	public AdminMenuWidget() {

		final Label menuItem0 = new Label("Dateien");
		final Label menuItem1 = new Label("Benutzer");
		final Label menuItem2 = new Label("<- Assos ->");
		final Label menuItem3 = new Label("Gruppen");
		final Label menuItem4 = new Label("GuestGroups");
		final Label menuItem5 = new Label("Guests");
		final Label menuItem6 = new Label("Rooms");
		final Label menuItem7 = new Label("Acceptance");
		final Label menuItem8 = new Label("Payer");
		final Label menuItem9 = new Label("Guest2Room");
		final Label menuItem10 = new Label("Guest2GuestGroup");
		final Label menuItem11 = new Label("Guest2Acceptance");
		final Label menuItem12 = new Label("Acceptance2Payer");

		MenuAndDisplay menuAndDisplay = new MenuAndDisplay(menu, display) {

			@Override
			public Widget getWidgetFor(Widget menuItem) {
				if (menuItem0 == menuItem) {
					return new com.hotelorga.foundation.web.client.rpc.file.chooserlarge.ChooserLargeWidget();
				}
				if (menuItem1 == menuItem) {
					return new com.hotelorga.foundation.web.client.rpc.user.chooserlarge.ChooserLargeWidget();
				}
				if (menuItem2 == menuItem) {
					return new com.hotelorga.foundation.web.client.rpc.user2group.chooserlarge.ChooserLargeWidget();
				}
				if (menuItem3 == menuItem) {
					return new com.hotelorga.foundation.web.client.rpc.group.chooserlarge.ChooserLargeWidget();
				}
				if (menuItem4 == menuItem) {
					return new com.hotelorga.core.web.client.rpc.guestgroup.chooserlarge.ChooserLargeWidget();
				}
				if (menuItem5 == menuItem) {
					return new com.hotelorga.core.web.client.rpc.guest.chooserlarge.ChooserLargeWidget();
				}
				if (menuItem6 == menuItem) {
					return new com.hotelorga.core.web.client.rpc.room.chooserlarge.ChooserLargeWidget();
				}
				if (menuItem7 == menuItem) {
					return new com.hotelorga.core.web.client.rpc.acceptance.chooserlarge.ChooserLargeWidget();
				}
				if (menuItem8 == menuItem) {
					return new com.hotelorga.core.web.client.rpc.payer.chooserlarge.ChooserLargeWidget();
				}
				if (menuItem9 == menuItem) {
					return new com.hotelorga.core.web.client.rpc.guest2room.chooserlarge.ChooserLargeWidget();
				}

				if (menuItem10 == menuItem) {
					return new com.hotelorga.core.web.client.rpc.guest2guestgroup.chooserlarge.ChooserLargeWidget();
				}

				if (menuItem11 == menuItem) {
					return new com.hotelorga.core.web.client.rpc.guest2acceptance.chooserlarge.ChooserLargeWidget();
				}

				if (menuItem12 == menuItem) {
					return new com.hotelorga.core.web.client.rpc.acceptance2payer.chooserlarge.ChooserLargeWidget();
				}

				return null;
			}
		};

		menuAndDisplay.addItem(menuItem0);
		menuAndDisplay.addItem(menuItem1);
		menuAndDisplay.addItem(menuItem2);
		menuAndDisplay.addItem(menuItem3);
		menuAndDisplay.addItem(menuItem4);
		menuAndDisplay.addItem(menuItem5);
		menuAndDisplay.addItem(menuItem6);
		menuAndDisplay.addItem(menuItem7);
		menuAndDisplay.addItem(menuItem8);
		menuAndDisplay.addItem(menuItem9);
		menuAndDisplay.addItem(menuItem10);
		menuAndDisplay.addItem(menuItem11);
		menuAndDisplay.addItem(menuItem12);

		menuAndDisplay.selectMenuItem(menuItem3);

	}

}
