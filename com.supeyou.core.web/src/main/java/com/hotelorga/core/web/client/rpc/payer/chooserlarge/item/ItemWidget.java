package com.hotelorga.core.web.client.rpc.payer.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.hotelorga.core.iface.dto.PayerDTO;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.hotelorga.core.web.client.rpc.payer.RPCCRUDProxy;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private PayerDTO thisDTO;

	public ItemWidget(PayerDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(renderInfoString()));

	}

	private String renderInfoString() {
		String infos = "";
		if (thisDTO.getName() != null) {
			infos += thisDTO.getName().value();
		}

		return infos;
	}

	private CRUDProxyListener<PayerDTO> listener = new CRUDProxyListener<PayerDTO>() {

		@Override
		public void wasUpdated(PayerDTO dto) {

			final String updatedStyle = "updated";

			thisWidget.removeStyleName(updatedStyle);

			if (thisDTO.equals(dto)) {

				thisDTO = dto;

				// without the delay the change in style doesn't get picked up
				new Timer() {

					@Override
					public void run() {

						thisWidget.addStyleName(updatedStyle);

					}
				}.schedule(100);

				render();
			}
		}

		@Override
		public void wasDeleted(PayerDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(PayerDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
