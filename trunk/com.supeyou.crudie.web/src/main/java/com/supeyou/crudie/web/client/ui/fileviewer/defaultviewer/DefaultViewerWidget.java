package com.supeyou.crudie.web.client.ui.fileviewer.defaultviewer;

import com.google.gwt.user.client.Window;
import com.supeyou.crudie.web.client.resources.GWTSTATICS;

public class DefaultViewerWidget extends View {

	public DefaultViewerWidget(String contentURL) {

		Window.open(contentURL + "&" + GWTSTATICS.PARAMKEY_SENDASOCTETSTREAM, "_self", "status=0,toolbar=0,menubar=0,location=0");

	}

}
