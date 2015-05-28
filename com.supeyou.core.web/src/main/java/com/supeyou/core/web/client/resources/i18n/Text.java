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

	String BUTTON_Invite();

	String INVITE_Text_HTML();

}