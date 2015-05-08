package com.hotelorga.core.impl.initialdata;

import com.hotelorga.core.iface.datatype.enums.GROUPROLE;
import com.hotelorga.core.iface.dto.Acceptance2PayerDTO;
import com.hotelorga.core.iface.dto.AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2GuestGroupDTO;
import com.hotelorga.core.iface.dto.Guest2RoomDTO;
import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.iface.dto.GuestGroupDTO;
import com.hotelorga.core.iface.dto.PayerDTO;
import com.hotelorga.core.iface.dto.RoomDTO;
import com.hotelorga.core.impl.Acceptance2PayerCRUDServiceImpl;
import com.hotelorga.core.impl.AcceptanceCRUDServiceImpl;
import com.hotelorga.core.impl.Guest2AcceptanceCRUDServiceImpl;
import com.hotelorga.core.impl.Guest2GuestGroupCRUDServiceImpl;
import com.hotelorga.core.impl.Guest2RoomCRUDServiceImpl;
import com.hotelorga.core.impl.GuestCRUDServiceImpl;
import com.hotelorga.core.impl.GuestGroupCRUDServiceImpl;
import com.hotelorga.core.impl.PayerCRUDServiceImpl;
import com.hotelorga.core.impl.RoomCRUDServiceImpl;
import com.hotelorga.foundation.iface.datatype.CRUDException;
import com.hotelorga.foundation.iface.datatype.types.AmountType;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.iface.datatype.types.PositivIntegerType;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.iface.dto.UserDTO;
import com.hotelorga.foundation.impl.UserCRUDServiceImpl;

public class InitialCoreData {

	public UserDTO admin;

	public RoomDTO room1;
	public RoomDTO room2;
	public RoomDTO room3;
	public RoomDTO room4;

	public GuestDTO guestDTO_FatherBlue;
	public GuestDTO guestDTO_MotherBlue;
	public GuestDTO guestDTO_Child1Blue;
	public GuestDTO guestDTO_Child2Blue;
	public GuestDTO guestDTO_MuhamaOliv;

	public Guest2RoomDTO fatherBlue2Room1a;
	public Guest2RoomDTO fatherBlue2Room1b;
	public Guest2RoomDTO child12Room1;
	public Guest2RoomDTO motherBlue2Room2;
	public Guest2RoomDTO child22Room2;
	public Guest2RoomDTO child12Room2;
	public Guest2RoomDTO fatherBlue2Room3;
	public Guest2RoomDTO motherBlue2Room3;
	public Guest2RoomDTO child1Blue2Room3;
	public Guest2RoomDTO child2Blue2Room3;
	public Guest2RoomDTO muhamaOliv2Room3;

	public GuestGroupDTO guestGroupDTO_blue;
	public GuestGroupDTO guestGroupDTO_olive;

	public static String cityName = "München";
	public PayerDTO cityPayer;
	public PayerDTO jobcenterPayer;

	public AcceptanceDTO acceptanceBlue1;
	public AcceptanceDTO acceptanceBlue2;
	public AcceptanceDTO acceptanceOlive;

	public Acceptance2PayerDTO acceptanceBlue12cityPayer;
	public Acceptance2PayerDTO acceptanceBlue22jobcenterPayer;
	public Acceptance2PayerDTO acceptanceOlive2jobcenterPayer;

	public Guest2AcceptanceDTO fatherBlue2acceptanceBlue1;

	public Guest2AcceptanceDTO fatherBlue2acceptanceBlue2;
	public Guest2AcceptanceDTO motherBlue2acceptanceBlue2;
	public Guest2AcceptanceDTO child1Blue2acceptanceBlue2;
	public Guest2AcceptanceDTO child2Blue2acceptanceBlue2;

	public Guest2AcceptanceDTO muhama2acceptanceOlive;

	private void init() throws CRUDException {

		admin = UserCRUDServiceImpl.i().getInitialAdmin();
		room1 = createRoom("001", 2);
		room2 = createRoom("002", 3);
		room3 = createRoom("003", 4);
		room4 = createRoom("004", 2);

		guestDTO_FatherBlue = createGuest("Father", "Blue", "1972-05-12");
		guestDTO_MotherBlue = createGuest("Mother", "Blue", "1978-10-02");
		guestDTO_Child1Blue = createGuest("Child2", "Blue", "1999-05-02");
		guestDTO_Child2Blue = createGuest("Child1", "Blue", "2004-02-15");
		guestDTO_MuhamaOliv = createGuest("Muhama", "Oliv", "1968-09-11");

		fatherBlue2Room1a = createGuest2Room(guestDTO_FatherBlue, room1, "2015-03-25", "2015-04-10");
		fatherBlue2Room1b = createGuest2Room(guestDTO_FatherBlue, room1, "2015-04-12", "2015-05-15");
		child12Room1 = createGuest2Room(guestDTO_Child1Blue, room1, "2015-04-12", "2015-04-27");
		motherBlue2Room2 = createGuest2Room(guestDTO_MotherBlue, room2, "2015-04-12", "2015-05-15");
		child22Room2 = createGuest2Room(guestDTO_Child2Blue, room2, "2015-04-12", "2015-05-15");
		child12Room2 = createGuest2Room(guestDTO_Child1Blue, room2, "2015-04-12", "2015-05-15");
		fatherBlue2Room3 = createGuest2Room(guestDTO_FatherBlue, room3, "2015-05-15", "2015-06-06");
		motherBlue2Room3 = createGuest2Room(guestDTO_MotherBlue, room3, "2015-05-15", "2015-06-06");
		child1Blue2Room3 = createGuest2Room(guestDTO_Child1Blue, room3, "2015-05-15", "2015-06-06");
		child2Blue2Room3 = createGuest2Room(guestDTO_Child2Blue, room3, "2015-05-15", "2015-06-06");
		muhamaOliv2Room3 = createGuest2Room(guestDTO_MuhamaOliv, room3, "2015-03-30", "2015-05-15");

		guestGroupDTO_blue = createGuestGroup("blue");
		guestGroupDTO_olive = createGuestGroup("olive");

		createGuest2GuestGroup(guestDTO_FatherBlue, guestGroupDTO_blue, GROUPROLE.FATHER);
		createGuest2GuestGroup(guestDTO_MotherBlue, guestGroupDTO_blue, GROUPROLE.MOTHER);
		createGuest2GuestGroup(guestDTO_Child1Blue, guestGroupDTO_blue, null);
		createGuest2GuestGroup(guestDTO_Child2Blue, guestGroupDTO_blue, GROUPROLE.CHILD);
		createGuest2GuestGroup(guestDTO_MuhamaOliv, guestGroupDTO_olive, GROUPROLE.OTHER);

		acceptanceBlue1 = createAcceptance("2015-03-27", "2015-04-16", new AmountType(25000), new PositivIntegerType(30));
		acceptanceBlue2 = createAcceptance("2015-04-12", "2015-05-22", new AmountType(204000), new PositivIntegerType(120));
		acceptanceOlive = createAcceptance("2015-03-15", "2015-04-27", new AmountType(51000), new PositivIntegerType(30));

		fatherBlue2acceptanceBlue1 = createGuest2Acceptance(guestDTO_FatherBlue, acceptanceBlue1);

		fatherBlue2acceptanceBlue2 = createGuest2Acceptance(guestDTO_FatherBlue, acceptanceBlue2);
		motherBlue2acceptanceBlue2 = createGuest2Acceptance(guestDTO_MotherBlue, acceptanceBlue2);
		child1Blue2acceptanceBlue2 = createGuest2Acceptance(guestDTO_Child1Blue, acceptanceBlue2);
		child2Blue2acceptanceBlue2 = createGuest2Acceptance(guestDTO_Child2Blue, acceptanceBlue2);

		muhama2acceptanceOlive = createGuest2Acceptance(guestDTO_MuhamaOliv, acceptanceOlive);

		cityPayer = createPayer(cityName);
		jobcenterPayer = createPayer("Jobcenter");

		acceptanceBlue12cityPayer = createAcceptance2Payer(acceptanceBlue1, cityPayer);
		acceptanceBlue22jobcenterPayer = createAcceptance2Payer(acceptanceBlue2, jobcenterPayer);
		acceptanceOlive2jobcenterPayer = createAcceptance2Payer(acceptanceOlive, null/* jobcenterPayer */);

	}

	private Guest2GuestGroupDTO createGuest2GuestGroup(GuestDTO guest, GuestGroupDTO guestGroup, GROUPROLE groupRole) throws CRUDException {
		Guest2GuestGroupDTO dto = new Guest2GuestGroupDTO();
		dto.setDtoA(guest);
		dto.setDtoB(guestGroup);
		dto.setGroupRole(groupRole);
		return Guest2GuestGroupCRUDServiceImpl.i().updadd(admin, dto);
	}

	private Guest2RoomDTO createGuest2Room(GuestDTO guest, RoomDTO room, String from, String to) throws CRUDException {

		Guest2RoomDTO dto = new Guest2RoomDTO();
		dto.setDtoA(guest);
		dto.setDtoB(room);
		dto.setFromDate(new DateType(from));
		dto.setToDate(new DateType(to));
		return Guest2RoomCRUDServiceImpl.i().updadd(admin, dto);

	}

	private Guest2AcceptanceDTO createGuest2Acceptance(GuestDTO guest, AcceptanceDTO acceptance) throws CRUDException {

		Guest2AcceptanceDTO dto = new Guest2AcceptanceDTO();
		dto.setDtoA(guest);
		dto.setDtoB(acceptance);
		return Guest2AcceptanceCRUDServiceImpl.i().updadd(admin, dto);

	}

	private Acceptance2PayerDTO createAcceptance2Payer(AcceptanceDTO acceptance, PayerDTO payer) throws CRUDException {

		Acceptance2PayerDTO dto = new Acceptance2PayerDTO();
		dto.setDtoA(acceptance);
		dto.setDtoB(payer);
		return Acceptance2PayerCRUDServiceImpl.i().updadd(admin, dto);

	}

	private GuestDTO createGuest(String firstname, String lastname, String birthdate) throws CRUDException {

		GuestDTO dto = new GuestDTO();
		dto.setFirstname(new SingleLineString256Type(firstname));
		dto.setLastname(new SingleLineString256Type(lastname));
		dto.setDateOfBirth(new DateType(birthdate));
		return GuestCRUDServiceImpl.i().updadd(admin, dto);

	}

	private AcceptanceDTO createAcceptance(String fromDate, String toDate, AmountType acceptedCosts, PositivIntegerType acceptedDays) throws CRUDException {

		AcceptanceDTO dto = new AcceptanceDTO();
		dto.setFromDate(new DateType(fromDate));
		dto.setToDate(new DateType(toDate));
		dto.setAcceptedCosts(acceptedCosts);
		dto.setAcceptedDays(acceptedDays);
		return AcceptanceCRUDServiceImpl.i().updadd(admin, dto);

	}

	private PayerDTO createPayer(String name) throws CRUDException {

		PayerDTO dto = new PayerDTO();
		dto.setName(new SingleLineString256Type(name));
		return PayerCRUDServiceImpl.i().updadd(admin, dto);

	}

	private GuestGroupDTO createGuestGroup(String name) throws CRUDException {

		GuestGroupDTO dto = new GuestGroupDTO();
		dto.setName(new SingleLineString256Type(name));
		return GuestGroupCRUDServiceImpl.i().updadd(admin, dto);

	}

	private RoomDTO createRoom(String name, int nrOfRooms) throws CRUDException {

		RoomDTO dto = new RoomDTO();
		dto.setName(new SingleLineString256Type(name));
		dto.setNumberOfBeds(new PositivIntegerType(nrOfRooms));
		return RoomCRUDServiceImpl.i().updadd(admin, dto);

	}

	// --- SINGLETON ---
	private static InitialCoreData instance = null;

	public static InitialCoreData i() throws CRUDException {
		if (instance == null) {
			instance = new InitialCoreData();
			instance.init();
		}
		return instance;
	}
}
