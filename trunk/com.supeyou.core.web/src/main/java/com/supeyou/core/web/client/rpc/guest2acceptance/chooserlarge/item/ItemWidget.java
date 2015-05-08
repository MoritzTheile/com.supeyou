package com.supeyou.core.web.client.rpc.guest2acceptance.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.core.iface.dto.Guest2AcceptanceDTO;
import com.supeyou.core.web.client.rpc.acceptance.Statics;
import com.supeyou.core.web.client.rpc.guest2acceptance.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private Guest2AcceptanceDTO thisDTO;

	public ItemWidget(Guest2AcceptanceDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(com.supeyou.core.web.client.rpc.guest.choosersmall.item.ItemWidget.renderInfoString(thisDTO.getDtoA()) + " <-> " + Statics.renderDescription(thisDTO.getDtoB())));

	}

	private CRUDProxyListener<Guest2AcceptanceDTO> listener = new CRUDProxyListener<Guest2AcceptanceDTO>() {

		@Override
		public void wasUpdated(Guest2AcceptanceDTO dto) {

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
		public void wasDeleted(Guest2AcceptanceDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(Guest2AcceptanceDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
