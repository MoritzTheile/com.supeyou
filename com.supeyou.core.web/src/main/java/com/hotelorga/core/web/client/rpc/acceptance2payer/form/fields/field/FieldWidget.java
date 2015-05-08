package com.hotelorga.core.web.client.rpc.acceptance2payer.form.fields.field;

import com.hotelorga.foundation.web.client.fields.base.AbstrCompositeField;

public class FieldWidget extends WidgetView {

	public FieldWidget(String name, AbstrCompositeField<?> field) {
		fieldLabel.setText(name);
		fieldSlot.add(field);
	}
}
