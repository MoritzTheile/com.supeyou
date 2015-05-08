package com.supeyou.core.iface.common;

import com.supeyou.core.iface.dto.paying.GuestCalculationDTO;
import com.supeyou.crudie.iface.datatype.types.DateType;

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
