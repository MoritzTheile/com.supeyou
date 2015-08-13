package com.supeyou.crudie.web.client.uiorga.menuanddisplay;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public abstract class MenuAndDisplay {

	private final Panel menuSlot;
	private final Panel displaySlot;
	private final List<Widget> menuItems = new ArrayList<Widget>();
	private Widget selectedMenuItem = null;

	public MenuAndDisplay(Panel menuSlot, Panel displaySlot) {

		this.menuSlot = menuSlot;
		this.displaySlot = displaySlot;

	}

	public void addItem(final Widget menuItem) {

		menuItems.add(menuItem);

		menuItem.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				selectMenuItem(menuItem);
			}

		}, ClickEvent.getType());

		menuSlot.add(menuItem);

	}

	public void clear() {
		menuItems.clear();
		menuSlot.clear();
		displaySlot.clear();
		menuSlot.clear();
		selectedMenuItem = null;
	}

	public void selectMenuItem(Widget menuItem) {
		if (menuItem == null) {
			return;
		}
		if (menuItem.equals(selectedMenuItem)) {
			return;
		}
		String styleName = "selected";
		if (selectedMenuItem != null) {
			selectedMenuItem.removeStyleName(styleName);
		}
		menuItem.addStyleName(styleName);
		selectedMenuItem = menuItem;
		displaySlot.clear();
		displaySlot.add(getWidgetFor(menuItem));

	}

	public Widget getSelectedMenuItem() {
		return selectedMenuItem;
	}

	public abstract Widget getWidgetFor(Widget menuItem);
}
