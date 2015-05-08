package com.hotelorga.foundation.iface.test;

import org.junit.Assert;
import org.junit.Test;

import com.hotelorga.foundation.iface.datatype.Page;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.iface.dto.GroupDTO;
import com.hotelorga.foundation.iface.dto.GroupFetchQuery;
import com.hotelorga.foundation.impl.GroupCRUDServiceImpl;
import com.hotelorga.foundation.impl.initialdata.InitialData;

public class CRUDGroupTest {

	@Test
	public void test() throws Exception {

		InitialData initialData = InitialData.i();

		Assert.assertNotNull(initialData.groupDTO_Blue);

	}

	@Test
	public void testCreateWithGivenID() throws Exception {

		String groupIdAsString = "121212";
		GroupDTO dto = new GroupDTO(groupIdAsString);

		dto.setName(new SingleLineString256Type("yellow asdf"));

		int groupCount = GroupCRUDServiceImpl.i().fetchList(InitialData.i().initialAdmin, new Page(), new GroupFetchQuery()).size();
		GroupCRUDServiceImpl.i().updadd(InitialData.i().initialAdmin, dto);

		GroupDTO persistedDTO = GroupCRUDServiceImpl.i().get(InitialData.i().initialAdmin, dto.getId());

		Assert.assertEquals(groupIdAsString, persistedDTO.getId() + "");

		int groupCountAfterCreation = GroupCRUDServiceImpl.i().fetchList(InitialData.i().initialAdmin, new Page(), new GroupFetchQuery()).size();

		Assert.assertEquals(groupCount + 1, groupCountAfterCreation);

	}
}
