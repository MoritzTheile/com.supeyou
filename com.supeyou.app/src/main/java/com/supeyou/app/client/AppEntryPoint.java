package com.supeyou.app.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.supeyou.actor.web.client.login.ActorStatics;
import com.supeyou.actor.web.client.login.loginbutton.LoginButtonWidget;
import com.supeyou.actor.web.client.rpc.RPCAuthServiceAsync;
import com.supeyou.core.web.client.HistoryController;
import com.supeyou.core.web.client.HistoryController.ANCHOR;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.web.client.model.AbstrObservable.Observer;
import com.supeyou.crudie.web.client.model.AppInfoModel;
import com.supeyou.crudie.web.client.model.LoginStateModel;
import com.supeyou.crudie.web.client.ui.version.VersionPresenter;

public class AppEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {

		RootPanel.get("login").add(new LoginButtonWidget(LoginStateModel.i()));

		LoginStateModel.i().addObserver(new Observer<Void>() {

			@Override
			public void modelHasChanged(Void changes) {

				if (LoginStateModel.i().getLoggedInUser() != null) {

					Window.alert("logged in user is "
							+ " " + LoginStateModel.i().getLoggedInUser().getLoginId());

				} else {

					Window.alert("no user logged in");

				}

			}
		});

		if (LoginStateModel.i().getLoggedInUser() == null) {// trying to login with authToken
			RPCAuthServiceAsync.i.authenticateActor(Window.Location.getParameter(ActorStatics.AUTHTOKEN_KEY), new AsyncCallback<UserDTO>() {

				@Override
				public void onFailure(Throwable caught) {

					caught.printStackTrace();

				}

				@Override
				public void onSuccess(UserDTO result) {

					LoginStateModel.i().setLoggedInUser(result);

				}
			});
		}

		//
		//
		// RPCAuthServiceAsync.i.getActorOfSession(new AsyncCallback<UserDTO>() {
		//
		// @Override
		// public void onSuccess(UserDTO result) {
		//
		// LoginStateModel.i().setLoggedInUser(result);
		//
		// }
		//
		// @Override
		// public void onFailure(Throwable caught) {
		//
		// caught.printStackTrace();
		//
		// }
		// });

		AppInfoModel appInfoModel = new AppInfoModel();
		RootPanel.get("footer").add(new VersionPresenter(appInfoModel));
		appInfoModel.updateFromServer();

		HistoryController.i();

		if (History.getToken() == null || History.getToken().equals("")) {
			History.newItem(ANCHOR.LP.name());
		} else {
			HistoryController.i().show(History.getToken());
		}

		RootPanel.get("main").add(new Label("Session"));
		RootPanel.get("main").add(new com.supeyou.actor.web.client.rpc.session.chooserlarge.ChooserLargeWidget());
		RootPanel.get("main").add(new Label("Session2User"));
		RootPanel.get("main").add(new com.supeyou.actor.web.client.rpc.session2user.chooserlarge.ChooserLargeWidget());
		RootPanel.get("main").add(new Label("User"));
		RootPanel.get("main").add(new com.supeyou.crudie.web.client.rpc.user.chooserlarge.ChooserLargeWidget());
		RootPanel.get("main").add(new Label("Supporter"));
		RootPanel.get("main").add(new com.supeyou.core.web.client.rpc.supporter.chooserlarge.ChooserLargeWidget());
		RootPanel.get("main").add(new Label("Supporter2Invitation"));
		RootPanel.get("main").add(new com.supeyou.core.web.client.rpc.supporter2invitation.chooserlarge.ChooserLargeWidget());
		RootPanel.get("main").add(new Label("Invitation2Supporter"));
		RootPanel.get("main").add(new com.supeyou.core.web.client.rpc.invitation2supporter.chooserlarge.ChooserLargeWidget());
		RootPanel.get("main").add(new Label("Invitation"));
		RootPanel.get("main").add(new com.supeyou.core.web.client.rpc.invitation.chooserlarge.ChooserLargeWidget());
		RootPanel.get("main").add(new Label("Supporter2Donation"));
		RootPanel.get("main").add(new com.supeyou.core.web.client.rpc.supporter2donation.chooserlarge.ChooserLargeWidget());
		RootPanel.get("main").add(new Label("Donation"));
		RootPanel.get("main").add(new com.supeyou.core.web.client.rpc.donation.chooserlarge.ChooserLargeWidget());

	}
}
