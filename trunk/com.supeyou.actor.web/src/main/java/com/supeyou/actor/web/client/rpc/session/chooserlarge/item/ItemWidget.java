package com.supeyou.actor.web.client.rpc.session.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.web.client.rpc.session.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private SessionDTO thisDTO;

	public ItemWidget(SessionDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(renderInfoString()));

		columnThree.clear();
		// columnThree.add(new com.supeyou.crudie.web.client.rpc.user2group.assoaeditor.AssoAEditorWidget(thisDTO, false));

	}

	private String renderInfoString() {
		String infos = "";
		if (thisDTO.getHttpSessionId() != null) {
			infos += thisDTO.getHttpSessionId().value();
		}

		return infos;
	}

	private CRUDProxyListener<SessionDTO> listener = new CRUDProxyListener<SessionDTO>() {

		@Override
		public void wasUpdated(SessionDTO dto) {

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
		public void wasDeleted(SessionDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(SessionDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
