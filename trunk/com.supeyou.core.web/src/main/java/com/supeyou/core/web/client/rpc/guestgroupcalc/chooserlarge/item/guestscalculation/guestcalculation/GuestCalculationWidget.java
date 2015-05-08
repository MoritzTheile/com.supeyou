package com.supeyou.core.web.client.rpc.guestgroupcalc.chooserlarge.item.guestscalculation.guestcalculation;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.common.HOTELHELPER;
import com.supeyou.core.iface.datatype.enums.GROUPROLE;
import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.Guest2GuestGroupDTO;
import com.supeyou.core.iface.dto.Guest2RoomDTO;
import com.supeyou.core.iface.dto.RoomDTO;
import com.supeyou.core.iface.dto.paying.GuestCalculationDTO;
import com.supeyou.core.iface.dto.paying.GuestCalculationState;
import com.supeyou.core.iface.dto.paying.GuestCalculationState.STATE;
import com.supeyou.core.web.client.fields.types.FieldForGroupRole;
import com.supeyou.core.web.client.rpc.acceptance.form.FormForFieldsWidget;
import com.supeyou.core.web.client.rpc.guest.form.FormWidget;
import com.supeyou.core.web.client.rpc.guest2guestgroup.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.guestgroupcalc.chooserlarge.item.guestscalculation.guestcalculationdetail.GuestCalculationDetailWidget;
import com.supeyou.crudie.iface.common.HELPER;
import com.supeyou.crudie.web.client.fields.types.FieldForAmountType;
import com.supeyou.crudie.web.client.fields.types.FieldForBoolean;
import com.supeyou.crudie.web.client.uiorga.linkbutton.LinkButtonWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class GuestCalculationWidget extends WidgetView {

	public GuestCalculationWidget(final GuestCalculationDTO dto, final Guest2GuestGroupDTO guest2GuestGroupDTO, String guestCount, String costs, String ownCosts, String toPay) {

		if (hasState(STATE.ERROR, dto)) {
			rootPanel.addStyleName("error");
		}

		GROUPROLE grouprole = null;

		if (guest2GuestGroupDTO != null) {
			grouprole = guest2GuestGroupDTO.getGroupRole();
		}

		columnA.add(new FieldForBoolean(guest2GuestGroupDTO.getGroupLeader()) {

			@Override
			public void onHasChanged(Boolean value) {

				guest2GuestGroupDTO.setGroupLeader(value);
				RPCCRUDProxy.i().updadd(guest2GuestGroupDTO);

			}

		});

		columnB.add(new FieldForGroupRole(grouprole) {

			@Override
			public void onHasChanged(GROUPROLE value) {

				guest2GuestGroupDTO.setGroupRole(value);

				RPCCRUDProxy.i().updadd(guest2GuestGroupDTO);

			}

		});
		columnC.add(getGuestLink(dto));
		columnD.add(new Label(HELPER.date2string(dto.getGuestDTO().getDateOfBirth())));
		for (Widget widget : getAcceptanceLinks(dto)) {
			columnE.add(widget);
		}
		for (Guest2RoomDTO guest2RoomDTO : dto.getGuest2RoomDTOs()) {
			columnF.add(getRoomLink(guest2RoomDTO));
		}
		columnG.add(new Label(guestCount));
		columnH.add(new Label(HELPER.date2string(dto.getFrom())));
		columnI.add(new Label(HELPER.date2string(dto.getTo())));
		columnJ.add(new Label(HOTELHELPER.getDaysInHotel(dto) + ""));
		columnK.add(new Label(FieldForAmountType.renderString(dto.getCost())));
		columnL.add(new Label(costs));
		columnM.add(new Label(ownCosts));
		columnN.add(new Label(toPay));

		rootPanel.addDomHandler(new ClickHandler() {

			PopupWidget popupWidget;

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();
				popupWidget = new PopupWidget(new GuestCalculationDetailWidget(dto) {
					@Override
					public void close() {
						popupWidget.closePopup();

					}
				}, 100, 700, true);

			}

		}, ClickEvent.getType());
	}

	private static Widget getGuestLink(final GuestCalculationDTO dto) {

		LinkButtonWidget linkButtonWidget = new LinkButtonWidget(com.supeyou.core.web.client.rpc.guest.chooserlarge.item.ItemWidget.renderHeaderString(dto.getGuestDTO()));
		linkButtonWidget.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				event.stopPropagation();

				final PopupWidget formPopup = new PopupWidget(false);
				formPopup.setPosition(100, 700);
				formPopup.init(
						new FormWidget(null, dto.getGuestDTO()) {
							@Override
							protected void close() {
								formPopup.closePopup();
							}
						}
						);

			}
		});

		return linkButtonWidget;

	}

	private static List<Widget> getAcceptanceLinks(final GuestCalculationDTO dto) {

		List<Widget> result = new ArrayList<>();

		for (final AcceptanceDTO acceptanceDTO : dto.getAcceptances()) {
			LinkButtonWidget linkButtonWidget = new LinkButtonWidget(getLabel(acceptanceDTO));
			linkButtonWidget.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					event.stopPropagation();

					final PopupWidget formPopup = new PopupWidget(false);
					formPopup.setPosition(100, 700);
					formPopup.init(
							new FormForFieldsWidget(null, acceptanceDTO) {
								@Override
								protected void close() {
									formPopup.closePopup();
								}
							}
							);

				}
			});
			result.add(linkButtonWidget);
		}

		return result;

	}

	private static String getLabel(final AcceptanceDTO acceptanceDTO) {
		if (acceptanceDTO.getTmpName() != null) {
			return acceptanceDTO.getTmpName().value();
		}
		return "kein Kostenträger";
	}

	private Widget getRoomLink(final Guest2RoomDTO guest2RoomDTO) {

		final RoomDTO roomDTO = guest2RoomDTO.getDtoB();
		String label = "";
		if (roomDTO.getName() != null) {
			label = (roomDTO.getName().value());
		} else {
			label = ("no name");
		}

		LinkButtonWidget linkButtonWidget = new LinkButtonWidget(label);
		linkButtonWidget.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				event.stopPropagation();

				final PopupWidget formPopup = new PopupWidget(false);
				formPopup.setPosition(100, 700);
				formPopup.init(
						new com.supeyou.core.web.client.rpc.guest2room.form.FormWidget(null, guest2RoomDTO, false, false) {
							@Override
							protected void close() {
								formPopup.closePopup();
							}
						}
						);

			}
		});

		return linkButtonWidget;

	}

	private boolean hasState(STATE error, GuestCalculationDTO dto) {
		for (GuestCalculationState calcState : dto.getStates()) {
			if (STATE.ERROR.equals(calcState.getState())) {
				return true;
			}
		}
		return false;
	}

}
