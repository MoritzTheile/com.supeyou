package com.supeyou.core.web.client.resources.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface Text extends Messages {

	public final static Text i = (Text) GWT.create(Text.class);

	String MULTIUSE_Version();

	String MULTIUSE_Save();

	String FIELD_WRONG_FORMAT_EmailAddress();

	String FIELD_WRONG_FORMAT_Double();

	String FIELD_WRONG_FORMAT_SinleLineText();

	String FIELD_WRONG_FORMAT_MultiLineText();

	String ADDHERO_Text_HTML();

	String LANDINGPAGE_Text_HTML();

	String LANDINGPAGEINVIT_Text_HTML();

	String LANDINGPAGE_ItsAGame_HTML();

	String LANDINGPAGE_TextTwo_HTML();

	String HEROSPAGE_FollowInvitation();

	String HEROSPAGE_ChooseHero();

	String HEROSPAGE_JoinAsHero();

	String HEROSPAGE_Admin();

	String BUTTON_Donate();

	String BUTTON_Invite();

	String DONATE_HeaderLabel();

	String INVITE_HeaderLabel();

	String DONATE_Text_HTML();

	String DONATE_Text2_HTML();

	String DONATE_MoreOptions();

	String DONATE_OPTION_DonateOnce();

	String DONATE_OPTION_Donate1Monthly();

	String DONATE_OPTION_Donate5Monthly();

	String DONATE_OPTION_Donate10Monthly();

	String DONATE_OPTION_Donate25Monthly();

	String DONATE_OPTION_Donate50Monthly();

	String INVITE_LinkHintLabel();

	String INVITE_LinkNameLabel();

	String INVITE_ShareWithMail();

	String INVITE_ShareWithWhatsApp();

	String INVITE_ShareWithGoogle();

	String INVITE_ShareWithFacebook();

	String INVITE_ShareWithTwitter();

	String MISSION_Text_HTML();

	String EUROSYMBOL();

	String THATS_YOU();

	String YOUR_GENERATED_RESULT();

	String YOUR_RESULT();

	String HEROVIDEO_Header();

	String INVITE_HOWTOINVITE_Header_HTML();

	String INVITE_SINGLEGROUPCHOOSER_Header_HTML();

	String INVITE_SINGLEGROUPCHOOSER_Single_HTML();

	String INVITE_SINGLEGROUPCHOOSER_Group_HTML();

	String INVITE_SINGLE_NameQuestion();

	String INVITE_SINGLE_CallToChoose();

	String INVITE_SINGLE_Email();

	String INVITE_SINGLE_WhatsApp();

	String INVITE_SINGLE_CutAndPaste();

	String INVITE_GROUP_NameQuestion();

	String INVITE_GROUP_CallToChoose();

	String INVITE_GROUP_Facebook();

	String INVITE_GROUP_GooglePlus();

	String INVITE_GROUP_Twitter();

	String INVITE_GROUP_CutAndPaste();

	String TREE_WasSupportingAlready();

	String INVITE_MANUALLY_COPYANDPASTE_Text1_HTML();

	String INVITE_MANUALLY_COPYANDPASTE_Text2_HTML();

	String INVITE_MANUALLY_COPYANDPASTE_Text3_HTML();

	String ASK_FOR_EMAIL_Header();

	String ASK_FOR_EMAIL_Text_HTML();

	String ASK_FOR_NAME_Header();

	String ASK_FOR_EMAIL_Name_HTML();

	String DONTBREAKINVIT1();

	String DONTBREAKINVIT2();

	String PLEASE_DONATE();

}