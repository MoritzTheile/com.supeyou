package com.supeyou.core.web.client.rpc.donation.chooserlarge.item;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class WidgetView extends Composite {

	private static ThisBinder thisBinder = GWT
			.create(ThisBinder.class);

	@UiField
	FlowPanel itemRoot;

	@UiField
	FlowPanel itemNameSlot;
	@UiField
	FlowPanel itemNumberSlot;
	@UiField
	FlowPanel paymentStatusSlot;
	@UiField
	FlowPanel paymentAmountSlot;
	@UiField
	FlowPanel paymentCurrencySlot;
	@UiField
	FlowPanel txnIdSlot;
	@UiField
	FlowPanel receiverEmailSlot;
	@UiField
	FlowPanel payerEmailSlot;
	@UiField
	FlowPanel responseSlot;
	@UiField
	FlowPanel requstParamySlot;
	@UiField
	FlowPanel errorSlot;

	interface ThisBinder extends UiBinder<Widget, WidgetView> {
	}

	public WidgetView() {
		initWidget(thisBinder.createAndBindUi(this));

	}

}
