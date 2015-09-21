package com.supeyou.actor.web.client.rpc.event.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.supeyou.actor.iface.dto.EventDTO;
import com.supeyou.actor.web.client.rpc.event.form.fields.field.FieldWidget;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;
import com.supeyou.crudie.web.client.fields.base.Field;
import com.supeyou.crudie.web.client.fields.types.FieldForSingleLineString256Type;

public class FieldsWidget extends WidgetView {

	private final EventDTO thisDto;

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	public FieldsWidget(EventDTO dto) {

		this.thisDto = dto;

		put(

				"Action",

				new FieldForSingleLineString256Type(thisDto.getAction(), true) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setAction(value);
						hasChanged();
					}

				}

		);

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
