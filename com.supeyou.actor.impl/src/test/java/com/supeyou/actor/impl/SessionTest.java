package com.supeyou.actor.impl;

import org.junit.Assert;
import org.junit.Test;

import com.supeyou.actor.impl.initialdata.InitialActorData;
import com.supeyou.crudie.iface.datatype.CRUDException;

public class SessionTest {

	@Test
	public void test() throws CRUDException {

		Assert.assertNotNull(InitialActorData.i().session1);
	}

}
