package com.hotelorga.core.impl.paying;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hotelorga.core.iface.dto.AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2RoomDTO;
import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.iface.dto.RoomDTO;
import com.hotelorga.core.iface.dto.paying.GuestCalculationDTO;
import com.hotelorga.core.iface.dto.paying.GuestCalculationState;
import com.hotelorga.core.iface.dto.paying.GuestCalculationState.STATE;
import com.hotelorga.core.impl.util.STATICS;
import com.hotelorga.foundation.iface.datatype.types.AmountType;
import com.hotelorga.foundation.iface.datatype.types.DateType;

public class CalculationHelper {

	public static GuestCalculationDTO getGuestCalculationDTO(

			GuestDTO guestDTO,
			DateType from,
			DateType to,
			List<Guest2RoomDTO> guest2RoomDTOs,
			List<Guest2AcceptanceDTO> guest2AcceptanceDTOs

			) {

		List<DateType> dates = STATICS.getDates(from, to);

		List<RoomDTO> rooms = calculateRooms(guest2RoomDTOs);
		List<AcceptanceDTO> acceptances = calculateAcceptances(guest2AcceptanceDTOs);
		Map<RoomDTO, Set<DateType>> room2dates = calculateRoom2Dates(dates, guest2RoomDTOs);
		Map<DateType, Set<Guest2RoomDTO>> date2guest2roomDTOs = calculateDate2RoomDTOs(dates, guest2RoomDTOs);
		AmountType cost = getCosts(dates, date2guest2roomDTOs);
		Map<Guest2AcceptanceDTO, Set<DateType>> acceptance2dates = acceptances2Dates(dates, guest2AcceptanceDTOs);
		Map<DateType, Set<Guest2AcceptanceDTO>> date2guest2acceptances = calculateDate2guest2AccpetanceDTOs(dates, guest2AcceptanceDTOs);
		Map<Guest2AcceptanceDTO, Double> acceptance2acceptedDailyAmount = calculateAcceptedDailyAmount(guest2AcceptanceDTOs);

		List<GuestCalculationState> states = new ArrayList<>();

		states.addAll(checkForMultiRooms(date2guest2roomDTOs));
		states.addAll(checkForMultiAcceptances(date2guest2acceptances));

		return new GuestCalculationDTO(guestDTO, from, to, guest2RoomDTOs, guest2AcceptanceDTOs, dates, rooms, acceptances, room2dates, date2guest2roomDTOs, cost, acceptance2dates, date2guest2acceptances, acceptance2acceptedDailyAmount, states);

	}

	private static List<AcceptanceDTO> calculateAcceptances(List<Guest2AcceptanceDTO> guest2AcceptanceDTOs) {
		List<AcceptanceDTO> result = new ArrayList<>();
		for (Guest2AcceptanceDTO guest2AcceptanceDTO : guest2AcceptanceDTOs) {
			result.add(guest2AcceptanceDTO.getDtoB());
		}
		return result;
	}

	private static List<RoomDTO> calculateRooms(List<Guest2RoomDTO> guest2RoomDTOs) {
		List<RoomDTO> result = new ArrayList<>();
		for (Guest2RoomDTO guest2RoomDTO : guest2RoomDTOs) {
			result.add(guest2RoomDTO.getDtoB());
		}
		return result;
	}

	private static Collection<? extends GuestCalculationState> checkForMultiAcceptances(Map<DateType, Set<Guest2AcceptanceDTO>> date2guest2acceptances) {
		List<GuestCalculationState> guestCalculationStates = new ArrayList<>();
		for (DateType date : date2guest2acceptances.keySet()) {
			if (date2guest2acceptances.get(date).size() > 1) {
				guestCalculationStates.add(new GuestCalculationState(STATE.ERROR, "Das Datum " + date + " ist mit mehreren Kostenübernahmen gedeckt."));
			}
		}
		return guestCalculationStates;

	}

	private static List<GuestCalculationState> checkForMultiRooms(Map<DateType, Set<Guest2RoomDTO>> date2guest2roomDTOs) {
		List<GuestCalculationState> guestCalculationStates = new ArrayList<>();
		for (DateType date : date2guest2roomDTOs.keySet()) {
			if (date2guest2roomDTOs.get(date).size() > 1) {
				guestCalculationStates.add(new GuestCalculationState(STATE.ERROR, "Das Datum " + date + " ist mit mehreren Zimmern belegt."));
			}
		}
		return guestCalculationStates;
	}

	private static Map<Guest2AcceptanceDTO, Double> calculateAcceptedDailyAmount(List<Guest2AcceptanceDTO> guest2AcceptanceDTOs) {
		Map<Guest2AcceptanceDTO, Double> acceptance2acceptedDailyAmount = new HashMap<>();
		for (Guest2AcceptanceDTO guest2AcceptanceDTO : guest2AcceptanceDTOs) {
			acceptance2acceptedDailyAmount.put(guest2AcceptanceDTO, getDailyAcceptedValue(guest2AcceptanceDTO));
		}
		return acceptance2acceptedDailyAmount;
	}

	private static Double getDailyAcceptedValue(Guest2AcceptanceDTO guest2AcceptanceDTO) {
		if (guest2AcceptanceDTO.getDtoB().getAcceptedCosts() == null || guest2AcceptanceDTO.getDtoB().getAcceptedDays() == null) {
			return new Double(0);
		}
		return new Double(guest2AcceptanceDTO.getDtoB().getAcceptedCosts().value()) / guest2AcceptanceDTO.getDtoB().getAcceptedDays().value();

	}

	private static AmountType getCosts(List<DateType> dates, Map<DateType, Set<Guest2RoomDTO>> date2guest2roomDTOs) {

		Double costs = new Double(0);
		int i = 0;
		for (DateType date : dates) {

			if (date2guest2roomDTOs.get(date) != null) {

				for (Guest2RoomDTO guest2RoomDTO : date2guest2roomDTOs.get(date)) {
					if (guest2RoomDTO.getMonthlyCosts() != null) {
						i++;
						if (i >= 31) {
							// in hotel business a month has max 30 accountable days:
							break;
						}
						costs = costs + (new Double(guest2RoomDTO.getMonthlyCosts().value()) / 30);

						// in case of multiple room occupations at that day we decide to use only the first occupation:
						break;
					}
				}
			}

		}

		return new AmountType(costs.intValue());
	}

	private static Map<DateType, Set<Guest2RoomDTO>> calculateDate2RoomDTOs(List<DateType> dates, List<Guest2RoomDTO> guest2RoomDTOs) {

		Map<DateType, Set<Guest2RoomDTO>> date2guest2roomDTOs = new HashMap<>();

		for (DateType date : dates) {
			date2guest2roomDTOs.put(date, new HashSet<Guest2RoomDTO>());
		}

		for (DateType date : dates) {

			for (Guest2RoomDTO guest2RoomDTO : guest2RoomDTOs) {

				if (STATICS.isInTimeFrame(date, guest2RoomDTO.getFromDate(), guest2RoomDTO.getToDate())) {
					date2guest2roomDTOs.get(date).add(guest2RoomDTO);
				}

			}

		}

		return date2guest2roomDTOs;

	}

	private static Map<RoomDTO, Set<DateType>> calculateRoom2Dates(List<DateType> dates, List<Guest2RoomDTO> guest2RoomDTOs) {

		Map<RoomDTO, Set<DateType>> room2dates = new HashMap<RoomDTO, Set<DateType>>();

		for (Guest2RoomDTO guest2RoomDTO : guest2RoomDTOs) {
			room2dates.put(guest2RoomDTO.getDtoB(), new HashSet<DateType>());
		}

		for (DateType date : dates) {

			for (Guest2RoomDTO guest2RoomDTO : guest2RoomDTOs) {

				if (STATICS.isInTimeFrame(date, guest2RoomDTO.getFromDate(), guest2RoomDTO.getToDate())) {
					room2dates.get(guest2RoomDTO.getDtoB()).add(date);
				}

			}

		}

		return room2dates;

	}

	private static Map<Guest2AcceptanceDTO, Set<DateType>> acceptances2Dates(List<DateType> dates, List<Guest2AcceptanceDTO> guest2AcceptancesDTOs) {

		Map<Guest2AcceptanceDTO, Set<DateType>> acceptances2dates = new HashMap<Guest2AcceptanceDTO, Set<DateType>>();

		for (Guest2AcceptanceDTO guest2Acceptance : guest2AcceptancesDTOs) {
			acceptances2dates.put(guest2Acceptance, new HashSet<DateType>());
		}

		for (DateType date : dates) {

			for (Guest2AcceptanceDTO guest2acceptanceDTO : guest2AcceptancesDTOs) {

				if (STATICS.isInTimeFrame(date, guest2acceptanceDTO.getDtoB().getFromDate(), guest2acceptanceDTO.getDtoB().getToDate())) {
					acceptances2dates.get(guest2acceptanceDTO).add(date);
				}

			}

		}

		return acceptances2dates;

	}

	private static Map<DateType, Set<Guest2AcceptanceDTO>> calculateDate2guest2AccpetanceDTOs(List<DateType> dates, List<Guest2AcceptanceDTO> guest2AcceptanceDTOs) {

		Map<DateType, Set<Guest2AcceptanceDTO>> date2guest2roomDTOs = new HashMap<>();

		for (DateType date : dates) {
			date2guest2roomDTOs.put(date, new HashSet<Guest2AcceptanceDTO>());
		}

		for (DateType date : dates) {

			for (Guest2AcceptanceDTO guest2AcceptanceDTO : guest2AcceptanceDTOs) {

				if (STATICS.isInTimeFrame(date, guest2AcceptanceDTO.getDtoB().getFromDate(), guest2AcceptanceDTO.getDtoB().getToDate())) {
					date2guest2roomDTOs.get(date).add(guest2AcceptanceDTO);
				}

			}

		}

		return date2guest2roomDTOs;

	}

	public static int calculateOverallCosts(List<GuestCalculationDTO> guestCalculationDTOs) {

		int costs = 0;

		for (GuestCalculationDTO guestCalculationDTO : guestCalculationDTOs) {

			costs += guestCalculationDTO.getCost().value();

		}

		return costs;

	}

	public static int calculateOverallAcceptedCosts(List<GuestCalculationDTO> guestCalculationDTOs) {

		Double overallAcceptedCosts = new Double(0);

		for (GuestCalculationDTO guestCalculationDTO : guestCalculationDTOs) {

			overallAcceptedCosts += getAcceptedCost(guestCalculationDTO);

		}

		return overallAcceptedCosts.intValue();

	}

	public static Double getAcceptedCost(GuestCalculationDTO guestCalculationDTO) {
		Double overallAcceptedCosts = new Double(0);
		int i = 0;
		for (DateType date : guestCalculationDTO.getDates()) {

			if (!guestCalculationDTO.getRoomsOfDate(date).isEmpty()) { // => guest stayed in hotel that day
				Guest2AcceptanceDTO guest2AcceptanceDTO_used = null;
				for (Guest2AcceptanceDTO guest2AcceptanceDTO : guestCalculationDTO.getAcceptancesOfDate(date)) {
					if (guest2AcceptanceDTO_used == null) {
						guest2AcceptanceDTO_used = guest2AcceptanceDTO;
					} else {
						// here can go some criteria to choose an Acceptance if multiple are available
					}
				}
				if (guest2AcceptanceDTO_used != null
						&& guest2AcceptanceDTO_used.getDtoB().getAcceptedDays() != null
						&& guest2AcceptanceDTO_used.getDtoB().getAcceptedCosts() != null) {
					i++;
					if (i >= 31) {
						// in hotel business a month has max 30 accountable days:
						break;
					}
					Double acceptedCosts = new Double(guest2AcceptanceDTO_used.getDtoB().getAcceptedCosts().value());
					Double acceptedCostsPerDayPerPerson = acceptedCosts / guest2AcceptanceDTO_used.getDtoB().getAcceptedDays().value();
					overallAcceptedCosts += acceptedCostsPerDayPerPerson;

				}
			}
		}
		return overallAcceptedCosts;
	}
}
