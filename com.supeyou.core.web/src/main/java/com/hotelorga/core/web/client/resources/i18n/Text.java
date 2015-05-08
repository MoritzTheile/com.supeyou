package com.hotelorga.core.web.client.resources.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;
import com.hotelorga.core.web.client.resources.i18n.Text;

public interface Text extends Messages {

	public final static Text i = (Text) GWT.create(Text.class);

	String MULTIUSE_Version();

	String FIELD_WRONG_FORMAT_EmailAddress();

	String FIELD_WRONG_FORMAT_Double();

	String FIELD_WRONG_FORMAT_SinleLineText();

	String FIELD_WRONG_FORMAT_MultiLineText();

}