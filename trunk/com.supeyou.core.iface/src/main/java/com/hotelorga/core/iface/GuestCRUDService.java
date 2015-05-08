package com.hotelorga.core.iface;

import java.util.List;

import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.iface.dto.GuestFetchQuery;
import com.hotelorga.core.iface.dto.GuestGroupDTO;
import com.hotelorga.core.iface.dto.paying.GuestCalculationDTO;
import com.hotelorga.core.iface.dto.paying.GuestsCalculationDTO;
import com.hotelorga.foundation.iface.CRUDService;
import com.hotelorga.foundation.iface.datatype.CRUDException;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.iface.datatype.types.FileIDType;
import com.hotelorga.foundation.iface.dto.UserDTO;

public interface GuestCRUDService extends CRUDService<GuestDTO, GuestFetchQuery> {

	GuestCalculationDTO getGuestCalculationDTO(UserDTO actor, GuestDTO guestDTO, DateType from, DateType to) throws CRUDException;

	GuestsCalculationDTO getGuestsCalculationDTO(UserDTO actor, GuestGroupDTO guestGroupDTO, DateType from, DateType to) throws CRUDException;

	FileIDType renderBill(
			UserDTO actorDTO,
			GuestsCalculationDTO guestsCalculationDTO
			) throws CRUDException;

	FileIDType renderExcelOverview(
			UserDTO actorDTO,
			List<GuestsCalculationDTO> guestsCalculationDTOList
			) throws CRUDException;

}