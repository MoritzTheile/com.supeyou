package com.supeyou.core.web.client.view.herocard.supporterinfo;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.rpc.supporter.RPCCRUDServiceAsync;
import com.supeyou.core.web.client.view.heropage.supportertree.SupporterTreeWidget;
import com.supeyou.crudie.iface.common.HELPER;

public class SupporterWidget extends WidgetView {

	public SupporterWidget(SupporterDTO supporterDTO) {

		pollingRender(supporterDTO);

	}

	private void pollingRender(final SupporterDTO supporterDTO) {

		yourGeneratedResult.setText(HELPER.cent2euro(SupporterTreeWidget.getAmountValueNullsave(supporterDTO.getDecendantAmount()) + SupporterTreeWidget.getAmountValueNullsave(supporterDTO.getOwnAmount())) + " " + Text.i.EUROSYMBOL());
		yourResult.setText(HELPER.cent2euro(SupporterTreeWidget.getAmountValueNullsave(supporterDTO.getOwnAmount())) + " " + Text.i.EUROSYMBOL());

		new Timer() {

			@Override
			public void run() {

				RPCCRUDServiceAsync.i.get(supporterDTO.getId(), new AsyncCallback<SupporterDTO>() {

					@Override
					public void onSuccess(SupporterDTO result) {

						pollingRender(result);

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
