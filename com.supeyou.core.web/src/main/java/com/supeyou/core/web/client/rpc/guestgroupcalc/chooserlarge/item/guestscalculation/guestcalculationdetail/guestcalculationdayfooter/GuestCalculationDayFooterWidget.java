package com.supeyou.core.web.client.rpc.guestgroupcalc.chooserlarge.item.guestscalculation.guestcalculationdetail.guestcalculationdayfooter;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.RoomDTO;
import com.supeyou.core.iface.dto.paying.GuestCalculationDTO;
import com.supeyou.crudie.web.client.fields.types.FieldForAmountType;

public class GuestCalculationDayFooterWidget extends WidgetView {

	public GuestCalculationDayFooterWidget(GuestCalculationDTO dto) {

		column1.add(new Label(dto.getDates().size() + ""));
		column2.add(agregateRooms(dto));
		column3.add(sumCosts(dto));
		column4.add(agregateAcceptances(dto));
	}

	public static Widget agregateAcceptances(GuestCalculationDTO dto) {
		FlowPanel widget = new FlowPanel();
		for (AcceptanceDTO acceptanceDTO : dto.getAcceptances()) {
			if (acceptanceDTO.getTmpName() != null) {
				widget.add(new Label(acceptanceDTO.getTmpName().value() + " "));
			} else {
				widget.add(new Label("no name found"));
			}
		}
		return widget;
	}

	public static Widget sumCosts(GuestCalculationDTO dto) {
		FlowPanel widget = new FlowPanel();

		widget.add(new Label(FieldForAmountType.renderString(dto.getCost())));

		return widget;
	}

	public static Widget agregateRooms(GuestCalculationDTO dto) {
		FlowPanel widget = new FlowPanel();
		for (RoomDTO roomDTO : dto.getRooms()) {
			widget.add(new Label(roomDTO.getName() + "(" + dto.getDatesInRoom(roomDTO).size() + ")"));
		}
		return widget;
	}

}
