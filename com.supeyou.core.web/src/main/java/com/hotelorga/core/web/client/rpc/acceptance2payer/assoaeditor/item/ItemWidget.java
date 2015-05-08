package com.hotelorga.core.web.client.rpc.acceptance2payer.assoaeditor.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.hotelorga.core.iface.dto.Acceptance2PayerDTO;
import com.hotelorga.core.web.client.rpc.acceptance.Statics;
import com.hotelorga.core.web.client.rpc.acceptance2payer.RPCCRUDProxy;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private Acceptance2PayerDTO thisDTO;

	public ItemWidget(Acceptance2PayerDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(Statics.renderDescription(thisDTO.getDtoA()) + " <-> " + thisDTO.getDtoB().getName()));

	}

	private CRUDProxyListener<Acceptance2PayerDTO> listener = new CRUDProxyListener<Acceptance2PayerDTO>() {

		@Override
		public void wasUpdated(Acceptance2PayerDTO dto) {

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
		public void wasDeleted(Acceptance2PayerDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(Acceptance2PayerDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
