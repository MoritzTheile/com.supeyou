package com.supeyou.core.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.supeyou.core.impl.initialdata.InitialCoreData;
import com.supeyou.crudie.iface.datatype.CRUDException;

public class SupporterTest {

	@Test
	public void test() throws CRUDException {
		assertNotNull(InitialCoreData.i().mTheile);

	}

}
