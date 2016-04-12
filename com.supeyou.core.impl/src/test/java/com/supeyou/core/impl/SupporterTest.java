package com.supeyou.core.impl;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
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

	@Test
	public void testb() throws CRUDException {

		assertNotNull(InitialCoreData.i().admin);

		List<SupporterDTO> supporterInPathToRoot = SupporterCRUDServiceImpl.i().getSupporterInPathToRoot(InitialCoreData.i().admin, InitialCoreData.i().supporter_Eugen);

		Assert.assertEquals(5, supporterInPathToRoot.size());

		Assert.assertTrue(supporterInPathToRoot.contains(InitialCoreData.i().supporter_Eugen));
		Assert.assertTrue(supporterInPathToRoot.contains(InitialCoreData.i().supporter_Andrea));
		Assert.assertTrue(supporterInPathToRoot.contains(InitialCoreData.i().supporter_Hermann));
		Assert.assertTrue(supporterInPathToRoot.contains(InitialCoreData.i().supporter_Hugo));
		Assert.assertTrue(supporterInPathToRoot.contains(InitialCoreData.i().rootSupporter_Martina));

	}

}
