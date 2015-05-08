package com.hotelorga.core.web.client.rpc.room.choosersmall.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.hotelorga.core.iface.dto.RoomDTO;
import com.hotelorga.core.web.client.rpc.room.RPCCRUDProxy;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private RoomDTO thisDTO;

	public ItemWidget(RoomDTO dto) {

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

	private CRUDProxyListener<RoomDTO> listener = new CRUDProxyListener<RoomDTO>() {

		@Override
		public void wasUpdated(RoomDTO dto) {

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
		public void wasDeleted(RoomDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(RoomDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
