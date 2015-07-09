package com.supeyou.core.impl.initialdata;

import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.Supporter2InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.impl.HeroCRUDServiceImpl;
import com.supeyou.core.impl.Invitation2SupporterCRUDServiceImpl;
import com.supeyou.core.impl.InvitationCRUDServiceImpl;
import com.supeyou.core.impl.Supporter2InvitationCRUDServiceImpl;
import com.supeyou.core.impl.SupporterCRUDServiceImpl;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.UserCRUDServiceImpl;

public class InitialCoreData {

	public UserDTO admin;

	public UserDTO user_Moritz;
	public UserDTO user_Martina;
	public UserDTO user_Nikolaus;
	public UserDTO user_Tara;

	public HeroDTO hero_Moritz;
	public HeroDTO hero_Martina;
	public HeroDTO hero_Nikolaus;
	public HeroDTO hero_Tara;

	public UserDTO user_Hugo;
	public UserDTO user_Andrea;
	public UserDTO user_Manfred;
	public UserDTO user_Otto;
	public UserDTO user_Emma;
	public UserDTO user_Hermann;
	public UserDTO user_Marie;
	public UserDTO user_Gunnar;
	public UserDTO user_Christian;
	public UserDTO user_Herbert;
	public UserDTO user_Eugen;
	public UserDTO user_Thomas;
	public UserDTO user_Melanie;
	public UserDTO user_Marion;

	public SupporterDTO supporter_Hugo;
	// public SupporterDTO supporter_Andrea;
	// public SupporterDTO supporter_Manfred;
	// public SupporterDTO supporter_Otto;
	// public SupporterDTO supporter_Emma;
	// public SupporterDTO supporter_Hermann;
	// public SupporterDTO supporter_Marie;
	// public SupporterDTO supporter_Gunnar;
	// public SupporterDTO supporter_Christian;
	// public SupporterDTO supporter_Herbert;
	// public SupporterDTO supporter_Eugen;
	// public SupporterDTO supporter_Thomas;
	// public SupporterDTO supporter_Melanie;
	// public SupporterDTO supporter_Marion;

	public InvitationDTO invitation_0105_1945;
	public InvitationDTO invitation_0105_1948;
	public InvitationDTO invitation_0105_2225;
	public InvitationDTO invitation_0105_2232;
	public InvitationDTO invitation_0205_1045;
	public InvitationDTO invitation_0205_1243;
	public InvitationDTO invitation_0205_1245;
	public InvitationDTO invitation_0205_1248;
	public InvitationDTO invitation_0105_2322;
	public InvitationDTO invitation_0305_1948;
	public InvitationDTO invitation_0405_0945;
	public InvitationDTO invitation_0205_0734;

	private void init() throws CRUDException {

		admin = UserCRUDServiceImpl.i().getInitialAdmin();

		user_Moritz = createUser("moritz@mtheile.com");
		user_Martina = createUser("martina@mtheile.com");
		user_Nikolaus = createUser("nikolaus@mtheile.com");
		user_Tara = createUser("tara@mtheile.com");
		user_Hugo = createUser("hugo@mtheile.com");
		user_Andrea = createUser("andrea@mtheile.com");
		user_Manfred = createUser("manfred@mtheile.com");
		user_Otto = createUser("otto@mtheile.com");
		user_Emma = createUser("emma@mtheile.com");
		user_Hermann = createUser("hermann@mtheile.com");
		user_Marie = createUser("marie@mtheile.com");
		user_Gunnar = createUser("gunnar@mtheile.com");
		user_Christian = createUser("christian@mtheile.com");
		user_Herbert = createUser("herbert@mtheile.com");
		user_Eugen = createUser("eugen@mtheile.com");
		user_Thomas = createUser("thomas@mtheile.com");
		user_Melanie = createUser("melanie@mtheile.com");
		user_Marion = createUser("marion@mtheile.com");

		hero_Moritz = createHero(user_Moritz, "./heroPics/MoritzTheile.jpg", "http://mtheile.com", "SupeYou Developer");
		hero_Martina = createHero(user_Martina, "./heroPics/MartinaFuchs.jpg", "https://www.xing.com/profile/Martina_Fuchs60", "SupeYou Coach");
		hero_Nikolaus = createHero(user_Nikolaus, "./heroPics/NikolausTeixeira.jpg", "http://willkommen-in-muenchen.de", "Engagiert sich für münchner Flüchtlinge");
		hero_Tara = createHero(user_Tara, "./heroPics/TaraMcCartney.jpg", "http://unitedforhope.org", "Dedicates her skills to empower India’s rural poor.");

		supporter_Hugo = createSupporter(user_Hugo, hero_Martina);
		// supporter_Andrea = createSupporter("Andrea");
		// supporter_Manfred = createSupporter("Manfred");
		// supporter_Otto = createSupporter("Otto");
		// supporter_Emma = createSupporter("Emma");
		// supporter_Hermann = createSupporter("Hermann");
		// supporter_Marie = createSupporter("Marie");
		// supporter_Gunnar = createSupporter("Gunnar");
		// supporter_Christian = createSupporter("Christian");
		// supporter_Herbert = createSupporter("Herbert");
		// supporter_Eugen = createSupporter("Eugen");
		// supporter_Thomas = createSupporter("Thomas");
		// supporter_Melanie = createSupporter("Melanie");
		// supporter_Marion = createSupporter("Marion");

		invitation_0105_1945 = createInvitation("0105_1945");
		// createSupporter2Invitation(supporter_Hugo, invitation_0105_1945);
		// createInvitation2Supporter(invitation_0105_1945, supporter_Andrea);
		// createInvitation2Supporter(invitation_0105_1945, supporter_Manfred);
		//
		// invitation_0105_1948 = createInvitation("0105_1948");
		// createSupporter2Invitation(supporter_Hugo, invitation_0105_1948);
		// createInvitation2Supporter(invitation_0105_1948, supporter_Otto);
		//
		// invitation_0105_2225 = createInvitation("0105_2225");
		// createSupporter2Invitation(supporter_Andrea, invitation_0105_2225);
		// createInvitation2Supporter(invitation_0105_2225, supporter_Emma);
		//
		// invitation_0105_2232 = createInvitation("0105_2232");
		// createSupporter2Invitation(supporter_Andrea, invitation_0105_2232);
		// createInvitation2Supporter(invitation_0105_2232, supporter_Hermann);
		//
		// invitation_0205_1045 = createInvitation("0205_1045");
		// createSupporter2Invitation(supporter_Manfred, invitation_0205_1045);
		//
		// invitation_0205_1243 = createInvitation("0205_1243");
		// createSupporter2Invitation(supporter_Otto, invitation_0205_1243);
		// createInvitation2Supporter(invitation_0205_1243, supporter_Marie);
		//
		// invitation_0205_1245 = createInvitation("0205_1245");
		// createSupporter2Invitation(supporter_Otto, invitation_0205_1245);
		// createInvitation2Supporter(invitation_0205_1245, supporter_Gunnar);
		// createInvitation2Supporter(invitation_0205_1245, supporter_Christian);
		// createInvitation2Supporter(invitation_0205_1245, supporter_Herbert);
		//
		// invitation_0205_1248 = createInvitation("0205_1248");
		// createSupporter2Invitation(supporter_Otto, invitation_0205_1248);
		//
		// invitation_0105_2322 = createInvitation("0105_2322");
		// createSupporter2Invitation(supporter_Hermann, invitation_0105_2322);
		// createInvitation2Supporter(invitation_0105_2322, supporter_Eugen);
		//
		// invitation_0305_1948 = createInvitation("0305_1948");
		// createSupporter2Invitation(supporter_Gunnar, invitation_0305_1948);
		// createInvitation2Supporter(invitation_0305_1948, supporter_Thomas);
		//
		// invitation_0405_0945 = createInvitation("0405_0945");
		// createSupporter2Invitation(supporter_Gunnar, invitation_0405_0945);
		//
		// invitation_0205_0734 = createInvitation("0205_0734");
		// createSupporter2Invitation(supporter_Eugen, invitation_0205_0734);
		// createInvitation2Supporter(invitation_0205_0734, supporter_Melanie);
		// createInvitation2Supporter(invitation_0205_0734, supporter_Marion);

	}

	private UserDTO createUser(String name) throws CRUDException {
		UserDTO userDTO = new UserDTO();
		userDTO.setLoginId(new SingleLineString256Type(name));
		return UserCRUDServiceImpl.i().updadd(admin, userDTO);
	}

	private void createInvitation2Supporter(InvitationDTO a, SupporterDTO b) throws CRUDException {
		Invitation2SupporterDTO asso = new Invitation2SupporterDTO();
		asso.setDtoA(a);
		asso.setDtoB(b);
		Invitation2SupporterCRUDServiceImpl.i().updadd(admin, asso);
	}

	private void createSupporter2Invitation(SupporterDTO a, InvitationDTO b) throws CRUDException {
		Supporter2InvitationDTO asso = new Supporter2InvitationDTO();
		asso.setDtoA(a);
		asso.setDtoB(b);
		Supporter2InvitationCRUDServiceImpl.i().updadd(admin, asso);
	}

	private SupporterDTO createSupporter(UserDTO userDTO, HeroDTO heroDTO) throws CRUDException {

		return SupporterCRUDServiceImpl.i().getOrCreate(admin, userDTO, heroDTO);

	}

	private InvitationDTO createInvitation(String string) throws CRUDException {

		InvitationDTO invitationDTO = new InvitationDTO();
		invitationDTO.setComment(new SingleLineString256Type(string));
		return InvitationCRUDServiceImpl.i().updadd(admin, invitationDTO);

	}

	private HeroDTO createHero(UserDTO user, String imageURL, String websiteURL, String comment) throws CRUDException {

		HeroDTO dto = HeroCRUDServiceImpl.i().getOrCreate(admin, user);
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
