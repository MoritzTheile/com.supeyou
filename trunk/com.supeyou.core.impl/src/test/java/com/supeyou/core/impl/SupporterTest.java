package com.supeyou.core.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;

import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterFetchQuery;
import com.supeyou.core.impl.initialdata.InitialCoreData;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.dto.DTOFetchList;

public class SupporterTest {

	@Test
	public void test() throws CRUDException {
		assertNotNull(InitialCoreData.i().admin);

		SupporterFetchQuery dtoQuery = new SupporterFetchQuery();
		dtoQuery.setInvitor(InitialCoreData.i().supporter_Hugo);
		DTOFetchList<SupporterDTO> fetchList = SupporterCRUDServiceImpl.i().fetchList(InitialCoreData.i().admin, new Page(), dtoQuery);
		for (SupporterDTO supporterDTO : fetchList) {
			System.out.println(supporterDTO.getTmpHeroName() + "");
		}
		Assert.assertEquals(3, fetchList.size());

	}

}
