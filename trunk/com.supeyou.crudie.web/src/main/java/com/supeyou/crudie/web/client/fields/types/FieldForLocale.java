package com.supeyou.crudie.web.client.fields.types;

import java.util.Arrays;
import java.util.List;

import com.supeyou.crudie.iface.datatype.enums.LOCALE;

public class FieldForLocale extends AbstrListBoxField<LOCALE> {

	public FieldForLocale(LOCALE defaultRole) {
		super(defaultRole, false);
	}

	public FieldForLocale(boolean focused, LOCALE defaultRole) {
		super(defaultRole, focused);
	}

	@Override
	public String value2displayName(LOCALE value) {
		return value.name();
	}

	@Override
	public List<LOCALE> getValues() {
		return Arrays.asList(LOCALE.values());
	}

}
