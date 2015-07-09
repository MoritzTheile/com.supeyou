package com.supeyou.core.web.client.rpc.user2supporter.assobeditor.item;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.supeyou.core.iface.dto.User2SupporterDTO;
import com.supeyou.core.web.client.rpc.user2supporter.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private User2SupporterDTO thisDTO;

	public ItemWidget(User2SupporterDTO dto) {

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

	private void render(User2SupporterDTO dto) {

		columnOne.clear();
		columnOne.add(new HTML(renderInfoHTML(thisDTO)));

	}

	protected String renderInfoHTML(User2SupporterDTO dto) {

		String infos = "";

		if (dto.getDtoB() != null) {
			infos += dto.getDtoB().getTmpHeroName();
		}

		return infos;

	}

	private CRUDProxyListener<User2SupporterDTO> listener = new CRUDProxyListener<User2SupporterDTO>() {

		@Override
		public void wasUpdated(final User2SupporterDTO dto) {

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
		public void wasDeleted(User2SupporterDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(User2SupporterDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
