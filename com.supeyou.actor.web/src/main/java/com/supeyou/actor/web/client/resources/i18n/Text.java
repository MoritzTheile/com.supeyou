package com.supeyou.actor.web.client.resources.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface Text extends Messages {

	public final static Text i = (Text) GWT.create(Text.class);

	String MULTIUSE_Version();

	String LOGIN_Email();

	String LOGIN_LoginId();

	String LOGIN_Password();

	String LOGIN_BUTTON_Login();

	String LOGIN_BUTTON_Logout();

	String LOGIN_WrongCredentials();

}