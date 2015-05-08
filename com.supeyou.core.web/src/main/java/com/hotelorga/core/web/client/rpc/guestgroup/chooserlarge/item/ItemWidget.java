package com.hotelorga.core.web.client.rpc.guestgroup.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.hotelorga.core.iface.dto.GuestGroupDTO;
import com.hotelorga.core.web.client.rpc.guestgroup.RPCCRUDProxy;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private GuestGroupDTO thisDTO;

	public ItemWidget(GuestGroupDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(renderInfoString()));

		columnThree.clear();
		columnThree.add(new com.hotelorga.core.web.client.rpc.guest2guestgroup.assoaeditor.AssoAEditorWidget(thisDTO, true));

	}

	private String renderInfoString() {
		String infos = "Rechnungsgruppe ";
		if (thisDTO.getName() != null) {
			infos += thisDTO.getName().value();
		}

		return infos;
	}

	private CRUDProxyListener<GuestGroupDTO> listener = new CRUDProxyListener<GuestGroupDTO>() {

		@Override
		public void wasUpdated(GuestGroupDTO dto) {

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
		public void wasDeleted(GuestGroupDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(GuestGroupDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
