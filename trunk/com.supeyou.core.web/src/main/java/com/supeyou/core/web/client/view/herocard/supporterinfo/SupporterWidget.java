package com.supeyou.core.web.client.view.herocard.supporterinfo;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.rpc.supporter.RPCCRUDServiceAsync;
import com.supeyou.core.web.client.view.heropage.supportertree.SupporterTreeWidget;
import com.supeyou.crudie.iface.common.HELPER;

public class SupporterWidget extends WidgetView {

	private final Widget thisWidget;

	public SupporterWidget(SupporterDTO supporterDTO) {

		thisWidget = this;

		pollingRender(supporterDTO);

	}

	private void pollingRender(final SupporterDTO supporterDTO) {

		yourDecendants.setText(supporterDTO.getDecendentCount() + " Supporter invited by you");

		if (0 < SupporterTreeWidget.getAmountValueNullsave(supporterDTO.getDecendantAmount()) + SupporterTreeWidget.getAmountValueNullsave(supporterDTO.getOwnAmount())) {

			yourGeneratedResult.setText(HELPER.cent2euro(SupporterTreeWidget.getAmountValueNullsave(supporterDTO.getDecendantAmount()) + SupporterTreeWidget.getAmountValueNullsave(supporterDTO.getOwnAmount())) + " " + Text.i.EUROSYMBOL());

			yourGeneratedResult.removeStyleName("hide");
			yourGeneratedResultLabel.removeStyleName("hide");

		} else {

			yourGeneratedResult.addStyleName("hide");
			yourGeneratedResultLabel.addStyleName("hide");

		}

		if (0 < SupporterTreeWidget.getAmountValueNullsave(supporterDTO.getOwnAmount())) {

			yourResult.setText(HELPER.cent2euro(SupporterTreeWidget.getAmountValueNullsave(supporterDTO.getDecendantAmount()) + SupporterTreeWidget.getAmountValueNullsave(supporterDTO.getOwnAmount())) + " " + Text.i.EUROSYMBOL());

			yourResult.removeStyleName("hide");
			yourResultLabel.removeStyleName("hide");

		} else {

			yourResult.addStyleName("hide");
			yourResultLabel.addStyleName("hide");

		}

		new Timer() {

			@Override
			public void run() {

				RPCCRUDServiceAsync.i.get(supporterDTO.getId(), new AsyncCallback<SupporterDTO>() {

					@Override
					public void onSuccess(SupporterDTO result) {

						if (thisWidget.isAttached()) {
							pollingRender(result);
						}

					}

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();

					}
				});

			}
		}.schedule(5000);
		;
	}

}
