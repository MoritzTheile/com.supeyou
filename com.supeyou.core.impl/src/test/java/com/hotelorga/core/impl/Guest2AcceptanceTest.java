package com.hotelorga.core.impl;

import org.junit.Assert;
import org.junit.Test;

import com.hotelorga.core.iface.dto.Guest2AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2AcceptanceFetchQuery;
import com.hotelorga.core.impl.initialdata.InitialCoreData;
import com.hotelorga.foundation.iface.datatype.Page;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.iface.dto.DTOFetchList;

public class Guest2AcceptanceTest {

	@Test
	public void testFetch() throws Exception {

		{
			Guest2AcceptanceFetchQuery query = new Guest2AcceptanceFetchQuery();
			DTOFetchList<Guest2AcceptanceDTO> list = Guest2AcceptanceCRUDServiceImpl.i().fetchList(InitialCoreData.i().admin, new Page(), query);
			Assert.assertEquals(6, list.size());
		}

		{
			Guest2AcceptanceFetchQuery query = new Guest2AcceptanceFetchQuery();
			query.setIdB(InitialCoreData.i().acceptanceBlue2.getId());
			DTOFetchList<Guest2AcceptanceDTO> list = Guest2AcceptanceCRUDServiceImpl.i().fetchList(InitialCoreData.i().admin, new Page(), query);
			Assert.assertEquals(4, list.size());
		}
		{
			Guest2AcceptanceFetchQuery query = new Guest2AcceptanceFetchQuery();
			query.setIdB(InitialCoreData.i().acceptanceBlue2.getId());
			query.setFrom(new DateType("2015-04-11"));
			query.setTo(new DateType("2015-04-15"));
			DTOFetchList<Guest2AcceptanceDTO> list = Guest2AcceptanceCRUDServiceImpl.i().fetchList(InitialCoreData.i().admin, new Page(), query);
			Assert.assertEquals(4, list.size());
		}

	}

}
