package com.supeyou.actor.web.client.rpc.event.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.actor.iface.dto.EventDTO;
import com.supeyou.actor.web.client.rpc.event.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private EventDTO thisDTO;

	public ItemWidget(EventDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(renderInfoString()));

		columnThree.clear();
		// columnThree.add(new com.supeyou.crudie.web.client.rpc.user2group.assoaeditor.AssoAEditorWidget(thisDTO, false));

	}

	private String renderInfoString() {
		String infos = "";
		if (thisDTO.getId() != null) {
			infos += thisDTO.getId().value();
		}
		if (thisDTO.getCategory() != null) {
			infos += thisDTO.getCategory().value();
		}
		if (thisDTO.getAction() != null) {
			infos += thisDTO.getAction().value();
		}
		if (thisDTO.getValue() != null) {
			infos += thisDTO.getValue().value();
		}

		return infos;
	}

	private CRUDProxyListener<EventDTO> listener = new CRUDProxyListener<EventDTO>() {

		@Override
		public void wasUpdated(EventDTO dto) {

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
		public void wasDeleted(EventDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(EventDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
