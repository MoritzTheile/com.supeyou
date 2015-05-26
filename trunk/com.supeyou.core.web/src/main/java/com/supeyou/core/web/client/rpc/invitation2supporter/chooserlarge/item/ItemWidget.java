package com.supeyou.core.web.client.rpc.invitation2supporter.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.web.client.rpc.invitation2supporter.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private Invitation2SupporterDTO thisDTO;

	public ItemWidget(Invitation2SupporterDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(thisDTO.getDtoA().getComment() + " <-> " + thisDTO.getDtoB().getComment()));

	}

	private CRUDProxyListener<Invitation2SupporterDTO> listener = new CRUDProxyListener<Invitation2SupporterDTO>() {

		@Override
		public void wasUpdated(Invitation2SupporterDTO dto) {

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
		public void wasDeleted(Invitation2SupporterDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(Invitation2SupporterDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
