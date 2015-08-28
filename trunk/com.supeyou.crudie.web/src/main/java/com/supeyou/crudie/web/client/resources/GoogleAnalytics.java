package com.supeyou.crudie.web.client.resources;

import com.google.gwt.core.client.GWT;

public class GoogleAnalytics {

	// see https://developers.google.com/analytics/devguides/collection/analyticsjs/events
	public static final GoogleAnalytics i = new GoogleAnalytics();

	public static final boolean isProductive = GWT.getHostPageBaseURL().toLowerCase().contains("supeyou.com");

	private GoogleAnalytics() {
		if (isProductive) {
			initNative();
		}
	}

	private native void initNative() /*-{
		$wnd.ga('create', 'UA-26226518-2', 'auto');
		$wnd.ga('send', 'pageview');
	}-*/;

	public void sendEvent(String category, String action, String label, String value) {
		if (isProductive) {
			gaNative(category, action, label, value);
		}
	}

	public void sendEvent(String category, String action, String label) {
		if (isProductive) {
			gaNative(category, action, label);
		}
	}

	public void sendEvent(String category, String action) {
		if (isProductive) {
			gaNative(category, action);
		}
	}

	private native void gaNative(String category, String action, String label, String value) /*-{
		$wnd.ga('send', 'event', category, action, label, value);
	}-*/;

	private native void gaNative(String category, String action, String label) /*-{
		$wnd.ga('send', 'event', category, action, label);
	}-*/;

	private native void gaNative(String category, String action) /*-{
		$wnd.ga('send', 'event', category, action);
	}-*/;

}
