package com.hotelorga.foundation.web.client.rpc.user2group.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.hotelorga.foundation.iface.dto.User2GroupDTO;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.hotelorga.foundation.web.client.rpc.user2group.RPCCRUDProxy;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private User2GroupDTO thisDTO;

	public ItemWidget(User2GroupDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(thisDTO.getDtoA().getEmailAddress() + " <-> " + thisDTO.getDtoB().getName()));

	}

	private CRUDProxyListener<User2GroupDTO> listener = new CRUDProxyListener<User2GroupDTO>() {

		@Override
		public void wasUpdated(User2GroupDTO dto) {

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
		public void wasDeleted(User2GroupDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(User2GroupDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
