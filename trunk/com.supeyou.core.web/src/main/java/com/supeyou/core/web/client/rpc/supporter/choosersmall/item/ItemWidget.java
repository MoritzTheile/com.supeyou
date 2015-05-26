package com.supeyou.core.web.client.rpc.supporter.choosersmall.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.rpc.supporter.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private SupporterDTO thisDTO;

	public ItemWidget(SupporterDTO dto) {

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

	private CRUDProxyListener<SupporterDTO> listener = new CRUDProxyListener<SupporterDTO>() {

		@Override
		public void wasUpdated(SupporterDTO dto) {

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
		public void wasDeleted(SupporterDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(SupporterDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
