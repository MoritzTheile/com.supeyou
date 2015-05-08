package com.supeyou.core.web.client.rpc.guest.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.core.iface.datatype.enums.TITLE;
import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.web.client.rpc.guest.RPCCRUDProxy;
import com.supeyou.crudie.iface.common.HELPER;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private GuestDTO thisDTO;

	public ItemWidget(GuestDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(renderHeaderString(thisDTO)));
		columnTwo.clear();
		columnTwo.add(new Label(renderInfoString(thisDTO)));

		columnFour.clear();
		if (thisDTO.getComment() != null) {
			columnFour.add(new Label(thisDTO.getComment().value()));
		}

	}

	public static String renderHeaderString(GuestDTO dto) {

		String infos = "";

		if (TITLE.MR.equals(dto.getTitle())) {
			infos += "Herr ";
		}
		if (TITLE.MRS.equals(dto.getTitle())) {
			infos += "Frau ";
		}

		if (dto.getFirstname() != null) {
			infos += dto.getFirstname().value();
		}
		if (dto.getLastname() != null) {
			infos += " " + dto.getLastname().value();
		}

		return infos;

	}

	public static String renderInfoString(GuestDTO dto) {

		String infos = "";

		if (dto.getDateOfBirth() != null) {
			infos += "geb. " + HELPER.date2string(dto.getDateOfBirth());
		}

		return infos;

	}

	private CRUDProxyListener<GuestDTO> listener = new CRUDProxyListener<GuestDTO>() {

		@Override
		public void wasUpdated(GuestDTO dto) {

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
		public void wasDeleted(GuestDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(GuestDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}
