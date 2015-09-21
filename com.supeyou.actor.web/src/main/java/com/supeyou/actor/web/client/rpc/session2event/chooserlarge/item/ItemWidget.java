package com.supeyou.actor.web.client.rpc.session2event.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.actor.iface.dto.Session2EventDTO;
import com.supeyou.actor.web.client.rpc.session2event.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private Session2EventDTO thisDTO;

	public ItemWidget(Session2EventDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(thisDTO.getDtoA().getHttpSessionId() + " <-> " + thisDTO.getDtoB().getAction()));

	}

	private CRUDProxyListener<Session2EventDTO> listener = new CRUDProxyListener<Session2EventDTO>() {

		@Override
		public void wasUpdated(Session2EventDTO dto) {

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
		public void wasDeleted(Session2EventDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(Session2EventDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
