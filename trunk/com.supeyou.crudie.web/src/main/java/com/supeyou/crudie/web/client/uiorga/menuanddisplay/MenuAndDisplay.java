package com.supeyou.crudie.web.client.uiorga.menuanddisplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public abstract class MenuAndDisplay {

	private final Panel menuSlot;
	private final Panel displaySlot;
	private final List<Widget> menuItems = new ArrayList<Widget>();
	private Widget selectedMenuItem = null;
	private final Map<String, Widget> menukey2displaywidget = new HashMap<String, Widget>();

	public MenuAndDisplay(Panel menuSlot, Panel displaySlot) {

		this.menuSlot = menuSlot;
		this.displaySlot = displaySlot;

		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				selectMenuItem(menukey2displaywidget.get(event.getValue()));
			}

		});

	}

	public String addItem(final Widget menuItem) {

		String stringkey = menuItemToStringKey(menuItem);

		menukey2displaywidget.put(stringkey, menuItem);

		menuItems.add(menuItem);

		menuItem.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				selectMenuItem(menuItem);
			}

		}, ClickEvent.getType());

		menuSlot.add(menuItem);

		return stringkey;
	}

	private String menuItemToStringKey(final Widget menuItem) {
		return menuItem.hashCode() + "";
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
		History.newItem(menuItemToStringKey(menuItem), false);
		displaySlot.clear();
		displaySlot.add(getWidgetFor(menuItem));

	}

	public abstract Widget getWidgetFor(Widget menuItem);
}
