package com.supeyou.actor.web.client.login;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.crudie.web.client.resources.GoogleAnalytics;

public class ActorStatics {

	public static final String AUTHTOKEN_KEY = "auth";

	public static void fireActorEvent(String category) {
		fireActorEvent(category, "", "");
	}

	public static void fireActorEvent(String category, String action) {
		fireActorEvent(category, action, "");
	}

	public static void fireActorEvent(String category, String action, String value) {
		com.supeyou.actor.web.client.rpc.session2event.RPCCRUDServiceAsync.i.addEventToSession(category, action, value, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				// nothing

			}

			@Override
			public void onFailure(Throwable caught) {

				caught.printStackTrace();

			}
		});

		GoogleAnalytics.i.sendEvent(category, action, value);
	}

}
