package com.supeyou.core.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;

import com.supeyou.core.iface.dto.Hero2SupporterDTO;
import com.supeyou.core.iface.dto.Hero2SupporterFetchQuery;
import com.supeyou.core.iface.dto.Supporter2DonationDTO;
import com.supeyou.core.iface.dto.Supporter2DonationFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.User2HeroDTO;
import com.supeyou.core.iface.dto.User2HeroFetchQuery;
import com.supeyou.core.iface.dto.User2SupporterDTO;
import com.supeyou.core.iface.dto.User2SupporterFetchQuery;
import com.supeyou.core.impl.initialdata.InitialCoreData;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.datatype.types.AmountType;
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

		// checking if a hero has an user or other way around

		User2HeroFetchQuery user2HeroFetchQuery = new User2HeroFetchQuery();

		user2HeroFetchQuery.setIdA(InitialCoreData.i().user_Tara.getId());

		DTOFetchList<User2HeroDTO> user2heroFetchList = User2HeroCRUDServiceImpl.i().fetchList(InitialCoreData.i().admin, new Page(), user2HeroFetchQuery);

		Assert.assertEquals(1, user2heroFetchList.size());

		Assert.assertEquals(13, SupporterCRUDServiceImpl.i().get(InitialCoreData.i().admin, InitialCoreData.i().supporter_Hugo.getId()).getDecendentCount());
		Assert.assertEquals(5, SupporterCRUDServiceImpl.i().get(InitialCoreData.i().admin, InitialCoreData.i().supporter_Andrea.getId()).getDecendentCount());
		Assert.assertEquals(3, SupporterCRUDServiceImpl.i().get(InitialCoreData.i().admin, InitialCoreData.i().supporter_Hermann.getId()).getDecendentCount());
		Assert.assertEquals(2, SupporterCRUDServiceImpl.i().get(InitialCoreData.i().admin, InitialCoreData.i().supporter_Eugen.getId()).getDecendentCount());
		Assert.assertEquals(0, SupporterCRUDServiceImpl.i().get(InitialCoreData.i().admin, InitialCoreData.i().supporter_Marion.getId()).getDecendentCount());

		// Checking donations
		Assert.assertNotNull(InitialCoreData.i().donationDTO_Andrea1Euro.getId());

		Supporter2DonationFetchQuery dtoQuery = new Supporter2DonationFetchQuery();
		dtoQuery.setIdA(InitialCoreData.i().supporter_Hermann.getId());
		DTOFetchList<Supporter2DonationDTO> dtoFetchList = Supporter2DonationCRUDServiceImpl.i().fetchList(InitialCoreData.i().admin, new Page(), dtoQuery);

		Assert.assertEquals(2, dtoFetchList.size());

		AmountType donationAmount = SupporterCRUDServiceImpl.i().calculateDonationAmount(InitialCoreData.i().admin, InitialCoreData.i().supporter_Hermann);

		Assert.assertEquals(new Integer(200), donationAmount.value());

	}

}
