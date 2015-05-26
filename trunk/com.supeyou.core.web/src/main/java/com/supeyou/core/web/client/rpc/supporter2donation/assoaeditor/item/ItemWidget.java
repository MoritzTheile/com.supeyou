package com.supeyou.core.web.client.rpc.supporter2donation.assoaeditor.item;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.supeyou.core.iface.dto.Supporter2DonationDTO;
import com.supeyou.core.web.client.rpc.supporter2donation.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private Supporter2DonationDTO thisDTO;

	public ItemWidget(Supporter2DonationDTO dto) {

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

	private void render(Supporter2DonationDTO dto) {

		columnOne.clear();
		columnOne.add(new HTML(renderInfoHTML(thisDTO)));

	}

	protected String renderInfoHTML(Supporter2DonationDTO dto) {

		String infos = "";

		if (dto.getDtoA() != null) {
			infos += dto.getDtoA().getComment();
		}

		return infos;

	}

	private CRUDProxyListener<Supporter2DonationDTO> listener = new CRUDProxyListener<Supporter2DonationDTO>() {

		@Override
		public void wasUpdated(final Supporter2DonationDTO dto) {

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
		public void wasDeleted(Supporter2DonationDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(Supporter2DonationDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
