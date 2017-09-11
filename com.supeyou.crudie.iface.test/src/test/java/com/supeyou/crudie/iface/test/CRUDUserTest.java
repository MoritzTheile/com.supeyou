package com.supeyou.crudie.iface.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.supeyou.crudie.iface.UserCRUDService;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.datatype.enums.LOCALE;
import com.supeyou.crudie.iface.datatype.enums.MIMETYPE;
import com.supeyou.crudie.iface.datatype.enums.ROLE;
import com.supeyou.crudie.iface.datatype.types.AmountType;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.iface.datatype.types.FileIDType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.datatype.types.UserIDType;
import com.supeyou.crudie.iface.dto.DTOFetchList;
import com.supeyou.crudie.iface.dto.FileDTO;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.iface.dto.UserFetchQuery;
import com.supeyou.crudie.impl.FileCRUDServiceImpl;
import com.supeyou.crudie.impl.UserCRUDServiceImpl;
import com.supeyou.crudie.impl.dtohelper.ReflectionExporter;

public class CRUDUserTest {

	private static UserCRUDService userService = UserCRUDServiceImpl.i();

	@Test
	public void test() throws Exception {

		UserDTO actor = userService.getInitialAdmin();

		String email = "DagobertDuck1@moritztheile.de";

		UserIDType userID1 = createUser(actor, email);

		UserDTO userDTO1 = userService.get(actor, userID1);

		Assert.assertNotNull(userDTO1);

		userService.delete(actor, userID1);

		Assert.assertNull(userService.get(actor, userID1));

	}

	@Test
	public void testFetch() throws Exception {

		UserDTO actor = userService.getInitialAdmin();

		int size = userService.fetchList(actor, null, new UserFetchQuery()).size();

		for (int i = 0; i < 32; i++) {
			createUser(actor, "DonaldDuck" + i + "@moritztheile.de");
		}
		{
			DTOFetchList<UserDTO> fetchList = userService.fetchList(actor, null, new UserFetchQuery());

			Assert.assertEquals(size + 32, fetchList.size());
		}
		{
			DTOFetchList<UserDTO> fetchList = userService.fetchList(actor, new Page(0, 5), new UserFetchQuery());

			Assert.assertEquals(5, fetchList.size());
		}
	}

	@Test
	public void testExport() throws Exception {

		UserDTO actor = userService.getInitialAdmin();

		long timestampStart = System.currentTimeMillis();
		FileIDType fileIDType = userService.exportData(actor, new UserFetchQuery());
		long timestampStop = System.currentTimeMillis();

		System.out.println("Export in: " + (timestampStop - timestampStart) + "ms");
		Assert.assertTrue((timestampStop - timestampStart) < 20000);

		Assert.assertNotNull(fileIDType);

		FileDTO csvFile = FileCRUDServiceImpl.i().get(actor, fileIDType);

		Assert.assertNotNull(csvFile);

		byte[] data = FileCRUDServiceImpl.i().getData(actor, fileIDType);

		Assert.assertNotNull(data);

		Assert.assertTrue(data.length > 0);

		System.out.println("csvFile.getName()=" + csvFile.getName());
	}

	@Test
	public void testImport() throws Exception {

		UserDTO actor = userService.getInitialAdmin();

		FileDTO csvFileDTO = new FileDTO();
		csvFileDTO.setMimetype(MIMETYPE.TEXT_COMMA_SEPERATED_VALUES);
		csvFileDTO.setName(new SingleLineString256Type("new tmp file"));
		csvFileDTO = FileCRUDServiceImpl.i().updadd(actor, csvFileDTO);

		UserDTO userDTO = getTestuser();
		List<UserDTO> userDTOs = new ArrayList<>();
		userDTOs.add(userDTO);
		byte[] csvData = ReflectionExporter.dtos2csv(userDTOs).getBytes();

		System.out.println(new String(csvData));

		FileCRUDServiceImpl.i().setData(actor, csvFileDTO.getId(), csvData);

		List<UserDTO> usersBeforeImport = UserCRUDServiceImpl.i().fetchList(actor, new Page(), new UserFetchQuery());
		UserCRUDServiceImpl.i().importData(actor, csvFileDTO.getId());
		List<UserDTO> importedUsers = UserCRUDServiceImpl.i().fetchList(actor, new Page(), new UserFetchQuery());
		importedUsers.removeAll(usersBeforeImport);

		Assert.assertEquals(1, importedUsers.size());

		UserDTO importedUser = importedUsers.iterator().next();

		Assert.assertEquals(userDTO.getActive(), importedUser.getActive());
		Assert.assertEquals(userDTO.getAmount(), importedUser.getAmount());
		Assert.assertEquals(userDTO.getBirthday(), importedUser.getBirthday());
		Assert.assertEquals(userDTO.getLocale(), importedUser.getLocale());
		Assert.assertEquals(userDTO.getLoginId(), importedUser.getLoginId());

	}

	private UserDTO getTestuser() {

		UserDTO userDTO = new UserDTO();
		userDTO.setActive(true);
		userDTO.setAmount(new AmountType(9999));
		userDTO.setBirthday(new DateType("2014-12-23"));
		userDTO.setLocale(LOCALE.de);
		userDTO.setLoginId(new SingleLineString256Type("testImport@crudusertest"));

		userDTO.addRole(ROLE.USER);
		return userDTO;

	}

	private UserIDType createUser(UserDTO adminUserDTO, String name1) throws CRUDException {

		UserDTO userDTO = new UserDTO();
		userDTO.setLoginId(new SingleLineString256Type(name1));

		UserDTO reloadedUserDTO = userService.updadd(adminUserDTO, userDTO);

		Assert.assertNotNull(reloadedUserDTO);
		Assert.assertNotNull(reloadedUserDTO.getId());
		return reloadedUserDTO.getId();

	}

}
