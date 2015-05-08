package com.hotelorga.foundation.web.client.fields.types;

import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.web.client.resources.i18n.Text;

public class FieldForSingleLineString256Type extends AbstrTextBoxField<SingleLineString256Type> {

	public FieldForSingleLineString256Type() {
		super(null, false);
	}

	public FieldForSingleLineString256Type(SingleLineString256Type emailAddress) {
		super(emailAddress, false);
	}

	public FieldForSingleLineString256Type(SingleLineString256Type emailAddress, boolean focused) {
		super(emailAddress, focused);
	}

	@Override
	protected boolean valid(String value) {
		try {

			new SingleLineString256Type(value);
			return true;

		} catch (Exception e) {

			return false;

		}
	}

	protected String getWrongFormatMessage() {
		// this should never be shown:
		return Text.i.FIELD_WRONG_FORMAT_SinleLineText();
	}

	@Override
	protected SingleLineString256Type string2value(String string) {

		return new SingleLineString256Type(string);

	}

	@Override
	protected String value2String(SingleLineString256Type value) {
		if (value == null) {
			return "";
		} else {
			return value.value();
		}
	}

	@Override
	public void onHasChanged(SingleLineString256Type value) {
		// can be overwritten

	}

	public void setPasswordType(boolean passwordType) {
		if (passwordType) {
			this.getElement().setAttribute("type", "password");
		} else {
			this.getElement().setAttribute("type", "");

		}
	}

}
