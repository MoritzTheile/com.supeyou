package com.supeyou.core.impl.initialdata;

import com.supeyou.core.iface.dto.DonationDTO;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.impl.DonationCRUDServiceImpl;
import com.supeyou.core.impl.HeroCRUDServiceImpl;
import com.supeyou.core.impl.InvitationCRUDServiceImpl;
import com.supeyou.core.impl.SupporterCRUDServiceImpl;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.types.AmountType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.datatype.types.URLType;
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

	public SupporterDTO rootSupporter_Moritz;
	public SupporterDTO rootSupporter_Martina;
	public SupporterDTO rootSupporter_Nikolaus;
	public SupporterDTO rootSupporter_Tara;

	public InvitationDTO rootInvitation_Moritz;
	public InvitationDTO rootInvitation_Martina;
	public InvitationDTO rootInvitation_Nikolaus;
	public InvitationDTO rootInvitation_Tara;

	public SupporterDTO supporter_Hugo;
	public SupporterDTO supporter_Andrea;
	public SupporterDTO supporter_Manfred;
	public SupporterDTO supporter_Otto;
	public SupporterDTO supporter_Emma;
	public SupporterDTO supporter_Hermann;
	public SupporterDTO supporter_Marie;
	public SupporterDTO supporter_Gunnar;
	public SupporterDTO supporter_Christian;
	public SupporterDTO supporter_Herbert;
	public SupporterDTO supporter_Eugen;
	public SupporterDTO supporter_Eugen2ndInvitation;
	public SupporterDTO supporter_Thomas;
	public SupporterDTO supporter_Melanie;
	public SupporterDTO supporter_Marion;

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
	public InvitationDTO invitation_0405_0955;

	public InvitationDTO invitation_0205_0734;

	public DonationDTO donationDTO_Marion2Euro;
	public DonationDTO donationDTO_Eugen1Euro;
	public DonationDTO donationDTO_Hermann1Euro1;
	public DonationDTO donationDTO_Hermann1Euro2;
	public DonationDTO donationDTO_Emma1Euro;
	public DonationDTO donationDTO_Andrea1Euro;

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

		hero_Moritz = createHero(user_Moritz, "Moritz Theile", "paypal.com@moritztheile.de", "./heroPics/MoritzTheile.jpg", "http://mtheile.com", "SupeYou Developer", false);
		hero_Martina = createHero(user_Martina, "Martina Fuchs", "paypal.com@moritztheile.de", "./heroPics/MartinaFuchs.jpg", "http://moeglichkeits-coach.de", "SupeYou Coach", false);
		hero_Nikolaus = createHero(user_Nikolaus, "Nikolaus Teixeira", "paypal.com@moritztheile.de", "./heroPics/NikolausTeixeira.jpg", "http://willkommen-in-muenchen.de", "Engagiert sich für münchner Flüchtlinge", true);
		hero_Tara = createHero(user_Tara, "Tara McCartney", "paypal.com@moritztheile.de", "./heroPics/TaraMcCartney.jpg", "http://unitedforhope.org", "Dedicates her skills to empower India’s rural poor.", false);

		rootSupporter_Moritz = createRootSupporter(hero_Moritz);
		rootSupporter_Martina = createRootSupporter(hero_Martina);
		rootSupporter_Nikolaus = createRootSupporter(hero_Nikolaus);
		rootSupporter_Tara = createRootSupporter(hero_Tara);

		rootInvitation_Moritz = createInvitation("rootInvitation", rootSupporter_Moritz);
		rootInvitation_Martina = createInvitation("rootInvitation", rootSupporter_Martina);
		rootInvitation_Nikolaus = createInvitation("rootInvitation", rootSupporter_Nikolaus);
		rootInvitation_Tara = createInvitation("rootInvitation", rootSupporter_Tara);

		supporter_Hugo = InvitationCRUDServiceImpl.i().followInvitation(admin, user_Hugo, rootInvitation_Martina.getToken());

		invitation_0105_1945 = createInvitation("0105_1945", supporter_Hugo);
		supporter_Andrea = InvitationCRUDServiceImpl.i().followInvitation(admin, user_Andrea, invitation_0105_1945.getToken());
		supporter_Manfred = InvitationCRUDServiceImpl.i().followInvitation(admin, user_Manfred, invitation_0105_1945.getToken());

		invitation_0105_1948 = createInvitation("0105_1948", supporter_Hugo);
		supporter_Otto = InvitationCRUDServiceImpl.i().followInvitation(admin, user_Otto, invitation_0105_1948.getToken());

		invitation_0105_2225 = createInvitation("0105_2225", supporter_Andrea);
		supporter_Emma = InvitationCRUDServiceImpl.i().followInvitation(admin, user_Emma, invitation_0105_2225.getToken());

		invitation_0105_2232 = createInvitation("0105_2232", supporter_Andrea);
		supporter_Hermann = InvitationCRUDServiceImpl.i().followInvitation(admin, user_Hermann, invitation_0105_2232.getToken());

		invitation_0205_1045 = createInvitation("0205_1045", supporter_Manfred);

		invitation_0205_1243 = createInvitation("0205_1243", supporter_Otto);
		supporter_Marie = InvitationCRUDServiceImpl.i().followInvitation(admin, user_Marie, invitation_0205_1243.getToken());

		invitation_0205_1245 = createInvitation("0205_1245", supporter_Otto);
		supporter_Gunnar = InvitationCRUDServiceImpl.i().followInvitation(admin, user_Gunnar, invitation_0205_1245.getToken());
		supporter_Christian = InvitationCRUDServiceImpl.i().followInvitation(admin, user_Christian, invitation_0205_1245.getToken());
		supporter_Herbert = InvitationCRUDServiceImpl.i().followInvitation(admin, user_Herbert, invitation_0205_1245.getToken());

		invitation_0205_1248 = createInvitation("0205_1248", supporter_Otto);

		invitation_0105_2322 = createInvitation("0105_2322", supporter_Hermann);
		supporter_Eugen = InvitationCRUDServiceImpl.i().followInvitation(admin, user_Eugen, invitation_0105_2322.getToken());

		invitation_0305_1948 = createInvitation("0305_1948", supporter_Gunnar);
		supporter_Thomas = InvitationCRUDServiceImpl.i().followInvitation(admin, user_Thomas, invitation_0305_1948.getToken());

		invitation_0405_0945 = createInvitation("0405_0945", supporter_Gunnar);

		invitation_0405_0955 = createInvitation("0405_0945", supporter_Herbert);

		invitation_0205_0734 = createInvitation("0205_0734", supporter_Eugen);
		supporter_Melanie = InvitationCRUDServiceImpl.i().followInvitation(admin, user_Melanie, invitation_0205_0734.getToken());
		supporter_Marion = InvitationCRUDServiceImpl.i().followInvitation(admin, user_Marion, invitation_0205_0734.getToken());

		// tree destroying edges:
		supporter_Eugen2ndInvitation = InvitationCRUDServiceImpl.i().followInvitation(admin, user_Eugen, invitation_0105_2232.getToken());
		InvitationCRUDServiceImpl.i().followInvitation(admin, user_Marie, invitation_0105_2232.getToken());
		InvitationCRUDServiceImpl.i().followInvitation(admin, user_Otto, invitation_0405_0955.getToken());

		{
			DonationDTO donationDTO = new DonationDTO();
			donationDTO.setPaymentAmount(new AmountType(200));
			donationDTO_Marion2Euro = DonationCRUDServiceImpl.i().save(admin, supporter_Marion, donationDTO);

		}
		{
			DonationDTO donationDTO = new DonationDTO();
			donationDTO.setPaymentAmount(new AmountType(100));
			donationDTO_Eugen1Euro = DonationCRUDServiceImpl.i().save(admin, supporter_Eugen, donationDTO);

		}
		{
			DonationDTO donationDTO = new DonationDTO();
			donationDTO.setPaymentAmount(new AmountType(100));
			donationDTO_Hermann1Euro1 = DonationCRUDServiceImpl.i().save(admin, supporter_Hermann, donationDTO);

		}
		{
			DonationDTO donationDTO = new DonationDTO();
			donationDTO.setPaymentAmount(new AmountType(100));
			donationDTO_Hermann1Euro2 = DonationCRUDServiceImpl.i().save(admin, supporter_Hermann, donationDTO);
		}
		{
			DonationDTO donationDTO = new DonationDTO();
			donationDTO.setPaymentAmount(new AmountType(100));
			donationDTO_Emma1Euro = DonationCRUDServiceImpl.i().save(admin, supporter_Emma, donationDTO);

		}
		{
			DonationDTO donationDTO = new DonationDTO();
			donationDTO.setPaymentAmount(new AmountType(100));
			donationDTO_Andrea1Euro = DonationCRUDServiceImpl.i().save(admin, supporter_Andrea, donationDTO);

		}

	}

	private UserDTO createUser(String name) throws CRUDException {
		UserDTO userDTO = new UserDTO();
		userDTO.setLoginId(new SingleLineString256Type(name));
		userDTO.setAuthToken(new SingleLineString256Type((name.toLowerCase().replaceAll("[^a-zA-Z0-9]", "") + "adfasdfasdf").substring(0, 6)));
		System.out.println("UserId=" + userDTO.getLoginId() + " AuthToken=" + userDTO.getAuthToken());
		return UserCRUDServiceImpl.i().updadd(admin, userDTO);
	}

	private SupporterDTO createRootSupporter(HeroDTO heroDTO) throws CRUDException {

		return SupporterCRUDServiceImpl.i().getOrCreateRootSupporter(admin, heroDTO);

	}

	private InvitationDTO createInvitation(String string, SupporterDTO supporterDTO) throws CRUDException {

		InvitationDTO invitationDTO = InvitationCRUDServiceImpl.i().create(admin, supporterDTO);
		invitationDTO.setComment(new SingleLineString256Type(string));
		return InvitationCRUDServiceImpl.i().updadd(admin, invitationDTO);

	}

	private HeroDTO createHero(UserDTO user, String name, String paypalAccount, String imageURL, String websiteURL, String comment, boolean hidden) throws CRUDException {

		HeroDTO dto = HeroCRUDServiceImpl.i().getOrCreate(admin, user);
		dto.setImageURL(new SingleLineString256Type(imageURL));
		dto.setWebsiteURL(new URLType(websiteURL));
		dto.setVideoURL(new URLType("https://www.youtube.com/embed/2P87NS63K94"));
		dto.setComment(new SingleLineString256Type(comment));
		dto.setPaypalAccount(new SingleLineString256Type(paypalAccount));
		dto.setName(new SingleLineString256Type(name));
		dto.setActive(hidden);
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
