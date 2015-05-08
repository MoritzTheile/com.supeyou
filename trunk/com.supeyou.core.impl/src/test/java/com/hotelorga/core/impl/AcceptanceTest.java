package com.hotelorga.core.impl;

import org.junit.Assert;
import org.junit.Test;

import com.hotelorga.core.iface.dto.AcceptanceDTO;
import com.hotelorga.core.impl.initialdata.InitialCoreData;
import com.hotelorga.foundation.impl.UserCRUDServiceImpl;

public class AcceptanceTest {

	@Test
	public void testFetch() throws Exception {

		AcceptanceDTO dto = AcceptanceCRUDServiceImpl.i().get(UserCRUDServiceImpl.i().getInitialAdmin(), InitialCoreData.i().acceptanceBlue1.getId());
		Assert.assertEquals(InitialCoreData.cityName, dto.getTmpName().value());

	}

}
