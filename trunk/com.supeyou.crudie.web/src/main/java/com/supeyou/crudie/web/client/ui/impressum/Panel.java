package com.supeyou.crudie.web.client.ui.impressum;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class Panel extends Composite {

	private static ThisBinder thisBinder = GWT
			.create(ThisBinder.class);

	interface ThisBinder extends UiBinder<Widget, Panel> {
	}

	@UiField
	Label label;

	public Panel() {
		initWidget(thisBinder.createAndBindUi(this));

	}

}
