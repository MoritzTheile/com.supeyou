package com.supeyou.actor.web.client.rpc.actinguser.choosersmall.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.actor.iface.dto.ActingUserDTO;
import com.supeyou.actor.web.client.rpc.actinguser.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private ActingUserDTO thisDTO;

	public ItemWidget(ActingUserDTO dto) {

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
		if (thisDTO.getLoginId() != null) {
			infos += thisDTO.getLoginId().value();
		}

		return infos;
	}

	private CRUDProxyListener<ActingUserDTO> listener = new CRUDProxyListener<ActingUserDTO>() {

		@Override
		public void wasUpdated(ActingUserDTO dto) {

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
		public void wasDeleted(ActingUserDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(ActingUserDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
