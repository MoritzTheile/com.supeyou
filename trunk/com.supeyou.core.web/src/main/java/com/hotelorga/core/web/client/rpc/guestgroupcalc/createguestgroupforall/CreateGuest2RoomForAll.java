package com.hotelorga.core.web.client.rpc.guestgroupcalc.createguestgroupforall;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hotelorga.core.iface.dto.Guest2GuestGroupDTO;
import com.hotelorga.core.iface.dto.Guest2GuestGroupFetchQuery;
import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.iface.dto.GuestGroupDTO;
import com.hotelorga.core.web.client.rpc.guest2guestgroup.RPCCRUDServiceAsync;
import com.hotelorga.core.web.client.rpc.guestgroupcalc.createguestgroupforall.createmultiguest2roomform.CreateMultiGuest2RoomFormWidget;
import com.hotelorga.foundation.iface.datatype.Page;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.iface.dto.DTOFetchList;
import com.hotelorga.foundation.web.client.uiorga.linkbutton.LinkButtonWidget;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class CreateGuest2RoomForAll extends LinkButtonWidget {

	public CreateGuest2RoomForAll(final GuestGroupDTO dto, final DateType from, final DateType to) {

		setText("Zimmerbelegung für alle erstellen");

		this.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				Guest2GuestGroupFetchQuery query = new Guest2GuestGroupFetchQuery();
				query.setIdB(dto.getId());
				RPCCRUDServiceAsync.i.fetchList(new Page(), query, new AsyncCallback<DTOFetchList<Guest2GuestGroupDTO>>() {

					@Override
					public void onFailure(Throwable caught) {

						caught.printStackTrace();

					}

					@Override
					public void onSuccess(DTOFetchList<Guest2GuestGroupDTO> result) {

						List<GuestDTO> guestDTOs = new ArrayList<>();
						for (Guest2GuestGroupDTO guest2GuestGroupDTO : result) {
							guestDTOs.add(guest2GuestGroupDTO.getDtoA());
						}

						final PopupWidget formPopup = new PopupWidget(false);
						formPopup.setPosition(100, 700);
						formPopup.init(
								new CreateMultiGuest2RoomFormWidget(guestDTOs, from, to) {
									protected void close() {
										formPopup.closePopup();
									};
								}
								);

					}
				});

			}
		});
	}
}
