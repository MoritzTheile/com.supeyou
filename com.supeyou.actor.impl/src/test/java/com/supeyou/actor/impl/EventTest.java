package com.supeyou.actor.impl;

import org.junit.Assert;
import org.junit.Test;

import com.supeyou.actor.iface.dto.EventDTO;
import com.supeyou.actor.iface.dto.Session2EventDTO;
import com.supeyou.actor.impl.initialdata.InitialActorData;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.impl.initialdata.InitialData;

public class EventTest {

	@Test
	public void test() throws CRUDException {

		EventDTO eventDTO = new EventDTO();

		String testValue = "testValue";

		eventDTO.setAction(new SingleLineString256Type("testAction"));
		eventDTO.setCategory(new SingleLineString256Type("testCategory"));
		eventDTO.setValue(new SingleLineString256Type(testValue));

		Session2EventDTO session2EventDTO = Session2EventCRUDServiceImpl.i().addEventToSession(InitialData.i().initialAdmin, InitialActorData.i().session1.getId(), eventDTO);

		Assert.assertNotNull(session2EventDTO.getId());
		Assert.assertNotNull(session2EventDTO.getDtoA());
		Assert.assertNotNull(session2EventDTO.getDtoB());
		Assert.assertEquals(testValue, session2EventDTO.getDtoB().getValue().value());

	}
}
