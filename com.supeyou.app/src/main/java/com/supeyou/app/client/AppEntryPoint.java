package com.supeyou.app.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.supeyou.actor.web.client.login.ActorStatics;
import com.supeyou.actor.web.client.login.loginbutton.LoginButtonWidget;
import com.supeyou.actor.web.client.rpc.RPCAuthServiceAsync;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.HistoryController;
import com.supeyou.core.web.client.HistoryController.ANCHOR;
import com.supeyou.core.web.client.resources.CoreStatics;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDServiceAsync;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.web.client.model.AppInfoModel;
import com.supeyou.crudie.web.client.model.LoginStateModel;
import com.supeyou.crudie.web.client.ui.impressum.ImpressumWidget;
import com.supeyou.crudie.web.client.ui.version.VersionWidget;

public class AppEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// RootPanel.get().clear();
		// RootPanel.get().add(new DonationWidget("paypal.com@moritztheile.de", "Support Hero", "Hero_"));
		// }
		//

		RootPanel.get("login").add(new LoginButtonWidget(LoginStateModel.i()));
		RootPanel.get("title").addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				ActorStatics.fireActorEvent("click", "SupeYouHome");

				History.newItem(ANCHOR.LP.name());

			}
		}, ClickEvent.getType());

		HistoryController.i();

		AppInfoModel appInfoModel = new AppInfoModel();
		RootPanel.get("footer").add(new ImpressumWidget());

		RootPanel.get("footer").add(new VersionWidget(appInfoModel));
		appInfoModel.updateFromServer();

		if (LoginStateModel.i().getLoggedInUser() == null) {// trying to login with authToken

			if (Window.Location.getParameter(ActorStatics.AUTHTOKEN_KEY) != null) {

				RPCAuthServiceAsync.i.authenticateActor(Window.Location.getParameter(ActorStatics.AUTHTOKEN_KEY), new AsyncCallback<UserDTO>() {

					@Override
					public void onFailure(Throwable caught) {

						caught.printStackTrace();

					}

					@Override
					public void onSuccess(UserDTO result) {

						LoginStateModel.i().setLoggedInUser(result);
						postAuthInitilization();

					}
				});
			} else {

				RPCAuthServiceAsync.i.getActorOfSession(new AsyncCallback<UserDTO>() {

					@Override
					public void onSuccess(UserDTO result) {

						LoginStateModel.i().setLoggedInUser(result);
						postAuthInitilization();

					}

					@Override
					public void onFailure(Throwable caught) {

						caught.printStackTrace();

					}

				});

			}

		} else {

			postAuthInitilization();

		}

	}

	private void postAuthInitilization() {

		if (Window.Location.getParameter(CoreStatics.INVITTOKEN_KEY) != null) {

			RPCCRUDServiceAsync.i.followInvitation(LoginStateModel.i().getLoggedInUser(), new SingleLineString256Type(Window.Location.getParameter(CoreStatics.INVITTOKEN_KEY)), new AsyncCallback<SupporterDTO>() {

				@Override
				public void onFailure(Throwable caught) {

					caught.printStackTrace();

				}

				@Override
				public void onSuccess(SupporterDTO result) {

					if (result.getHeroDTO() != null) {
						History.newItem(ANCHOR.HERO.name() + "_" + result.getHeroDTO().getId().value(), false);
						History.fireCurrentHistoryState();
					} else {
						Window.alert("no hero found for invitation " + Window.Location.getParameter(CoreStatics.INVITTOKEN_KEY));
						History.newItem(ANCHOR.LP.name());
					}

				}

			});

		} else {

			History.fireCurrentHistoryState();

		}

		// private void sdf() {
		//
		// RootPanel.get("main").add(new Label("Session"));
		// RootPanel.get("main").add(new com.supeyou.actor.web.client.rpc.session.chooserlarge.ChooserLargeWidget());
		// RootPanel.get("main").add(new Label("Session2User"));
		// RootPanel.get("main").add(new com.supeyou.actor.web.client.rpc.session2user.chooserlarge.ChooserLargeWidget());
		// RootPanel.get("main").add(new Label("User"));
		// RootPanel.get("main").add(new com.supeyou.crudie.web.client.rpc.user.chooserlarge.ChooserLargeWidget());
		// RootPanel.get("main").add(new Label("Supporter"));
		// RootPanel.get("main").add(new com.supeyou.core.web.client.rpc.supporter.chooserlarge.ChooserLargeWidget());
		// RootPanel.get("main").add(new Label("Supporter2Invitation"));
		// RootPanel.get("main").add(new com.supeyou.core.web.client.rpc.supporter2invitation.chooserlarge.ChooserLargeWidget());
		// RootPanel.get("main").add(new Label("Invitation2Supporter"));
		// RootPanel.get("main").add(new com.supeyou.core.web.client.rpc.invitation2supporter.chooserlarge.ChooserLargeWidget());
		// RootPanel.get("main").add(new Label("Invitation"));
		// RootPanel.get("main").add(new com.supeyou.core.web.client.rpc.invitation.chooserlarge.ChooserLargeWidget());
		// RootPanel.get("main").add(new Label("Supporter2Donation"));
		// RootPanel.get("main").add(new com.supeyou.core.web.client.rpc.supporter2donation.chooserlarge.ChooserLargeWidget());
		// RootPanel.get("main").add(new Label("Donation"));
		// RootPanel.get("main").add(new com.supeyou.core.web.client.rpc.donation.chooserlarge.ChooserLargeWidget());
		//
		// }
	}
}
