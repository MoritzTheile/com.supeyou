package com.supeyou.core.web.client.rpc.acceptance2payer.choosersmall.item;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.core.iface.dto.Acceptance2PayerDTO;
import com.supeyou.core.web.client.rpc.acceptance.Statics;
import com.supeyou.core.web.client.rpc.acceptance2payer.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private Acceptance2PayerDTO thisDTO;

	public ItemWidget(Acceptance2PayerDTO dto) {

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

	private void render(Acceptance2PayerDTO dto) {

		columnOne.clear();
		columnOne.add(new Label(renderInfoString(thisDTO)));

	}

	protected String renderInfoString(Acceptance2PayerDTO dto) {
		String infos = "";
		infos += Statics.renderDescription(thisDTO.getDtoA()) + " <-> " + dto.getDtoB().getName();

		return infos;
	}

	private CRUDProxyListener<Acceptance2PayerDTO> listener = new CRUDProxyListener<Acceptance2PayerDTO>() {

		@Override
		public void wasUpdated(final Acceptance2PayerDTO dto) {

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
		public void wasDeleted(Acceptance2PayerDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(Acceptance2PayerDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
