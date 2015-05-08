package com.hotelorga.foundation.web.client.rpc.user.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.hotelorga.foundation.iface.common.HELPER;
import com.hotelorga.foundation.iface.dto.UserDTO;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.hotelorga.foundation.web.client.rpc.user.RPCCRUDProxy;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private UserDTO thisDTO;

	public ItemWidget(UserDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(renderInfoString()));

		columnTwo.clear();
		columnTwo.add(new Label(thisDTO.getRoles().toString()));

	}

	private String renderInfoString() {
		String infos = "";
		if (thisDTO.getLoginId() != null) {
			infos += thisDTO.getLoginId().value() + "\n";
		}
		if (thisDTO.getEmailAddress() != null) {
			infos += thisDTO.getEmailAddress().value();
		}
		if (thisDTO.getBirthday() != null) {
			infos += ", " + HELPER.date2string(thisDTO.getBirthday());
		}
		if (thisDTO.getAmount() != null) {
			infos += ", " + HELPER.cent2euro(thisDTO.getAmount().value());
		}
		if (thisDTO.getActive() != null) {
			if (thisDTO.getActive()) {
				infos += ", aktiv";
			} else {
				infos += ", inaktiv";
			}
		}
		return infos;
	}

	private CRUDProxyListener<UserDTO> listener = new CRUDProxyListener<UserDTO>() {

		@Override
		public void wasUpdated(UserDTO dto) {

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
		public void wasDeleted(UserDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(UserDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
