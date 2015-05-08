package com.hotelorga.core.iface.common;

import com.hotelorga.core.iface.dto.paying.GuestCalculationDTO;
import com.hotelorga.foundation.iface.datatype.types.DateType;

public class HOTELHELPER {

	public static int getDaysInHotel(GuestCalculationDTO dto) {

		int dayCount = 0;
		for (DateType dateType : dto.getDates()) {
			if (dto.getRoomsOfDate(dateType) != null && !dto.getRoomsOfDate(dateType).isEmpty()) {
				dayCount++;
			}
		}
		return dayCount;
	}
}
