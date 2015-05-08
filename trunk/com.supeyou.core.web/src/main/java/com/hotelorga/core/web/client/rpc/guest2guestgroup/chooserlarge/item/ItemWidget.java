package com.hotelorga.core.web.client.rpc.guest2guestgroup.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.hotelorga.core.iface.dto.Guest2GuestGroupDTO;
import com.hotelorga.core.web.client.rpc.guest2guestgroup.RPCCRUDProxy;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private Guest2GuestGroupDTO thisDTO;

	public ItemWidget(Guest2GuestGroupDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(com.hotelorga.core.web.client.rpc.guest.choosersmall.item.ItemWidget.renderInfoString(thisDTO.getDtoA()) + " <-> " + thisDTO.getDtoB().getName()));

	}

	private CRUDProxyListener<Guest2GuestGroupDTO> listener = new CRUDProxyListener<Guest2GuestGroupDTO>() {

		@Override
		public void wasUpdated(Guest2GuestGroupDTO dto) {

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
		public void wasDeleted(Guest2GuestGroupDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(Guest2GuestGroupDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
