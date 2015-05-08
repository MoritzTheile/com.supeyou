package com.supeyou.core.web.client.rpc.guest2room.form.fields.field;

import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;

public class FieldWidget extends WidgetView {

	public FieldWidget(String name, AbstrCompositeField<?> field) {
		fieldLabel.setText(name);
		fieldSlot.add(field);
	}
}
