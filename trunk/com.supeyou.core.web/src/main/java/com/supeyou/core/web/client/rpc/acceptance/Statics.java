package com.supeyou.core.web.client.rpc.acceptance;

import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.crudie.iface.common.HELPER;

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
