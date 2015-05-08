package com.supeyou.core.web.client.rpc.guestgroupcalc.chooserlarge.item.guestscalculation;

import java.util.ArrayList;
import java.util.List;

import com.supeyou.core.iface.dto.paying.GuestCalculationDTO;
import com.supeyou.core.iface.dto.paying.GuestsCalculationDTO;
import com.supeyou.core.web.client.rpc.guestgroupcalc.chooserlarge.item.guestscalculation.guestcalculation.GuestCalculationWidget;
import com.supeyou.crudie.web.client.fields.types.FieldForAmountType;

public class GuestsCalculationWidget extends WidgetView {

	private final GuestsCalculationDTO guestsCalculationDTO;

	public GuestsCalculationWidget(GuestsCalculationDTO dto) {

		this.guestsCalculationDTO = dto;
		init();

	}

	private void init() {

		rootPanel.clear();

		String guestCount = guestsCalculationDTO.getGuestCalculationDTOs().size() + "";

		String overallCostsString = FieldForAmountType.renderString(guestsCalculationDTO.getOverallCosts());
		String overallOwnCostsString = FieldForAmountType.renderString(guestsCalculationDTO.getOverallOwnCosts());
		String overallOpenCostsString = FieldForAmountType.renderString(guestsCalculationDTO.getOverallOpenCosts());

		int i = 0;
		List<GuestCalculationDTO> calculationDTOs = guestsCalculationDTO.getGuestCalculationDTOs();
		// moving group leader to first position
		for (GuestCalculationDTO guestCalculationDTO : new ArrayList<GuestCalculationDTO>(calculationDTOs)) {
			if (guestsCalculationDTO.getGroupLeader() != null && guestsCalculationDTO.getGroupLeader().equals(guestCalculationDTO)) {
				calculationDTOs.remove(guestCalculationDTO);
				calculationDTOs.add(0, guestCalculationDTO);
			}
		}

		for (GuestCalculationDTO guestCalculationDTO : calculationDTOs) {
			i++;

			if (i != 1) {

				guestCount = "";

			}

			if (i == guestsCalculationDTO.getGuestCalculationDTOs().size()) {

				rootPanel.add(new GuestCalculationWidget(guestCalculationDTO, guestsCalculationDTO.getGuestDTO2Guest2GuestGroupDTO().get(guestCalculationDTO.getGuestDTO()), guestCount, overallCostsString, overallOwnCostsString, overallOpenCostsString));

			} else {

				rootPanel.add(new GuestCalculationWidget(guestCalculationDTO, guestsCalculationDTO.getGuestDTO2Guest2GuestGroupDTO().get(guestCalculationDTO.getGuestDTO()), guestCount, "", "", ""));

			}

		}

	}

}
