package com.supeyou.core.web.client.mainmenu.adminmenu.coremenu;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.web.client.rpc.hero.chooserlarge.item.ItemWidget;
import com.supeyou.core.web.client.rpc.hero.form.FormWidget;
import com.supeyou.crudie.web.client.uiorga.menuanddisplay.MenuAndDisplay;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;
import com.supeyou.crudie.web.client.uiorga.popup.contentwrapper.ContentWrapperWidget;

public class CoreMenuWidget extends WidgetView {

	public CoreMenuWidget() {

		final Label menuItem0 = new Label("Donation");
		final Label menuItem1 = new Label("Hero");

		MenuAndDisplay menuAndDisplay = new MenuAndDisplay(menu, display) {

			@Override
			public Widget getWidgetFor(Widget menuItem) {

				if (menuItem0 == menuItem) {
					return new com.supeyou.core.web.client.rpc.donation.chooserlarge.ChooserLargeWidget();
				}

				if (menuItem1 == menuItem) {

					return new com.supeyou.core.web.client.rpc.hero.chooserlarge.ChooserLargeWidget() {

						@Override
						public void onSelectionChange(List<HeroDTO> selection) {

							if (selection.size() == 1) {

								new PopupWidget(new ContentWrapperWidget("Hero-Form", new FormWidget(dataProvider, selection.iterator().next()) {
									@Override
									protected void close() {
										widgetList.getSelectionModel().clearSelection();
									}
								}), false);

							} else {
								Window.alert("selection size has to be equal one (codemarker=asorg0)");
							}
						}

						public ItemWidget getHeroWidget(final HeroDTO data) {

							return new ItemWidget(data);
						}
					};
				}

				return null;
			}
		};

		menuAndDisplay.addItem(menuItem0);
		menuAndDisplay.addItem(menuItem1);

		menuAndDisplay.selectMenuItem(menuItem1);

	}
}
