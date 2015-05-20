package com.supeyou.actor.web.client.login.login;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.actor.web.client.resources.i18n.Text;
import com.supeyou.actor.web.client.rpc.RPCAuthServiceAsync;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.web.client.model.LoginStateModel;

public abstract class LoginWidget extends WidgetView {

	private final LoginStateModel loginStateModel;

	public LoginWidget(final LoginStateModel loginStateModel) {

		this.loginStateModel = loginStateModel;

		passwordInput.setPasswordType(true);
		this.addDomHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {

				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {

					login();

				}

			}
		}, KeyUpEvent.getType());

		flatButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				login();

			}

		});

		cancelFlatButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				close();

			}

		});
	}

	@Override
	protected void onLoad() {

		nameInput.setFocus();

		// if (Location.getHostName().contains("127.0.0.1") || Location.getPath().contains("supeyou_latest")) { // default login autologin
		// nameInput.setOrigValue(new SingleLineString256Type("MT"));
		// nameInput.value2field();
		// passwordInput.setOrigValue(new SingleLineString256Type("mutzmutz"));
		// passwordInput.render();
		// login();
		//
		// }

	}

	public void userLoggedIn(UserDTO result) {

	}

	private void login() {
		String user = nameInput.getValue().value();
		String pass = passwordInput.getValue().value();

		RPCAuthServiceAsync.i.authenticateActor(user, pass, new AsyncCallback<UserDTO>() {

			@Override
			public void onSuccess(UserDTO result) {

				userLoggedIn(result);

				if (result != null) {
					loginStateModel.setLoggedInUser(result);
				} else {
					messageLabel.setText(Text.i.LOGIN_WrongCredentials());
				}

			}

			@Override
			public void onFailure(Throwable caught) {

				caught.printStackTrace();

			}

		});
	}

	public abstract void close();

}
