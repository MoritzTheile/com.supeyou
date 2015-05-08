package com.supeyou.crudie.web.client.rpc.user2group.assoaeditor.item;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.supeyou.crudie.iface.dto.User2GroupDTO;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.user2group.RPCCRUDProxy;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private User2GroupDTO thisDTO;

	public ItemWidget(User2GroupDTO dto) {

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

	private void render(User2GroupDTO dto) {

		columnOne.clear();
		columnOne.add(new HTML(renderInfoHTML(thisDTO)));

	}

	protected String renderInfoHTML(User2GroupDTO dto) {

		String infos = "";

		if (dto.getDtoA() != null) {
			infos += dto.getDtoA().getLoginId();
		}

		return infos;

	}

	private CRUDProxyListener<User2GroupDTO> listener = new CRUDProxyListener<User2GroupDTO>() {

		@Override
		public void wasUpdated(final User2GroupDTO dto) {

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
		public void wasDeleted(User2GroupDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(User2GroupDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
