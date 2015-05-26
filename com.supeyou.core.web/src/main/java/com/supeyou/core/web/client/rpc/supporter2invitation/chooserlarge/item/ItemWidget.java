package com.supeyou.core.web.client.rpc.supporter2invitation.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.core.iface.dto.Supporter2InvitationDTO;
import com.supeyou.core.web.client.rpc.supporter2invitation.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private Supporter2InvitationDTO thisDTO;

	public ItemWidget(Supporter2InvitationDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(thisDTO.getDtoA().getComment() + " <-> " + thisDTO.getDtoB().getComment()));

	}

	private CRUDProxyListener<Supporter2InvitationDTO> listener = new CRUDProxyListener<Supporter2InvitationDTO>() {

		@Override
		public void wasUpdated(Supporter2InvitationDTO dto) {

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
		public void wasDeleted(Supporter2InvitationDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(Supporter2InvitationDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
