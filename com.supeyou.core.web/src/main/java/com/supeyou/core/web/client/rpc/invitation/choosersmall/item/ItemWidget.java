package com.supeyou.core.web.client.rpc.invitation.choosersmall.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private InvitationDTO thisDTO;

	public ItemWidget(InvitationDTO dto) {

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
		if (thisDTO.getComment() != null) {
			infos += thisDTO.getComment().value();
		}

		return infos;
	}

	private CRUDProxyListener<InvitationDTO> listener = new CRUDProxyListener<InvitationDTO>() {

		@Override
		public void wasUpdated(InvitationDTO dto) {

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
		public void wasDeleted(InvitationDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(InvitationDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
