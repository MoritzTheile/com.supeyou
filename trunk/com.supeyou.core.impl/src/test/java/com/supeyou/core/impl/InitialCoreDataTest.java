package com.supeyou.core.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;

import com.supeyou.core.iface.dto.Hero2SupporterDTO;
import com.supeyou.core.iface.dto.Hero2SupporterFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.User2SupporterDTO;
import com.supeyou.core.iface.dto.User2SupporterFetchQuery;
import com.supeyou.core.impl.initialdata.InitialCoreData;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.dto.DTOFetchList;
import com.supeyou.crudie.iface.dto.UserDTO;

public class InitialCoreDataTest {

	@Test
	public void checkUser2SupporterAssos() throws CRUDException {

		assertNotNull(InitialCoreData.i().admin);

		User2SupporterFetchQuery user2SupporterFetchQuery = new User2SupporterFetchQuery();

		user2SupporterFetchQuery.setIdA(InitialCoreData.i().user_Hugo.getId());

		DTOFetchList<User2SupporterDTO> fetchList = User2SupporterCRUDServiceImpl.i().fetchList(InitialCoreData.i().admin, new Page(), user2SupporterFetchQuery);

		// Hugo supports one Hero
		Assert.assertEquals(1, fetchList.size());
		SupporterDTO supporterDTO = SupporterCRUDServiceImpl.i().get(InitialCoreData.i().admin, fetchList.iterator().next().getDtoB().getId());

		Assert.assertNotNull(supporterDTO);

		Assert.assertNotNull(supporterDTO.getUserDTO());

		Hero2SupporterFetchQuery hero2SupporterFetchQuery = new Hero2SupporterFetchQuery();
		hero2SupporterFetchQuery.setIdB(supporterDTO.getId());
		DTOFetchList<Hero2SupporterDTO> hero2SupporterFetchList = Hero2SupporterCRUDServiceImpl.i().fetchList(InitialCoreData.i().admin, new Page(), hero2SupporterFetchQuery);
		Assert.assertEquals(1, hero2SupporterFetchList.size());

		// going back from supporter to user
		User2SupporterFetchQuery user2SupporterFetchQuery_byUser = new User2SupporterFetchQuery();
		user2SupporterFetchQuery_byUser.setIdB(supporterDTO.getId());
		DTOFetchList<User2SupporterDTO> user2SupporterFetchList_byUser = User2SupporterCRUDServiceImpl.i().fetchList(InitialCoreData.i().admin, new Page(), user2SupporterFetchQuery_byUser);
		Assert.assertEquals(1, user2SupporterFetchList_byUser.size());

		UserDTO reloadedHugo = user2SupporterFetchList_byUser.iterator().next().getDtoA();

		System.out.println(reloadedHugo.getLoginId());

	}

}
