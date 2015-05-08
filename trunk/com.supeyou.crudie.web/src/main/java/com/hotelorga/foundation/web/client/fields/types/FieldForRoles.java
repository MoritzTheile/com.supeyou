package com.hotelorga.foundation.web.client.fields.types;

import java.util.Arrays;
import java.util.List;

import com.hotelorga.foundation.iface.datatype.enums.ROLE;

public class FieldForRoles extends AbstrListBoxField<ROLE> {

	public FieldForRoles(ROLE defaultRole) {
		super(defaultRole, false);
	}

	public FieldForRoles(boolean focused, ROLE defaultRole) {
		super(defaultRole, focused);
	}

	@Override
	public String value2displayName(ROLE value) {
		return value.name();
	}

	@Override
	public List<ROLE> getValues() {
		return Arrays.asList(ROLE.values());
	}

}
