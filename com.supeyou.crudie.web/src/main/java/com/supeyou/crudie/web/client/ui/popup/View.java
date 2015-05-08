package com.supeyou.crudie.web.client.ui.popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class View extends Composite {

	private static RoleListUiBinder uiBinder = GWT
			.create(RoleListUiBinder.class);
	@UiField
	FlowPanel content;

	interface RoleListUiBinder extends UiBinder<Widget, View> {
	}

	public View() {

		initWidget(uiBinder.createAndBindUi(this));

		com.supeyou.crudie.web.client.resources.GWTSTATICS.setJClassAsCSSClass(this);

	}

}
