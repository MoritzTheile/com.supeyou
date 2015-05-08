package com.supeyou.crudie.impl.initialdata;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.enums.ROLE;
import com.supeyou.crudie.iface.datatype.types.EmailAddressType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.GroupDTO;
import com.supeyou.crudie.iface.dto.User2GroupDTO;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.GroupCRUDServiceImpl;
import com.supeyou.crudie.impl.User2GroupCRUDServiceImpl;
import com.supeyou.crudie.impl.UserCRUDServiceImpl;

public class InitialData {

	private static Logger log = Logger.getLogger(InitialData.class.getName());

	public UserDTO initialAdmin;

	public UserDTO userDTO_MrGreen;
	public UserDTO userDTO_MrRed;
	public UserDTO userDTO_MrBlue;
	public UserDTO userDTO_MrColorful;

	public GroupDTO groupDTO_Green;
	public GroupDTO groupDTO_Red;
	public GroupDTO groupDTO_Blue;

	// Singleton ------------------------------
	private static InitialData instance;

	private InitialData() throws CRUDException {

		log.log(Level.INFO, "creating initial data...");

		initialAdmin = UserCRUDServiceImpl.i().getInitialAdmin();

		userDTO_MrGreen = createUser("MrGreen");
		userDTO_MrRed = createUser("MrRed");
		userDTO_MrBlue = createUser("MrBlue");
		userDTO_MrColorful = createUser("MrColorful");

		groupDTO_Green = createGroup("green");
		groupDTO_Red = createGroup("red");
		groupDTO_Blue = createGroup("blue");

		createAsso(userDTO_MrGreen, groupDTO_Green);
		createAsso(userDTO_MrBlue, groupDTO_Blue);
		createAsso(userDTO_MrRed, groupDTO_Red);

		createAsso(userDTO_MrColorful, groupDTO_Green);
		createAsso(userDTO_MrColorful, groupDTO_Blue);
		createAsso(userDTO_MrColorful, groupDTO_Red);

		creatingManyUsers();

		log.log(Level.INFO, "... created initial data.");

	}

	private User2GroupDTO createAsso(UserDTO userDTO, GroupDTO groupDTO) throws CRUDException {

		User2GroupDTO user2GroupDTO = new User2GroupDTO();
		user2GroupDTO.setDtoA(userDTO);
		user2GroupDTO.setDtoB(groupDTO);
		return User2GroupCRUDServiceImpl.i().updadd(initialAdmin, user2GroupDTO);

	}

	private GroupDTO createGroup(String name) throws CRUDException {
		GroupDTO dto = new GroupDTO();
		dto.setName(new SingleLineString256Type(name));
		return GroupCRUDServiceImpl.i().updadd(initialAdmin, dto);
	}

	private UserDTO createUser(String name) throws CRUDException {
		UserDTO userDTO = new UserDTO();
		userDTO.setActive(true);
		List<ROLE> roles = new ArrayList<>();
		userDTO.setRoles(roles);
		userDTO.setLoginId(new SingleLineString256Type(name + "@mtheile.com"));
		userDTO.setEmailAddress(new EmailAddressType(name + "@mtheile.com"));
		return UserCRUDServiceImpl.i().updadd(initialAdmin, userDTO);
	}

	private void creatingManyUsers() throws CRUDException {
		for (int i = 0; i < 120; i++) {
			UserDTO userDTO = new UserDTO();
			userDTO.setLoginId(new SingleLineString256Type("initialUser" + i + "@mtheile.com"));
			userDTO.setEmailAddress(new EmailAddressType("initialUser" + i + "@mtheile.com"));
			UserCRUDServiceImpl.i().updadd(initialAdmin, userDTO);
		}
	}

	public static InitialData i() throws CRUDException {
		if (instance == null) {
			instance = new InitialData();
		}
		return instance;
	}

}