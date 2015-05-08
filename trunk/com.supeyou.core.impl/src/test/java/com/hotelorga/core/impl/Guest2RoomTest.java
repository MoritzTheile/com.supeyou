package com.hotelorga.core.impl;

import org.junit.Assert;
import org.junit.Test;

import com.hotelorga.core.iface.dto.Guest2RoomDTO;
import com.hotelorga.core.iface.dto.Guest2RoomFetchQuery;
import com.hotelorga.core.impl.initialdata.InitialCoreData;
import com.hotelorga.foundation.iface.datatype.Page;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.iface.dto.DTOFetchList;

public class Guest2RoomTest {

	@Test
	public void testFetch() throws Exception {

		{
			Guest2RoomFetchQuery query = new Guest2RoomFetchQuery();
			DTOFetchList<Guest2RoomDTO> list = Guest2RoomCRUDServiceImpl.i().fetchList(InitialCoreData.i().admin, new Page(), query);
			Assert.assertEquals(11, list.size());
		}

		{
			Guest2RoomFetchQuery query = new Guest2RoomFetchQuery();
			query.setIdB(InitialCoreData.i().room1.getId());
			DTOFetchList<Guest2RoomDTO> list = Guest2RoomCRUDServiceImpl.i().fetchList(InitialCoreData.i().admin, new Page(), query);
			Assert.assertEquals(3, list.size());
		}
		{
			Guest2RoomFetchQuery query = new Guest2RoomFetchQuery();
			query.setIdB(InitialCoreData.i().room1.getId());
			query.setFrom(new DateType("2015-04-11"));
			query.setTo(new DateType("2015-04-15"));
			DTOFetchList<Guest2RoomDTO> list = Guest2RoomCRUDServiceImpl.i().fetchList(InitialCoreData.i().admin, new Page(), query);
			Assert.assertEquals(2, list.size());
		}
		{
			Guest2RoomFetchQuery query = new Guest2RoomFetchQuery();
			query.setFrom(new DateType("2015-04-15"));
			query.setTo(new DateType("2015-04-27"));
			DTOFetchList<Guest2RoomDTO> list = Guest2RoomCRUDServiceImpl.i().fetchList(InitialCoreData.i().admin, new Page(), query);
			Assert.assertEquals(6, list.size());
		}

	}

}
