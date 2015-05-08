package com.supeyou.crudie.web.client.model;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.crudie.web.client.rpc.RPCServiceAsync;

public class AppInfoModel extends AbstrObservable<AppInfoModel> {
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void updateFromServer() {

		RPCServiceAsync.i.getVersion(new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				setVersion(result);
				hasChanged();
			}

			@Override
			public void onFailure(Throwable caught) {

				Window.alert("codemarker=ga0g00");

			}
		});

	}

	@Override
	public AppInfoModel getModel() {
		return this;
	}

}
