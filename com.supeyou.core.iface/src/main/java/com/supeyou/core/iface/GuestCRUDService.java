package com.supeyou.core.iface;

import java.util.List;

import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.iface.dto.GuestFetchQuery;
import com.supeyou.core.iface.dto.GuestGroupDTO;
import com.supeyou.core.iface.dto.paying.GuestCalculationDTO;
import com.supeyou.core.iface.dto.paying.GuestsCalculationDTO;
import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.iface.datatype.types.FileIDType;
import com.supeyou.crudie.iface.dto.UserDTO;

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