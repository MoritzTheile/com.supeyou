package com.supeyou.core.web.client.resources.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface Text extends Messages {

	public final static Text i = (Text) GWT.create(Text.class);

	String MULTIUSE_Version();

	String FIELD_WRONG_FORMAT_EmailAddress();

	String FIELD_WRONG_FORMAT_Double();

	String FIELD_WRONG_FORMAT_SinleLineText();

	String FIELD_WRONG_FORMAT_MultiLineText();

	String ADDHERO_Text_HTML();

	String LANDINGPAGE_Text_HTML();

	String HEROSPAGE_ChooseHero();

	String HEROSPAGE_JoinAsHero();

	String HEROSPAGE_Admin();

	String BUTTON_Donate();

	String BUTTON_Invite();

	String DONATE_HeaderLabel();

	String INVITE_HeaderLabel();

	String DONATE_Text_HTML();

	String DONATE_Text2_HTML();

	String INVITE_Text_HTML();

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

	String HOWITWORKS_Text_HTML();

	String HEROVIDEO_Header();

}