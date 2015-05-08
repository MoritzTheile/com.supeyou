package com.hotelorga.foundation.iface.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.hotelorga.foundation.iface.datatype.CRUDException;
import com.hotelorga.foundation.iface.datatype.Page;
import com.hotelorga.foundation.iface.datatype.enums.MIMETYPE;
import com.hotelorga.foundation.iface.datatype.enums.ROLE;
import com.hotelorga.foundation.iface.datatype.types.EmailAddressType;
import com.hotelorga.foundation.iface.datatype.types.FileIDType;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.iface.dto.DTOFetchList;
import com.hotelorga.foundation.iface.dto.FileDTO;
import com.hotelorga.foundation.iface.dto.GroupDTO;
import com.hotelorga.foundation.iface.dto.User2GroupDTO;
import com.hotelorga.foundation.iface.dto.User2GroupFetchQuery;
import com.hotelorga.foundation.iface.dto.UserDTO;
import com.hotelorga.foundation.impl.FileCRUDServiceImpl;
import com.hotelorga.foundation.impl.GroupCRUDServiceImpl;
import com.hotelorga.foundation.impl.User2GroupCRUDServiceImpl;
import com.hotelorga.foundation.impl.UserCRUDServiceImpl;
import com.hotelorga.foundation.impl.dtohelper.ReflectionExporter;
import com.hotelorga.foundation.impl.initialdata.InitialData;

public class CRUDUser2GroupTest {

	@Test
	public void testFetch() throws Exception {

		InitialData initialData = InitialData.i();

		{// fetch all assos
			DTOFetchList<User2GroupDTO> fetchList = User2GroupCRUDServiceImpl.i().fetchList(initialData.initialAdmin, new Page(), new User2GroupFetchQuery());
			for (User2GroupDTO dto : fetchList) {
				Assert.assertNotNull(dto.getDtoA().getEmailAddress());
				Assert.assertNotNull(dto.getDtoB().getName());
			}

			Assert.assertEquals(6, fetchList.size());
		}
		{// fetch Mr Colorful Groups
			User2GroupFetchQuery fetchAssoQuery = new User2GroupFetchQuery();
			fetchAssoQuery.setIdA(initialData.userDTO_MrColorful.getId());
			DTOFetchList<User2GroupDTO> fetchList = User2GroupCRUDServiceImpl.i().fetchList(initialData.initialAdmin, new Page(), fetchAssoQuery);
			Assert.assertEquals(3, fetchList.size());
		}
		{// fetch Users with color green
			User2GroupFetchQuery fetchAssoQuery = new User2GroupFetchQuery();
			fetchAssoQuery.setIdB(initialData.groupDTO_Green.getId());
			DTOFetchList<User2GroupDTO> fetchList = User2GroupCRUDServiceImpl.i().fetchList(initialData.initialAdmin, new Page(), fetchAssoQuery);
			Assert.assertEquals(2, fetchList.size());
		}
		{// fetch Asso between Mr Colorful and color green
			User2GroupFetchQuery fetchAssoQuery = new User2GroupFetchQuery();
			fetchAssoQuery.setIdA(initialData.userDTO_MrColorful.getId());
			fetchAssoQuery.setIdB(initialData.groupDTO_Green.getId());
			DTOFetchList<User2GroupDTO> fetchList = User2GroupCRUDServiceImpl.i().fetchList(initialData.initialAdmin, new Page(), fetchAssoQuery);
			Assert.assertEquals(1, fetchList.size());
		}
	}

	@Test
	public void testCreateDelete() throws Exception {

		InitialData initialData = InitialData.i();

		User2GroupDTO user2GroupDTO = new User2GroupDTO();
		{// creating one asso
			user2GroupDTO.setDtoA(initialData.userDTO_MrBlue);
			user2GroupDTO.setDtoB(initialData.groupDTO_Green);
			user2GroupDTO = User2GroupCRUDServiceImpl.i().updadd(initialData.initialAdmin, user2GroupDTO);
		}

		{// fetch all assos
			DTOFetchList<User2GroupDTO> fetchList = User2GroupCRUDServiceImpl.i().fetchList(initialData.initialAdmin, new Page(), new User2GroupFetchQuery());
			Assert.assertEquals(7, fetchList.size());
		}
		{ // delete one asso
			User2GroupCRUDServiceImpl.i().delete(initialData.initialAdmin, user2GroupDTO.getId());
		}
		{// fetch all assos
			DTOFetchList<User2GroupDTO> fetchList = User2GroupCRUDServiceImpl.i().fetchList(initialData.initialAdmin, new Page(), new User2GroupFetchQuery());
			Assert.assertEquals(6, fetchList.size());
		}

	}

	/**
	 * On deleting A or B all assos between A and B shall be deleted.
	 */
	@Test
	public void testCascadingDeleteA() throws Exception {

		InitialData initialData = InitialData.i();

		GroupDTO groupDTO = new GroupDTO();
		UserDTO userDTO = new UserDTO();
		User2GroupDTO user2GroupDTO1 = new User2GroupDTO();
		User2GroupDTO user2GroupDTO2 = new User2GroupDTO();
		creatingAAndBWithOneAssosBetween(initialData, groupDTO, userDTO, user2GroupDTO1, user2GroupDTO2);

		{// fetch all assos
			DTOFetchList<User2GroupDTO> fetchList = User2GroupCRUDServiceImpl.i().fetchList(initialData.initialAdmin, new Page(), new User2GroupFetchQuery());
			Assert.assertEquals(7, fetchList.size());
		}

		UserCRUDServiceImpl.i().delete(initialData.initialAdmin, userDTO.getId());

		{// fetch all assos
			DTOFetchList<User2GroupDTO> fetchList = User2GroupCRUDServiceImpl.i().fetchList(initialData.initialAdmin, new Page(), new User2GroupFetchQuery());
			Assert.assertEquals(6, fetchList.size());
		}
		// making sure not deleting B
		Assert.assertNotNull(GroupCRUDServiceImpl.i().get(initialData.initialAdmin, groupDTO.getId()));

		// cleaning

		GroupCRUDServiceImpl.i().delete(initialData.initialAdmin, groupDTO.getId());

	}

	/**
	 * On deleting A or B all assos between A and B shall be deleted.
	 */
	@Test
	public void testCascadingDeleteB() throws Exception {

		InitialData initialData = InitialData.i();

		GroupDTO groupDTO = new GroupDTO();
		UserDTO userDTO = new UserDTO();
		User2GroupDTO user2GroupDTO1 = new User2GroupDTO();
		User2GroupDTO user2GroupDTO2 = new User2GroupDTO();
		creatingAAndBWithOneAssosBetween(initialData, groupDTO, userDTO, user2GroupDTO1, user2GroupDTO2);

		{// fetch all assos
			DTOFetchList<User2GroupDTO> fetchList = User2GroupCRUDServiceImpl.i().fetchList(initialData.initialAdmin, new Page(), new User2GroupFetchQuery());
			Assert.assertEquals(7, fetchList.size());
		}

		GroupCRUDServiceImpl.i().delete(initialData.initialAdmin, groupDTO.getId());

		{// fetch all assos
			DTOFetchList<User2GroupDTO> fetchList = User2GroupCRUDServiceImpl.i().fetchList(initialData.initialAdmin, new Page(), new User2GroupFetchQuery());
			Assert.assertEquals(6, fetchList.size());
		}

		// making sure not deleting A
		Assert.assertNotNull(UserCRUDServiceImpl.i().get(initialData.initialAdmin, userDTO.getId()));

		// cleaning
		UserCRUDServiceImpl.i().delete(initialData.initialAdmin, userDTO.getId());
	}

	private void creatingAAndBWithOneAssosBetween(InitialData initialData, GroupDTO groupDTO, UserDTO userDTO, User2GroupDTO user2GroupDTO1, User2GroupDTO user2GroupDTO2) throws CRUDException {
		{
			groupDTO.setName(new SingleLineString256Type("GroupB"));
			groupDTO.setId(GroupCRUDServiceImpl.i().updadd(initialData.initialAdmin, groupDTO).getId());
		}
		{
			userDTO.setActive(true);
			List<ROLE> roles = new ArrayList<>();
			userDTO.setRoles(roles);
			userDTO.setLoginId(new SingleLineString256Type("UserA" + "@mtheile.com"));
			userDTO.setEmailAddress(new EmailAddressType("UserA" + "@mtheile.com"));
			userDTO.setId(UserCRUDServiceImpl.i().updadd(initialData.initialAdmin, userDTO).getId());

		}
		{// creating one asso
			user2GroupDTO1.setDtoA(userDTO);
			user2GroupDTO1.setDtoB(groupDTO);
			user2GroupDTO1.setId(User2GroupCRUDServiceImpl.i().updadd(initialData.initialAdmin, user2GroupDTO1).getId());
		}

	}

	@Test
	public void testExport() throws Exception {

		UserDTO actor = InitialData.i().initialAdmin;

		FileIDType fileIDType = User2GroupCRUDServiceImpl.i().exportData(actor, new User2GroupFetchQuery());

		Assert.assertNotNull(fileIDType);

		FileDTO csvFile = FileCRUDServiceImpl.i().get(actor, fileIDType);

		Assert.assertNotNull(csvFile);

		byte[] data = FileCRUDServiceImpl.i().getData(actor, fileIDType);

		Assert.assertNotNull(data);

		Assert.assertTrue(data.length > 0);

		System.out.println("csvFile.getName()=" + csvFile.getName());

		String csvString = new String(data);

		System.out.println(csvString);

		Assert.assertTrue(
				csvString.matches("" +
						"Id;DtoA;DtoB;\n" +
						".*;.*;.*;\n" +
						".*;.*;.*;\n" +
						".*;.*;.*;\n" +
						".*;.*;.*;\n" +
						".*;.*;.*;\n" +
						".*;.*;.*;\n"
						)
				);
	}

	@Test
	public void testImport() throws Exception {

		UserDTO actor = InitialData.i().initialAdmin;

		FileDTO csvFileDTO = new FileDTO();
		csvFileDTO.setMimetype(MIMETYPE.TEXT_COMMA_SEPERATED_VALUES);
		csvFileDTO.setName(new SingleLineString256Type("new tmp file"));
		csvFileDTO = FileCRUDServiceImpl.i().updadd(actor, csvFileDTO);

		User2GroupDTO user2groupDTO = getMrRed2GroupGreenDTO(actor);

		List<User2GroupDTO> dtos = new ArrayList<>();
		dtos.add(user2groupDTO);
		byte[] csvData = ReflectionExporter.dtos2csv(dtos).getBytes();

		System.out.println(new String(csvData));

		FileCRUDServiceImpl.i().setData(actor, csvFileDTO.getId(), csvData);

		User2GroupCRUDServiceImpl.i().importData(actor, csvFileDTO.getId());

		User2GroupFetchQuery user2GroupFetchQuery = new User2GroupFetchQuery();
		user2GroupFetchQuery.setIdA(InitialData.i().userDTO_MrRed.getId());

		DTOFetchList<User2GroupDTO> user2GroupDTOsOfMrRed = User2GroupCRUDServiceImpl.i().fetchList(actor, new Page(), user2GroupFetchQuery);

		User2GroupDTO importedUser2GroupDTO = null;

		for (User2GroupDTO user2GroupDTO2 : user2GroupDTOsOfMrRed) {

			if (user2groupDTO.getDtoB().getId().equals(InitialData.i().groupDTO_Green.getId())) {
				importedUser2GroupDTO = user2GroupDTO2;
				break;
			}

		}

		Assert.assertNotNull(importedUser2GroupDTO);

		// cleaning
		User2GroupCRUDServiceImpl.i().delete(actor, importedUser2GroupDTO.getId());

	}

	private User2GroupDTO getMrRed2GroupGreenDTO(UserDTO actor) throws CRUDException {
		User2GroupDTO user2groupDTO = new User2GroupDTO();
		user2groupDTO.setDtoA(UserCRUDServiceImpl.i().get(actor, InitialData.i().userDTO_MrRed.getId()));
		user2groupDTO.setDtoB(GroupCRUDServiceImpl.i().get(actor, InitialData.i().groupDTO_Green.getId()));
		return user2groupDTO;
	}

}
