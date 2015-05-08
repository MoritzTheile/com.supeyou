package com.supeyou.core.web.client.rpc.guestgroupcalc.createguestgroupforall.createmultiguest2roomform;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.Guest2RoomDTO;
import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.web.client.rpc.guest.chooserlarge.item.ItemWidget;
import com.supeyou.core.web.client.rpc.guest2room.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.guestgroupcalc.createguestgroupforall.createmultiguest2roomform.fields.FieldsWidget;
import com.supeyou.crudie.iface.datatype.types.DateType;

public class CreateMultiGuest2RoomFormWidget extends WidgetView {

	private final List<GuestDTO> guestDTOs;

	private final FieldsWidget fieldsWidget;

	public CreateMultiGuest2RoomFormWidget(List<GuestDTO> guestDTOs, DateType from, DateType to) {

		this.guestDTOs = guestDTOs;

		fieldsWidget = new FieldsWidget(from, to);

		formSlot.add(fieldsWidget);

		saveButton.setText(guestDTOs.size() + " Gäste in Zimmer einbuchen");

		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				cancel();

			}
		});

		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				save();

			}

		});

		this.addDomHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {

				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {

					save();

				}

				if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {

					cancel();

				}

			}

		}, KeyDownEvent.getType());

	}

	private void save() {
		if (fieldsWidget.getRoomDTO() == null) {
			Window.alert("Bitte wählen Sie zunächst ein Zimmer");
			return;
		}

		List<GuestDTO> guestList = new ArrayList<>(guestDTOs);

		createGuest2RoomFromGuestListUntilEmpty(guestList);
		close();
	}

	private void createGuest2RoomFromGuestListUntilEmpty(final List<GuestDTO> guestList) {
		if (guestList.isEmpty()) {
			return;
		}
		GuestDTO guestDTO = guestList.get(0);
		guestList.remove(0);
		{

			final Guest2RoomDTO dto = new Guest2RoomDTO();
			dto.setDtoA(guestDTO);
			dto.setDtoB(fieldsWidget.getRoomDTO());
			dto.setFromDate(fieldsWidget.getFrom());
			dto.setToDate(fieldsWidget.getTo());

			RPCCRUDProxy.i().updadd(dto, new AsyncCallback<Guest2RoomDTO>() {

				@Override
				public void onSuccess(Guest2RoomDTO result) {
					createGuest2RoomFromGuestListUntilEmpty(guestList);
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Der Gast " + ItemWidget.renderHeaderString(dto.getDtoA()) + " konnte nicht ins Zimmer " + dto.getDtoB().getComment() + " eingebucht werden");

				}
			});
		}
	}

	private void cancel() {

		close();
	}

	protected void close() {
		// can be overwritten
	}

}
