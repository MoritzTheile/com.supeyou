package com.hotelorga.core.impl;

import org.junit.Assert;
import org.junit.Test;

import com.hotelorga.core.iface.datatype.enums.GROUPROLE;
import com.hotelorga.core.iface.dto.Guest2GuestGroupDTO;
import com.hotelorga.core.iface.dto.Guest2GuestGroupFetchQuery;
import com.hotelorga.core.impl.initialdata.InitialCoreData;
import com.hotelorga.foundation.iface.datatype.CRUDException;
import com.hotelorga.foundation.iface.datatype.Page;
import com.hotelorga.foundation.iface.dto.DTOFetchList;

public class Guest2GuestGroupTest {

	@Test
	public void testFetch() throws Exception {

		{
			Guest2GuestGroupFetchQuery query = new Guest2GuestGroupFetchQuery();
			DTOFetchList<Guest2GuestGroupDTO> list = Guest2GuestGroupCRUDServiceImpl.i().fetchList(InitialCoreData.i().admin, new Page(), query);
			Assert.assertTrue(list.size() > 0);
		}

		{
			DTOFetchList<Guest2GuestGroupDTO> list = getGuestGroupsOfFather();

			Assert.assertEquals(1, list.size());

			Assert.assertEquals(GROUPROLE.FATHER, list.iterator().next().getGroupRole());

		}

	}

	@Test
	public void testOneToN() throws Exception {

		Guest2GuestGroupDTO dto = new Guest2GuestGroupDTO();
		dto.setDtoA(InitialCoreData.i().guestDTO_FatherBlue);
		dto.setDtoB(InitialCoreData.i().guestGroupDTO_olive);
		dto.setGroupRole(GROUPROLE.MOTHER);
		// there should be an Exception because father can't be in two GuestGroups at a time
		Guest2GuestGroupCRUDServiceImpl.i().updadd(InitialCoreData.i().admin, dto);

		{
			DTOFetchList<Guest2GuestGroupDTO> list = getGuestGroupsOfFather();
			Assert.assertEquals(1, list.size());

			Assert.assertEquals(GROUPROLE.MOTHER, list.iterator().next().getGroupRole());

		}

	}

	private DTOFetchList<Guest2GuestGroupDTO> getGuestGroupsOfFather() throws CRUDException {
		Guest2GuestGroupFetchQuery query = new Guest2GuestGroupFetchQuery();

		query.setIdA(InitialCoreData.i().guestDTO_FatherBlue.getId());

		DTOFetchList<Guest2GuestGroupDTO> list = Guest2GuestGroupCRUDServiceImpl.i().fetchList(InitialCoreData.i().admin, new Page(), query);
		return list;
	}

}
