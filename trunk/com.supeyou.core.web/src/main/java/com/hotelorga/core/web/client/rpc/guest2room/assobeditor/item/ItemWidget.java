package com.hotelorga.core.web.client.rpc.guest2room.assobeditor.item;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.hotelorga.core.iface.dto.Guest2RoomDTO;
import com.hotelorga.core.web.client.rpc.guest2room.RPCCRUDProxy;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private Guest2RoomDTO thisDTO;

	public ItemWidget(Guest2RoomDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		deleteLink.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) { 

				event.stopPropagation();

				RPCCRUDProxy.i().delete(thisDTO);

			}
		}, ClickEvent.getType());

		render(thisDTO);

	}

	private void render(Guest2RoomDTO dto) {

		columnOne.clear();
		columnOne.add(new HTML(renderInfoHTML(thisDTO)));

	}

	protected String renderInfoHTML(Guest2RoomDTO dto) {
		String infos = "";
		infos += com.hotelorga.core.web.client.rpc.guest.choosersmall.item.ItemWidget.renderInfoString(thisDTO.getDtoA()) + " <-> " + dto.getDtoB().getName();

		return infos;
	}

	private CRUDProxyListener<Guest2RoomDTO> listener = new CRUDProxyListener<Guest2RoomDTO>() {

		@Override
		public void wasUpdated(final Guest2RoomDTO dto) {

			final String updatedStyle = "updated";

			thisWidget.removeStyleName(updatedStyle);

			if (thisDTO.equals(dto)) {

				// without the delay the change in style doesn't get picked up
				new Timer() {

					@Override
					public void run() {

						thisWidget.addStyleName(updatedStyle);

					}
				}.schedule(100);

				render(dto);
			}
		}

		@Override
		public void wasDeleted(Guest2RoomDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(Guest2RoomDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
