package com.hotelorga.core.web.client.fields.types;

import java.util.Arrays;
import java.util.List;

import com.hotelorga.core.iface.datatype.enums.MONTH;
import com.hotelorga.foundation.web.client.fields.types.AbstrListBoxField;

public class FieldForMonth extends AbstrListBoxField<MONTH> {

	public FieldForMonth(MONTH defaultRole) {
		super(defaultRole, false);
	}

	public FieldForMonth(boolean focused, MONTH defaultRole) {
		super(defaultRole, focused);
	}

	@Override
	public String value2displayName(MONTH value) {
		return value.label;
	}

	@Override
	public List<MONTH> getValues() {
		return Arrays.asList(MONTH.values());
	}

}
