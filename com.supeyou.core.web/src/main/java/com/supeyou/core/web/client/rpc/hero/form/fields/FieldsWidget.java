package com.supeyou.core.web.client.rpc.hero.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.web.client.rpc.hero.form.fields.field.FieldWidget;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.datatype.types.URLType;
import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;
import com.supeyou.crudie.web.client.fields.base.Field;
import com.supeyou.crudie.web.client.fields.types.FieldForBoolean;
import com.supeyou.crudie.web.client.fields.types.FieldForSingleLineString256Type;
import com.supeyou.crudie.web.client.fields.types.FieldForURLType;

public class FieldsWidget extends WidgetView {

	private final HeroDTO thisDto;

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	public FieldsWidget(HeroDTO dto) {

		this.thisDto = dto;

		put(

				"Hero active",

				new FieldForBoolean(thisDto.getActive()) {

					@Override
					public void onHasChanged(Boolean value) {
						thisDto.setActive(value);
						hasChanged();
					}

				}

		);

		put(

				"Name",

				new FieldForSingleLineString256Type(thisDto.getName()) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setName(value);
						hasChanged();
					}

				}

		);

		put(

				"PaypalAccount",

				new FieldForSingleLineString256Type(thisDto.getPaypalAccount()) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setPaypalAccount(value);
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

		put(

				"Image",

				new FieldForSingleLineString256Type(thisDto.getImageURL()) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setImageURL(value);
						hasChanged();
					}

				}

		);

		put(

				"WebsiteURL",

				new FieldForURLType(thisDto.getWebsiteURL()) {

					@Override
					public void onHasChanged(URLType value) {
						thisDto.setWebsiteURL(value);
						hasChanged();
					}

				}

		);

		put(

				"VideoURL",

				new FieldForURLType(thisDto.getVideoURL()) {

					@Override
					public void onHasChanged(URLType value) {
						thisDto.setVideoURL(value);
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
