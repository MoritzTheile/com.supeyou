package com.supeyou.core.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;

import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterFetchQuery;
import com.supeyou.core.impl.initialdata.InitialCoreData;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.dto.DTOFetchList;

public class SupporterTest {

	@Test
	public void test() throws CRUDException {

		assertNotNull(InitialCoreData.i().admin);

		Invitation2SupporterFetchQuery dtoQuery = new Invitation2SupporterFetchQuery();

		dtoQuery.setInvitor(InitialCoreData.i().supporter_Hugo);

		DTOFetchList<Invitation2SupporterDTO> fetchList = Invitation2SupporterCRUDServiceImpl.i().fetchList(InitialCoreData.i().admin, new Page(), dtoQuery);

		for (Invitation2SupporterDTO dto : fetchList) {

			System.out.println(dto.getDtoB()

					.getUserDTO()

					.getLoginId() + "");

		}

		Assert.assertEquals(3, fetchList.size());

	}
}
