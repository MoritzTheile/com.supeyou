package com.hotelorga.core.iface.dto.paying;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hotelorga.core.iface.dto.AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2RoomDTO;
import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.iface.dto.RoomDTO;
import com.hotelorga.foundation.iface.datatype.types.AmountType;
import com.hotelorga.foundation.iface.datatype.types.DateType;

public class GuestCalculationDTO implements Serializable {

	private static final long serialVersionUID = 845622450L;

	private GuestDTO guestDTO;
	private DateType from;
	private DateType to;
	private List<Guest2RoomDTO> guest2RoomDTOs;
	private List<Guest2AcceptanceDTO> guest2AcceptanceDTOs;
	private List<DateType> dates;
	private List<RoomDTO> rooms;
	private List<AcceptanceDTO> acceptances;
	private Map<RoomDTO, Set<DateType>> room2dates;
	private Map<DateType, Set<Guest2RoomDTO>> date2guest2roomDTOs;
	private AmountType cost;
	private Map<Guest2AcceptanceDTO, Set<DateType>> acceptance2dates;
	private Map<DateType, Set<Guest2AcceptanceDTO>> date2acceptances;
	private Map<Guest2AcceptanceDTO, Double> acceptance2acceptedDailyAmount;
	private List<GuestCalculationState> states;

	// zero arg constructor
	public GuestCalculationDTO() {

	}

	public GuestCalculationDTO(

			GuestDTO guestDTO,
			DateType from,
			DateType to,
			List<Guest2RoomDTO> guest2RoomDTOs,
			List<Guest2AcceptanceDTO> guest2AcceptanceDTOs,
			List<DateType> dates,
			List<RoomDTO> rooms,
			List<AcceptanceDTO> acceptances,
			Map<RoomDTO, Set<DateType>> room2dates,
			Map<DateType, Set<Guest2RoomDTO>> date2roomDTOs,
			AmountType cost,
			Map<Guest2AcceptanceDTO, Set<DateType>> acceptance2dates,
			Map<DateType, Set<Guest2AcceptanceDTO>> date2acceptances,
			Map<Guest2AcceptanceDTO, Double> acceptance2acceptedDailyAmount,
			List<GuestCalculationState> states

	) {

		this.guestDTO = guestDTO;
		this.from = from;
		this.to = to;
		this.guest2RoomDTOs = guest2RoomDTOs;
		this.guest2AcceptanceDTOs = guest2AcceptanceDTOs;
		this.dates = dates;
		this.rooms = rooms;
		this.acceptances = acceptances;
		this.room2dates = room2dates;
		this.date2guest2roomDTOs = date2roomDTOs;
		this.cost = cost;
		this.acceptance2dates = acceptance2dates;
		this.date2acceptances = date2acceptances;
		this.acceptance2acceptedDailyAmount = acceptance2acceptedDailyAmount;
		this.states = states;
	}

	public GuestDTO getGuestDTO() {
		return guestDTO;
	}

	public DateType getFrom() {
		return from;
	}

	public DateType getTo() {
		return to;
	}

	public List<Guest2RoomDTO> getGuest2RoomDTOs() {
		return guest2RoomDTOs;
	}

	public List<Guest2AcceptanceDTO> getGuest2AcceptanceDTOs() {
		return guest2AcceptanceDTOs;
	}

	public List<DateType> getDates() {
		return dates;
	}

	public List<RoomDTO> getRooms() {
		return rooms;
	}

	public List<AcceptanceDTO> getAcceptances() {
		return acceptances;
	}

	public Collection<Guest2RoomDTO> getRoomsOfDate(DateType date) {
		return date2guest2roomDTOs.get(date);
	}

	public Collection<DateType> getDatesInRoom(RoomDTO roomDTO) {
		return room2dates.get(roomDTO);
	}

	public AmountType getCost() {
		return cost;
	}

	public Collection<Guest2AcceptanceDTO> getAcceptancesOfDate(DateType date) {
		return date2acceptances.get(date);
	}

	public Collection<DateType> getDatesOfAcceptance(Guest2AcceptanceDTO guest2AcceptanceDTO) {
		return acceptance2dates.get(guest2AcceptanceDTO);
	}

	public Double getAcceptedDailyAmount(Guest2AcceptanceDTO guest2AcceptanceDTO) {
		return acceptance2acceptedDailyAmount.get(guest2AcceptanceDTO);
	}

	public List<GuestCalculationState> getStates() {
		return states;
	}

}
