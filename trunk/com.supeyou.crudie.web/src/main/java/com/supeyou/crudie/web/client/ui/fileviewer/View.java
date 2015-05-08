package com.supeyou.crudie.web.client.ui.fileviewer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class View extends Composite {

	private static ThisUiBinder uiBinder = GWT.create(ThisUiBinder.class);

	@UiField
	SimplePanel fileViewerRoot;

	interface ThisUiBinder extends UiBinder<Widget, View> {
	}

	public View() {

		initWidget(uiBinder.createAndBindUi(this));

	}

}
