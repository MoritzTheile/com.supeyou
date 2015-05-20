package com.supeyou.actor.web.client.login.loginbutton;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.actor.web.client.login.login.LoginWidget;
import com.supeyou.actor.web.client.resources.i18n.Text;
import com.supeyou.actor.web.client.rpc.RPCAuthServiceAsync;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.web.client.model.AbstrObservable.Observer;
import com.supeyou.crudie.web.client.model.LoginStateModel;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class LoginButtonWidget extends WidgetView {

	private final LoginStateModel thisLoginStateModel;

	public LoginButtonWidget(LoginStateModel loginStateModel) {

		thisLoginStateModel = loginStateModel;

		loginStateModel.addObserver(new Observer<Void>() {

			@Override
			public void modelHasChanged(Void changes) {

				render();

			}
		});

		logInOutButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				if (thisLoginStateModel.getLoggedInUser() == null) {

					login();

				} else {

					logout();

				}

			}

		});

		render();

		// autologin
		// new Timer() {
		// @Override
		// public void run() {
		// login();
		//
		// }
		// }.schedule(200);

	}

	private void login() {

		final PopupWidget popupWidget = new PopupWidget(true);

		LoginWidget loginWidget = new LoginWidget(thisLoginStateModel) {

			@Override
			public void userLoggedIn(UserDTO result) {

				if (result != null) {
					popupWidget.closePopup();
				}

				super.userLoggedIn(result);

			}

			@Override
			public void close() {

				popupWidget.closePopup();
			}

		};

		popupWidget.init(loginWidget);
	}

	private void logout() {
		RPCAuthServiceAsync.i.sessionLogout(new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				thisLoginStateModel.setLoggedInUser(null);
			}

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
			}

		});
	}

	private void render() {

		if (thisLoginStateModel.getLoggedInUser() == null) {
			logInOutButton.setText(Text.i.LOGIN_BUTTON_Login());
		} else {
			logInOutButton.setText(Text.i.LOGIN_BUTTON_Logout());
		}
	}

}
