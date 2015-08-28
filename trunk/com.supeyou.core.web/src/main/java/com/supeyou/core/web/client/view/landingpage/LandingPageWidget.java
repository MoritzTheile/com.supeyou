package com.supeyou.core.web.client.view.landingpage;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.supeyou.core.web.client.HistoryController.ANCHOR;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.crudie.web.client.resources.GoogleAnalytics;
import com.supeyou.crudie.web.client.uiorga.flatbutton.FlatButtonWidget;

public class LandingPageWidget extends WidgetView {

	public LandingPageWidget() {

		FlatButtonWidget flatButtonWidget = new FlatButtonWidget();
		flatButtonWidget.setText(Text.i.HEROSPAGE_ChooseHero());
		flatButtonWidget.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				GoogleAnalytics.i.sendEvent("click", "buttonChooseHero");

				History.newItem(ANCHOR.HEROS.name());

			}
		});

		chooseHeroButtonSlot.add(flatButtonWidget);

	}

}
