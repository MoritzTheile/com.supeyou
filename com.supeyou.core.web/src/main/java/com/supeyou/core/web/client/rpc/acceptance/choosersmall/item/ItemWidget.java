package com.supeyou.core.web.client.rpc.acceptance.choosersmall.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.web.client.rpc.acceptance.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.acceptance.Statics;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private AcceptanceDTO thisDTO;

	public ItemWidget(AcceptanceDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(Statics.renderDescription(thisDTO)));

	}

	private CRUDProxyListener<AcceptanceDTO> listener = new CRUDProxyListener<AcceptanceDTO>() {

		@Override
		public void wasUpdated(AcceptanceDTO dto) {

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
		public void wasDeleted(AcceptanceDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(AcceptanceDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
