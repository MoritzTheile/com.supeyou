package com.hotelorga.core.web.client.rpc.acceptance;

import com.hotelorga.core.iface.dto.AcceptanceDTO;
import com.hotelorga.foundation.iface.common.HELPER;

public class Statics {

	public static String renderDescription(AcceptanceDTO dto) {

		if (dto == null) {
			return "- - -";
		}

		String description = "";
		if (dto.getComment() != null) {
			description += dto.getComment().value() + " ";
		}
		description += HELPER.date2string(dto.getFromDate());
		description += " - ";
		description += HELPER.date2string(dto.getToDate());
		description += " (" + dto.getTmpName() + ")";

		return description;
	}
}
