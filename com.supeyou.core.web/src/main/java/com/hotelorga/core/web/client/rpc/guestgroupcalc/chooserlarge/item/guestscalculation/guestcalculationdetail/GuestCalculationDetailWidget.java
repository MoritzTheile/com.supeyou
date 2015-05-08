package com.hotelorga.core.web.client.rpc.guestgroupcalc.chooserlarge.item.guestscalculation.guestcalculationdetail;

import com.google.gwt.user.client.ui.Label;
import com.hotelorga.core.iface.dto.paying.GuestCalculationDTO;
import com.hotelorga.core.iface.dto.paying.GuestCalculationState;
import com.hotelorga.core.iface.dto.paying.GuestCalculationState.STATE;
import com.hotelorga.core.web.client.rpc.guestgroupcalc.chooserlarge.item.guestscalculation.guestcalculationdetail.guestcalculationday.GuestCalculationDayWidget;
import com.hotelorga.core.web.client.rpc.guestgroupcalc.chooserlarge.item.guestscalculation.guestcalculationdetail.guestcalculationdayfooter.GuestCalculationDayFooterWidget;
import com.hotelorga.core.web.client.rpc.guestgroupcalc.chooserlarge.item.guestscalculation.guestcalculationdetail.guestcalculationdayheader.GuestCalculationDayHeaderWidget;
import com.hotelorga.foundation.iface.datatype.types.DateType;

public abstract class GuestCalculationDetailWidget extends WidgetView {

	public GuestCalculationDetailWidget(GuestCalculationDTO dto) {

		rootPanel.add(new Label(com.hotelorga.core.web.client.rpc.guest.chooserlarge.item.ItemWidget.renderHeaderString(dto.getGuestDTO())));

		rootPanel.add(new GuestCalculationDayHeaderWidget());

		for (DateType date : dto.getDates()) {
			rootPanel.add(new GuestCalculationDayWidget(dto, date) {

				@Override
				public void clicked() {
					close();
				}
			});
		}

		rootPanel.add(new GuestCalculationDayFooterWidget(dto));

		for (GuestCalculationState message : dto.getStates()) {
			Label label = new Label(message.getMessage());
			if (STATE.ERROR.equals(message.getState())) {
				label.addStyleName("errortext");
			}
			rootPanel.add(label);
		}
	}

	public abstract void close();

}
