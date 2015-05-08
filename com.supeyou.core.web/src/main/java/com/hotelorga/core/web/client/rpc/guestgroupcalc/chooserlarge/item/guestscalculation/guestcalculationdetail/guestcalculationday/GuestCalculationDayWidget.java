package com.hotelorga.core.web.client.rpc.guestgroupcalc.chooserlarge.item.guestscalculation.guestcalculationdetail.guestcalculationday;

import java.util.Collection;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.core.iface.dto.AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2RoomDTO;
import com.hotelorga.core.iface.dto.RoomDTO;
import com.hotelorga.core.iface.dto.paying.GuestCalculationDTO;
import com.hotelorga.foundation.iface.common.HELPER;
import com.hotelorga.foundation.iface.datatype.types.AmountType;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.web.client.fields.types.FieldForAmountType;

public abstract class GuestCalculationDayWidget extends WidgetView {

	public GuestCalculationDayWidget(GuestCalculationDTO dto, DateType date) {

		column1.add(new Label(HELPER.date2string(date)));
		column2.add(agregateRooms(dto.getRoomsOfDate(date)));
		column3.add(getCostFromRooms(dto.getRoomsOfDate(date)));
		column4.add(aggregateAcceptances(dto.getAcceptancesOfDate(date)));
	}

	private Widget aggregateAcceptances(Collection<Guest2AcceptanceDTO> acceptancesOfDate) {

		FlowPanel flowPanel = new FlowPanel();

		for (Guest2AcceptanceDTO dto : acceptancesOfDate) {
			flowPanel.add(getAcceptanceLabel(dto.getDtoB()));
		}

		return flowPanel;

	}

	private Widget getAcceptanceLabel(final AcceptanceDTO dto) {

		String label = "";
		if (dto.getTmpName() != null) {
			label = (dto.getTmpName().value());
		} else {
			label = ("no name");
		}

		return new Label(label);

	}

	private Widget getCostFromRooms(Collection<Guest2RoomDTO> guest2RoomDTOs) {

		FlowPanel flowPanel = new FlowPanel();

		for (Guest2RoomDTO guest2roomDTO : guest2RoomDTOs) {
			flowPanel.add(new Label(FieldForAmountType.renderString(new AmountType(new Double(new Double(guest2roomDTO.getMonthlyCosts().value()) / 30).intValue()))));

			// taking the first room:
			break;
		}

		return flowPanel;
	}

	private Widget agregateRooms(Collection<Guest2RoomDTO> guest2roomsDTO) {

		FlowPanel flowPanel = new FlowPanel();

		for (Guest2RoomDTO guest2roomDTO : guest2roomsDTO) {
			flowPanel.add(getRoomLabel(guest2roomDTO));
		}

		return flowPanel;

	}

	private Widget getRoomLabel(final Guest2RoomDTO guest2RoomDTO) {
		final RoomDTO roomDTO = guest2RoomDTO.getDtoB();
		String label = "";
		if (roomDTO.getName() != null) {
			label = (roomDTO.getName().value());
		} else {
			label = ("no name");
		}

		return new Label(label);

	}

	public abstract void clicked();
}
