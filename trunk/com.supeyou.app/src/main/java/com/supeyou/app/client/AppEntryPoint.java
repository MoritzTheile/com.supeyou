package com.supeyou.app.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.supeyou.auth.web.client.login.loginbutton.LoginButtonWidget;
import com.supeyou.core.web.client.mainmenu.MainMenuWidget;
import com.supeyou.crudie.web.client.model.AppInfoModel;
import com.supeyou.crudie.web.client.model.LoginStateModel;
import com.supeyou.crudie.web.client.model.AbstrObservable.Observer;
import com.supeyou.crudie.web.client.ui.version.VersionPresenter;

public class AppEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {

		RootPanel.get("title").getElement().setInnerHTML("Hotel Waltram");

		RootPanel.get("login").add(new LoginButtonWidget(LoginStateModel.i()));

		LoginStateModel.i().addObserver(new Observer<Void>() {

			@Override
			public void modelHasChanged(Void changes) {

				if (LoginStateModel.i().getLoggedInUser() != null) {

					// RootPanel.get("main").add(new MainMenuWidget());
					RootPanel.get("main").add(new MainMenuWidget());

				} else {
					RootPanel.get("main").clear();
				}

			}
		});

		AppInfoModel appInfoModel = new AppInfoModel();
		RootPanel.get("footer").add(new VersionPresenter(appInfoModel));
		appInfoModel.updateFromServer();
	}

}
