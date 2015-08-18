package com.supeyou.crudie.web.client.resources.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface Text extends Messages {

	public final static Text i = (Text) GWT.create(Text.class);

	String MULTIUSE_Version();

	String MULTIUSE_Cancel();

	String MULTIUSE_Delete();

	String MULTIUSE_Create();

	String MULTIUSE_Save();

	String FIELD_WRONG_FORMAT_EmailAddress();

	String FIELD_WRONG_FORMAT_Double();

	String FIELD_WRONG_FORMAT_SinleLineText();

	String FIELD_WRONG_FORMAT_MultiLineText();

	String CRUD_CreateNewElement();

	String FIELD_NAME_Email();

	String FIELD_NAME_Active();

	String FIELD_NAME_Birthdate();

	String FIELD_NAME_Amount();

	String FIELD_NAME_Roles();

	String QUERY_PAGESIZE_Five();

	String QUERY_PAGESIZE_Ten();

	String QUERY_PAGESIZE_TwentyFive();

	String QUERY_PAGESIZE_Fifty();

	String QUERY_PAGESIZE_OneHundred();

	String QUERY_COLUMN_EMAILADDRESS();

	String QUERY_COLUMN_BIRTHDATE();

	String QUERY_COLUMN_AMOUNT();

	String QUERY_COLUMN_ACTIVE();

	String PAGER_Of();

	String PAGER_To();

	String PAGER_NoHits();

	String FIELD_NAME_Group();

	String FIELD_NAME_Groups();

	String FIELD_NAME_User();

	String FIELD_NAME_LoginId();

	String FIELD_NAME_Locale();

	String FIELD_NAME_Name();

	String LINK_Export();

	String LINK_Import();

	String IMPRESSUM_HTML();

}