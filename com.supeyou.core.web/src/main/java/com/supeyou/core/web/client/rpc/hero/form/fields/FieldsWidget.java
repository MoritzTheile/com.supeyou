package com.supeyou.core.web.client.rpc.hero.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.web.client.rpc.hero.form.fields.field.FieldWidget;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;
import com.supeyou.crudie.web.client.fields.base.Field;
import com.supeyou.crudie.web.client.fields.types.FieldForSingleLineString256Type;

public class FieldsWidget extends WidgetView {

	private final HeroDTO thisDto;

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	public FieldsWidget(HeroDTO dto) {

		this.thisDto = dto;

		put(

				"Hero",

				new FieldForSingleLineString256Type(thisDto.getName(), true) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setName(value);
						hasChanged();
					}

				}

		);

		put(

				"Bemerkung",

				new FieldForSingleLineString256Type(thisDto.getComment()) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setComment(value);
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
