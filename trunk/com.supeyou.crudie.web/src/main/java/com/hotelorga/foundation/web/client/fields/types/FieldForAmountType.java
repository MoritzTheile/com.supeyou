package com.hotelorga.foundation.web.client.fields.types;

import com.hotelorga.foundation.iface.common.HELPER;
import com.hotelorga.foundation.iface.datatype.types.AmountType;
import com.hotelorga.foundation.web.client.resources.GWTSTATICS;

public class FieldForAmountType extends AbstrTextBoxField<AmountType> {

	public FieldForAmountType(AmountType value) {
		super(value, false);
	}

	public FieldForAmountType(boolean focused, AmountType value) {
		super(value, focused);
	}

	@Override
	protected boolean valid(String value) {

		if (GWTSTATICS.euro2cent(value) != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	protected String getWrongFormatMessage() {

		return "codemarker=z892z89";

	}

	@Override
	protected AmountType string2value(String string) {

		return new AmountType(GWTSTATICS.euro2cent(string));
	}

	@Override
	protected String value2String(AmountType value) {

		return renderString(value);

	}

	public static String renderString(AmountType value) {
		if (value == null) {
			return null;
		}
		return HELPER.cent2euro(value.value());
	}

}
