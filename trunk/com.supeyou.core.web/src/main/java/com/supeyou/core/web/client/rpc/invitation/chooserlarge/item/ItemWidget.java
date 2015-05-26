package com.supeyou.core.web.client.rpc.invitation.chooserlarge.item;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
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

		websiteSlot.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				event.stopPropagation();

			}
		}, ClickEvent.getType());

	}

	private void render() {

		commentSlot.clear();
		if (thisDTO.getComment() != null) {
			commentSlot.add(new HTML(thisDTO.getComment().value()));
		}

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

	@Override
	protected void onLoad() {
		super.onLoad();
		render();
	}

}
