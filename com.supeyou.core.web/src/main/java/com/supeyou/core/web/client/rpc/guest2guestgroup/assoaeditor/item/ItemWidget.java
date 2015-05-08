package com.supeyou.core.web.client.rpc.guest2guestgroup.assoaeditor.item;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.supeyou.core.iface.datatype.enums.GROUPROLE;
import com.supeyou.core.iface.dto.Guest2GuestGroupDTO;
import com.supeyou.core.web.client.rpc.guest2guestgroup.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private Guest2GuestGroupDTO thisDTO;

	public ItemWidget(Guest2GuestGroupDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		deleteLink.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				RPCCRUDProxy.i().delete(thisDTO);

			}
		}, ClickEvent.getType());

		render(thisDTO);

	}

	private void render(Guest2GuestGroupDTO dto) {

		columnOne.clear();
		columnOne.add(new HTML(renderInfoHTML(thisDTO)));

	}

	protected String renderInfoHTML(Guest2GuestGroupDTO dto) {
		String infos = "";
		infos += com.supeyou.core.web.client.rpc.guest.chooserlarge.item.ItemWidget.renderHeaderString(thisDTO.getDtoA());
		infos += ", " + com.supeyou.core.web.client.rpc.guest.chooserlarge.item.ItemWidget.renderInfoString(thisDTO.getDtoA());
		if (dto.getGroupRole() != null && !GROUPROLE.NO_ROLE.equals(dto.getGroupRole())) {
			infos += ", (" + dto.getGroupRole() + ")";
		}
		return infos;
	}

	private CRUDProxyListener<Guest2GuestGroupDTO> listener = new CRUDProxyListener<Guest2GuestGroupDTO>() {

		@Override
		public void wasUpdated(final Guest2GuestGroupDTO dto) {

			final String updatedStyle = "updated";

			thisWidget.removeStyleName(updatedStyle);

			if (thisDTO.equals(dto)) {

				// without the delay the change in style doesn't get picked up
				new Timer() {

					@Override
					public void run() {

						thisWidget.addStyleName(updatedStyle);

					}
				}.schedule(100);

				render(dto);
			}
		}

		@Override
		public void wasDeleted(Guest2GuestGroupDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(Guest2GuestGroupDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
