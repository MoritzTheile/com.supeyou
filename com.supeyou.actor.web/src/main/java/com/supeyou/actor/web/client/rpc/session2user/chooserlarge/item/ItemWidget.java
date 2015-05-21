package com.supeyou.actor.web.client.rpc.session2user.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.actor.iface.dto.Session2UserDTO;
import com.supeyou.actor.web.client.rpc.session2user.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private Session2UserDTO thisDTO;

	public ItemWidget(Session2UserDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(thisDTO.getDtoA().getHttpSessionId() + " <-> " + thisDTO.getDtoB().getLoginId()));

	}

	private CRUDProxyListener<Session2UserDTO> listener = new CRUDProxyListener<Session2UserDTO>() {

		@Override
		public void wasUpdated(Session2UserDTO dto) {

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
		public void wasDeleted(Session2UserDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(Session2UserDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
