package com.hotelorga.core.web.client.fields.types;

import java.util.Arrays;
import java.util.List;

import com.hotelorga.core.iface.datatype.enums.TITLE;
import com.hotelorga.foundation.web.client.fields.types.AbstrListBoxField;

public class FieldForTitle extends AbstrListBoxField<TITLE> {

	public FieldForTitle(TITLE defaultRole) {
		super(defaultRole, false);
	}

	public FieldForTitle(boolean focused, TITLE defaultRole) {
		super(defaultRole, focused);
	}

	@Override
	public String value2displayName(TITLE value) {
		return value.name();
	}

	@Override
	public List<TITLE> getValues() {
		return Arrays.asList(TITLE.values());
	}

}
