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

	public HeroDTO hero1;
	public HeroDTO hero2;
	public HeroDTO hero3;
	public HeroDTO hero4;

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
	public InvitationDTO invitation_0205_0734;

	private void init() throws CRUDException {

		admin = UserCRUDServiceImpl.i().getInitialAdmin();
		hero1 = createHero("Moritz Theile", "./heroPics/MoritzTheile.jpg", "http://mtheile.com", "SupeYou Developer");
		hero2 = createHero("Martina Fuchs", "./heroPics/MartinaFuchs.jpg", "https://www.xing.com/profile/Martina_Fuchs60", "SupeYou Coach");
		hero3 = createHero("Nikolaus Teixeira", "./heroPics/NikolausTeixeira.jpg", "http://willkommen-in-muenchen.de", "Engagiert sich für münchner Flüchtlinge");
		hero4 = createHero("Tara McCartney", "./heroPics/TaraMcCartney.jpg", "http://unitedforhope.org", "Dedicates her skills to empower India’s rural poor.");

		supporter_Hugo = createSupporter("Hugo");
		supporter_Andrea = createSupporter("Andrea");
		supporter_Manfred = createSupporter("Manfred");
		supporter_Otto = createSupporter("Otto");
		supporter_Emma = createSupporter("Emma");
		supporter_Hermann = createSupporter("Hermann");
		supporter_Marie = createSupporter("Marie");
		supporter_Gunnar = createSupporter("Gunnar");
		supporter_Christian = createSupporter("Christian");
		supporter_Herbert = createSupporter("Herbert");
		supporter_Eugen = createSupporter("Eugen");
		supporter_Thomas = createSupporter("Thomas");
		supporter_Melanie = createSupporter("Melanie");
		supporter_Marion = createSupporter("Marion");

		invitation_0105_1945 = createInvitation("0105_1945");
		createSupporter2Invitation(supporter_Hugo, invitation_0105_1945);
		createInvitation2Supporter(invitation_0105_1945, supporter_Andrea);
		createInvitation2Supporter(invitation_0105_1945, supporter_Manfred);

		invitation_0105_1948 = createInvitation("0105_1948");
		createSupporter2Invitation(supporter_Hugo, invitation_0105_1948);
		createInvitation2Supporter(invitation_0105_1948, supporter_Otto);

		invitation_0105_2225 = createInvitation("0105_2225");
		createSupporter2Invitation(supporter_Andrea, invitation_0105_2225);
		createInvitation2Supporter(invitation_0105_2225, supporter_Emma);

		invitation_0105_2232 = createInvitation("0105_2232");
		createSupporter2Invitation(supporter_Andrea, invitation_0105_2232);
		createInvitation2Supporter(invitation_0105_2232, supporter_Hermann);

		invitation_0205_1045 = createInvitation("0205_1045");
		createSupporter2Invitation(supporter_Manfred, invitation_0205_1045);

		invitation_0205_1243 = createInvitation("0205_1243");
		createSupporter2Invitation(supporter_Otto, invitation_0205_1243);
		createInvitation2Supporter(invitation_0205_1243, supporter_Marie);

		invitation_0205_1245 = createInvitation("0205_1245");
		createSupporter2Invitation(supporter_Otto, invitation_0205_1245);
		createInvitation2Supporter(invitation_0205_1245, supporter_Gunnar);
		createInvitation2Supporter(invitation_0205_1245, supporter_Christian);
		createInvitation2Supporter(invitation_0205_1245, supporter_Herbert);

		invitation_0205_1248 = createInvitation("0205_1248");
		createSupporter2Invitation(supporter_Otto, invitation_0205_1248);

		invitation_0105_2322 = createInvitation("0105_2322");
		createSupporter2Invitation(supporter_Hermann, invitation_0105_2322);
		createInvitation2Supporter(invitation_0105_2322, supporter_Eugen);

		invitation_0305_1948 = createInvitation("0305_1948");
		createSupporter2Invitation(supporter_Gunnar, invitation_0305_1948);
		createInvitation2Supporter(invitation_0305_1948, supporter_Thomas);

		invitation_0405_0945 = createInvitation("0405_0945");
		createSupporter2Invitation(supporter_Gunnar, invitation_0405_0945);

		invitation_0205_0734 = createInvitation("0205_0734");
		createSupporter2Invitation(supporter_Eugen, invitation_0205_0734);
		createInvitation2Supporter(invitation_0205_0734, supporter_Melanie);
		createInvitation2Supporter(invitation_0205_0734, supporter_Marion);

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

	private SupporterDTO createSupporter(String string) throws CRUDException {

		SupporterDTO supporterDTO = new SupporterDTO();
		supporterDTO.setComment(new SingleLineString256Type(string));
		return SupporterCRUDServiceImpl.i().updadd(admin, supporterDTO);

	}

	private InvitationDTO createInvitation(String string) throws CRUDException {

		InvitationDTO invitationDTO = new InvitationDTO();
		invitationDTO.setComment(new SingleLineString256Type(string));
		return InvitationCRUDServiceImpl.i().updadd(admin, invitationDTO);

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
