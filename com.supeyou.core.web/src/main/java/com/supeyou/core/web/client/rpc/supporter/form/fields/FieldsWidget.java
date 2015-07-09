package com.supeyou.core.web.client.rpc.supporter.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.rpc.supporter.form.fields.field.FieldWidget;
import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;
import com.supeyou.crudie.web.client.fields.base.Field;

public class FieldsWidget extends WidgetView {

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	public FieldsWidget(SupporterDTO dto) {

	}

	protected void put(String name, AbstrCompositeField<?> field) {
		fields.add(field);
		formRoot.add(new FieldWidget(name, field));
	}

	public boolean isDirty() {
		for (Field<?> field : fields) {
			if (field.isDirty()) {
				return true;
			}
		}
		return false;
	}

	public void hasChanged() {
		// can be overwritten
	}

}
