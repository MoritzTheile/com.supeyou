package com.supeyou.core.web.client.rpc.supporter2donation.form.fields.field;

import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;

public class FieldWidget extends WidgetView {

	public FieldWidget(String name, AbstrCompositeField<?> field) {
		fieldLabel.setText(name);
		fieldSlot.add(field);
	}
}
