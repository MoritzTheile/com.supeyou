package com.supeyou.core.web.client.fields.types;

import java.util.Arrays;
import java.util.List;

import com.supeyou.core.iface.datatype.enums.GROUPROLE;
import com.supeyou.crudie.web.client.fields.types.AbstrListBoxField;

public class FieldForGroupRole extends AbstrListBoxField<GROUPROLE> {

	public FieldForGroupRole(GROUPROLE defaultRole) {
		super(defaultRole, false);
	}

	public FieldForGroupRole(boolean focused, GROUPROLE defaultRole) {
		super(defaultRole, focused);
	}

	@Override
	public String value2displayName(GROUPROLE value) {
		if (GROUPROLE.CHILD.equals(value)) {
			return "Kind";
		}
		if (GROUPROLE.MOTHER.equals(value)) {
			return "Mutter";
		}
		if (GROUPROLE.FATHER.equals(value)) {
			return "Vater";
		}
		if (GROUPROLE.OTHER.equals(value)) {
			return "sonst.";
		}
		if (GROUPROLE.NO_ROLE.equals(value)) {
			return "- - -";
		}

		return value.name();
	}

	@Override
	public List<GROUPROLE> getValues() {
		return Arrays.asList(GROUPROLE.values());
	}

}
