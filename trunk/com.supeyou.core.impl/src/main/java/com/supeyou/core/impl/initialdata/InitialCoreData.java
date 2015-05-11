package com.supeyou.core.impl.initialdata;

import com.supeyou.core.iface.dto.RoomDTO;
import com.supeyou.core.impl.RoomCRUDServiceImpl;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.types.PositivIntegerType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.UserCRUDServiceImpl;

public class InitialCoreData {

	public UserDTO admin;

	public RoomDTO room1;
	public RoomDTO room2;
	public RoomDTO room3;
	public RoomDTO room4;

	private void init() throws CRUDException {

		admin = UserCRUDServiceImpl.i().getInitialAdmin();
		room1 = createRoom("001", 2);
		room2 = createRoom("002", 3);
		room3 = createRoom("003", 4);
		room4 = createRoom("004", 2);

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
