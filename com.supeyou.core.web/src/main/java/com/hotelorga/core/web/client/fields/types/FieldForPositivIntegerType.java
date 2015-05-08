package com.hotelorga.core.web.client.fields.types;

import com.hotelorga.foundation.iface.datatype.types.PositivIntegerType;
import com.hotelorga.foundation.web.client.fields.types.AbstrTextBoxField;

public class FieldForPositivIntegerType extends AbstrTextBoxField<PositivIntegerType> {

	public FieldForPositivIntegerType(PositivIntegerType value) {
		super(value, false);
	}

	public FieldForPositivIntegerType(boolean focused, PositivIntegerType value) {
		super(value, focused);
	}

	@Override
	protected boolean valid(String value) {

		try {

			new PositivIntegerType(Integer.parseInt(value));

			return true;

		} catch (Exception e) {

			return false;

		}
	}

	@Override
	protected String getWrongFormatMessage() {

		return "codemarker=z2452z9";

	}

	@Override
	protected PositivIntegerType string2value(String string) {

		return new PositivIntegerType(Integer.parseInt(string));
	}

	@Override
	protected String value2String(PositivIntegerType value) {

		if (value == null) {
			return null;
		}
		return value.value() + "";
	}

}
