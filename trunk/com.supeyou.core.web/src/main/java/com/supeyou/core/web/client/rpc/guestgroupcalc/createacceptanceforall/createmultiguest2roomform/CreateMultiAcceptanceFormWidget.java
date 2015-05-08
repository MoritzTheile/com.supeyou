package com.supeyou.core.web.client.rpc.guestgroupcalc.createacceptanceforall.createmultiguest2roomform;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.Acceptance2PayerDTO;
import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.Guest2AcceptanceDTO;
import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.web.client.rpc.guest.chooserlarge.item.ItemWidget;
import com.supeyou.core.web.client.rpc.guestgroupcalc.createacceptanceforall.createmultiguest2roomform.fields.FieldsWidget;
import com.supeyou.crudie.iface.datatype.types.DateType;

public class CreateMultiAcceptanceFormWidget extends WidgetView {

	private final List<GuestDTO> guestDTOs;

	private final FieldsWidget fieldsWidget;

	public CreateMultiAcceptanceFormWidget(List<GuestDTO> guestDTOs, DateType from, DateType to) {

		this.guestDTOs = guestDTOs;

		fieldsWidget = new FieldsWidget(from, to);

		formSlot.add(fieldsWidget);

		saveButton.setText("Kostenübernahme für " + guestDTOs.size() + " Gäste erstellen");

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

		AcceptanceDTO dto = new AcceptanceDTO();

		dto.setComment(fieldsWidget.getComment());
		dto.setFromDate(fieldsWidget.getFrom());
		dto.setToDate(fieldsWidget.getTo());
		dto.setAcceptedCosts(fieldsWidget.getAcceptedCosts());
		dto.setAcceptedDays(fieldsWidget.getAcceptedDays());
		dto.setFixOwnCosts(fieldsWidget.getFixedOwnCosts());

		com.supeyou.core.web.client.rpc.acceptance.RPCCRUDProxy.i().updadd(dto, new AsyncCallback<AcceptanceDTO>() {

			@Override
			public void onFailure(Throwable caught) {

				caught.printStackTrace();

			}

			@Override
			public void onSuccess(AcceptanceDTO acceptanceDTO) {

				if (fieldsWidget.getPayer() != null) {
					Acceptance2PayerDTO acceptance2payer = new Acceptance2PayerDTO();
					acceptance2payer.setDtoA(acceptanceDTO);
					acceptance2payer.setDtoB(fieldsWidget.getPayer());
					com.supeyou.core.web.client.rpc.acceptance2payer.RPCCRUDProxy.i().updadd(acceptance2payer);
				} else {
					Window.alert("Bitte wählen Sie einen Kostenträger");
					return;
				}

				createAcceptanceAssosPoppingFromListUntilEmpty(acceptanceDTO, new ArrayList<>(guestDTOs));
			}

			private void createAcceptanceAssosPoppingFromListUntilEmpty(final AcceptanceDTO acceptanceDTO, final List<GuestDTO> guestDTOList) {

				if (guestDTOList.isEmpty()) {
					return;
				}

				GuestDTO guestDTO = guestDTOList.get(0);

				guestDTOList.remove(0);

				{

					final Guest2AcceptanceDTO dto = new Guest2AcceptanceDTO();
					dto.setDtoA(guestDTO);
					dto.setDtoB(acceptanceDTO);

					com.supeyou.core.web.client.rpc.guest2acceptance.RPCCRUDProxy.i().updadd(dto, new AsyncCallback<Guest2AcceptanceDTO>() {

						@Override
						public void onSuccess(Guest2AcceptanceDTO result) {
							createAcceptanceAssosPoppingFromListUntilEmpty(acceptanceDTO, guestDTOList);
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Der Gast " + ItemWidget.renderHeaderString(dto.getDtoA()) + " konnte nicht zur Kostenübernahme " + dto.getDtoB().getComment() + " hinzugefügt werden");

						}
					});
				}
			}
		});

		close();
	}

	private void cancel() {

		close();
	}

	protected void close() {
		// can be overwritten
	}

}
