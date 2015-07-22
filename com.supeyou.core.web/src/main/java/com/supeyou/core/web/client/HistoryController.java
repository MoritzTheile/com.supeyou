package com.supeyou.core.web.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import com.supeyou.core.iface.dto.HeroIDType;
import com.supeyou.core.web.client.mainmenu.MainMenuWidget;
import com.supeyou.core.web.client.view.heropage.HeroPageWidget;
import com.supeyou.core.web.client.view.landingpage.LandingPageWidget;

public class HistoryController {

	public enum ANCHOR {
		LP, HEROS, HERO
	}

	private HistoryController() {

		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {

				String anchor = event.getValue();

				show(anchor);
			}

		});
	}

	public void show(String anchor) {
		// there can be ANCHORS with args seperated by underscore
		String[] anchorParts = anchor.split("_");

		if (ANCHOR.LP.name().equals(anchorParts[0])) {
			showLandingPage();
		} else if (ANCHOR.HERO.name().equals(anchorParts[0])) {
			showHeroPage(anchorParts);
		} else if (ANCHOR.HEROS.name().equals(anchorParts[0])) {
			showHerosPage();
		} else {
			showLandingPage();
		}
	}

	private void showHerosPage() {

		RootPanel.get("main").clear();
		RootPanel.get("main").add(new MainMenuWidget());

	}

	private static HistoryController instance;

	public static HistoryController i() {
		if (instance == null) {
			instance = new HistoryController();
		}
		return instance;
	}

	private void showLandingPage() {
		RootPanel.get("main").clear();
		RootPanel.get("main").add(new LandingPageWidget());

	}

	private void showHeroPage(String[] anchor) {

		try {
			RootPanel.get("main").clear();
			RootPanel.get("main").add(new HeroPageWidget(new HeroIDType(anchor[1])));
		} catch (Exception e) {
			// nothing
		}

	}

}
