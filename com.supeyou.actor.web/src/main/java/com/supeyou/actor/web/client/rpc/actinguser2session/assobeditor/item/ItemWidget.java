package com.supeyou.actor.web.client.rpc.actinguser2session.assobeditor.item;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.supeyou.actor.iface.dto.ActingUser2SessionDTO;
import com.supeyou.actor.web.client.rpc.actinguser2session.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private ActingUser2SessionDTO thisDTO;

	public ItemWidget(ActingUser2SessionDTO dto) {

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

	private void render(ActingUser2SessionDTO dto) {

		columnOne.clear();
		columnOne.add(new HTML(renderInfoHTML(thisDTO)));

	}

	protected String renderInfoHTML(ActingUser2SessionDTO dto) {

		String infos = "";

		if (dto.getDtoB() != null) {
			infos += dto.getDtoB().getHttpSessionId();
		}

		return infos;

	}

	private CRUDProxyListener<ActingUser2SessionDTO> listener = new CRUDProxyListener<ActingUser2SessionDTO>() {

		@Override
		public void wasUpdated(final ActingUser2SessionDTO dto) {

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
		public void wasDeleted(ActingUser2SessionDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(ActingUser2SessionDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
