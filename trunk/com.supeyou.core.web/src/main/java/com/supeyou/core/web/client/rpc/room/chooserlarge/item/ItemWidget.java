package com.supeyou.core.web.client.rpc.room.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.core.iface.dto.RoomDTO;
import com.supeyou.core.web.client.rpc.room.RPCCRUDProxy;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private RoomDTO thisDTO;

	public ItemWidget(RoomDTO dto, DateType fromDate, DateType toDate) {
		this(dto);
	}

	public ItemWidget(RoomDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(renderInfoString()));
		columnTwo.clear();
		if (thisDTO.getComment() != null) {
			columnTwo.add(new Label(thisDTO.getComment().value()));
		}
	}

	private String renderInfoString() {
		String infos = "Zimmer ";
		if (thisDTO.getName() != null) {
			infos += thisDTO.getName().value();
		}
		if (thisDTO.getNumberOfBeds() != null) {
			if (thisDTO.getNumberOfBeds().value() == 1) {
				infos += " (" + thisDTO.getNumberOfBeds().value() + " Bett)";
			} else {
				infos += " (" + thisDTO.getNumberOfBeds().value() + " Betten)";
			}
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

	@Override
	protected void onLoad() {
		super.onLoad();
		render();
	}

}
