package com.supeyou.actor.web.client.login;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.crudie.iface.datatype.types.PositivIntegerType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.web.client.resources.GoogleAnalytics;

public class ActorStatics {

	private static long loadingTimeStamp = System.currentTimeMillis();

	public static final String AUTHTOKEN_KEY = "auth";

	public static void fireActorEvent(String category) {
		fireActorEvent(category, "", "");
	}

	public static void fireActorEvent(String category, String action) {
		fireActorEvent(category, action, "");
	}

	public static void fireActorEvent(String category, String action, String value) {
		com.supeyou.actor.web.client.rpc.session2event.RPCCRUDServiceAsync.i.addEventToSession(new SingleLineString256Type(category), new SingleLineString256Type(action), new SingleLineString256Type(value), new PositivIntegerType((System.currentTimeMillis() - loadingTimeStamp) / 1000 + ""), new AsyncCallback<Void>() {

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
