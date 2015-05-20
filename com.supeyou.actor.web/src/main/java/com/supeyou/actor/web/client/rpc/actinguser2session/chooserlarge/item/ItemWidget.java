package com.supeyou.actor.web.client.rpc.actinguser2session.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.actor.iface.dto.ActingUser2SessionDTO;
import com.supeyou.actor.web.client.rpc.actinguser2session.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private ActingUser2SessionDTO thisDTO;

	public ItemWidget(ActingUser2SessionDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(thisDTO.getDtoA().getLoginId() + " <-> " + thisDTO.getDtoB().getHttpSessionId()));

	}

	private CRUDProxyListener<ActingUser2SessionDTO> listener = new CRUDProxyListener<ActingUser2SessionDTO>() {

		@Override
		public void wasUpdated(ActingUser2SessionDTO dto) {

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
		public void wasDeleted(ActingUser2SessionDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(ActingUser2SessionDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
