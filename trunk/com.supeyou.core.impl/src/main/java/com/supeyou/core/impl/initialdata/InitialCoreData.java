package com.supeyou.core.impl.initialdata;

import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.impl.HeroCRUDServiceImpl;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.UserCRUDServiceImpl;

public class InitialCoreData {

	public UserDTO admin;

	public HeroDTO hero1;
	public HeroDTO hero2;
	public HeroDTO hero3;
	public HeroDTO hero4;

	private void init() throws CRUDException {

		admin = UserCRUDServiceImpl.i().getInitialAdmin();
		hero1 = createHero("Tara McCartney", "./heroPics/TaraMcCartney.jpg", "http://unitedforhope.com", "Dedicate her skills to empower India’s rural poor.");
		hero2 = createHero("Hero 002", "", "", "");
		hero3 = createHero("Hero 003", "", "", "");
		hero4 = createHero("Hero 004", "", "", "");

	}

	private HeroDTO createHero(String name, String imageURL, String websiteURL, String comment) throws CRUDException {

		HeroDTO dto = new HeroDTO();
		dto.setName(new SingleLineString256Type(name));
		dto.setImageURL(new SingleLineString256Type(imageURL));
		dto.setWebsiteURL(new SingleLineString256Type(websiteURL));
		dto.setComment(new SingleLineString256Type(comment));
		return HeroCRUDServiceImpl.i().updadd(admin, dto);

	}

	// --- SINGLETON ---
	private static InitialCoreData instance = null;

	public static InitialCoreData i() throws CRUDException {
		if (instance == null) {
			instance = new InitialCoreData();
			instance.init();
		}
		return instance;
	}
}
