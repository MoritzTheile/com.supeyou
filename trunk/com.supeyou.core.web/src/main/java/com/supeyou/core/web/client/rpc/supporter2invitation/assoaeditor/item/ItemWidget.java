package com.supeyou.core.web.client.rpc.supporter2invitation.assoaeditor.item;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.supeyou.core.iface.dto.Supporter2InvitationDTO;
import com.supeyou.core.web.client.rpc.supporter2invitation.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private Supporter2InvitationDTO thisDTO;

	public ItemWidget(Supporter2InvitationDTO dto) {

		this.thisDTO = dto;

		// remove onDetach
		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		deleteLink.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				event.stopPropagation();

				RPCCRUDProxy.i().delete(thisDTO);

			}
		}, ClickEvent.getType());

		render(thisDTO);

	}

	private void render(Supporter2InvitationDTO dto) {

		columnOne.clear();
		columnOne.add(new HTML(renderInfoHTML(thisDTO)));

	}

	protected String renderInfoHTML(Supporter2InvitationDTO dto) {

		String infos = "";

		if (dto.getDtoA() != null) {
			infos += dto.getDtoA().getTmpHeroName();
		}

		return infos;

	}

	private CRUDProxyListener<Supporter2InvitationDTO> listener = new CRUDProxyListener<Supporter2InvitationDTO>() {

		@Override
		public void wasUpdated(final Supporter2InvitationDTO dto) {

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
