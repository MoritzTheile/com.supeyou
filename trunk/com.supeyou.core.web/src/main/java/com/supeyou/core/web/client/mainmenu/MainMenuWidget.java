package com.supeyou.core.web.client.mainmenu;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.web.client.mainmenu.addhero.AddHeroWidget;
import com.supeyou.core.web.client.mainmenu.adminmenu.AdminMenuWidget;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.rpc.hero.chooserlarge.ChooserLargeWidget;
import com.supeyou.crudie.web.client.model.AbstrObservable.Observer;
import com.supeyou.crudie.web.client.model.LoginStateModel;
import com.supeyou.crudie.web.client.uiorga.menuanddisplay.MenuAndDisplay;

public class MainMenuWidget extends WidgetView {

	private final MenuAndDisplay menuAndDisplay;

	private final Label menuItem1 = new Label(Text.i.HEROSPAGE_ChooseHero());
	private final Label menuItem2 = new Label(Text.i.HEROSPAGE_JoinAsHero());
	private final Label menuItem3 = new Label(Text.i.HEROSPAGE_Admin());

	public MainMenuWidget() {

		LoginStateModel.i().addObserver(new Observer<Void>() {

			@Override
			public void modelHasChanged(Void changes) {
				render();

			}

		});

		menuAndDisplay = new MenuAndDisplay(menu, display) {

			@Override
			public Widget getWidgetFor(Widget menuItem) {

				if (menuItem1 == menuItem) {
					return new ChooserLargeWidget();
				}

				if (menuItem2 == menuItem) {
					return new AddHeroWidget();
				}

				if (menuItem3 == menuItem) {
					return new AdminMenuWidget();
				}

				return null;
			}
		};

		render();

	}

	private void render() {

		Widget selectedMenuItem = menuAndDisplay.getSelectedMenuItem();

		menuAndDisplay.clear();

		menuAndDisplay.addItem(menuItem1);
		menuAndDisplay.addItem(menuItem2);

		if (LoginStateModel.i().userIsAdmin()) {

			menuAndDisplay.addItem(menuItem3);

		}

		if (selectedMenuItem != null) {

			menuAndDisplay.selectMenuItem(selectedMenuItem);

		}

		if (menuAndDisplay.getSelectedMenuItem() == null) {

			menuAndDisplay.selectMenuItem(menuItem1);

		}

	}

}
