package com.supeyou.core.web.client.view.landingpage;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.supeyou.actor.web.client.login.ActorStatics;
import com.supeyou.core.web.client.HistoryController.ANCHOR;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.view.landingpage.vision.VisionWidget;
import com.supeyou.crudie.web.client.uiorga.flatbutton.FlatButtonWidget;

public class LandingPageWidget extends WidgetView {

	private ClickHandler clickHandler = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {

			ActorStatics.fireActorEvent("click", "buttonChooseHero");

			History.newItem(ANCHOR.HEROES.name());

		}
	};

	public LandingPageWidget() {

		chooseHeroButtonSlot.add(getButton());
		chooseHeroButtonSlot2.add(getButton());

		visionSlot.add(new VisionWidget());

	}

	private FlatButtonWidget getButton() {
		FlatButtonWidget flatButtonWidget;
		flatButtonWidget = new FlatButtonWidget();
		flatButtonWidget.setText(Text.i.HEROSPAGE_ChooseHero());
		flatButtonWidget.addClickHandler(clickHandler);
		return flatButtonWidget;
	}

}
