package com.supeyou.crudie.web.client.rpc.user.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.crudie.iface.datatype.enums.LOCALE;
import com.supeyou.crudie.iface.datatype.enums.ROLE;
import com.supeyou.crudie.iface.datatype.types.AmountType;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;
import com.supeyou.crudie.web.client.fields.base.Field;
import com.supeyou.crudie.web.client.fields.types.FieldForAmountType;
import com.supeyou.crudie.web.client.fields.types.FieldForBoolean;
import com.supeyou.crudie.web.client.fields.types.FieldForDateType;
import com.supeyou.crudie.web.client.fields.types.FieldForLocale;
import com.supeyou.crudie.web.client.fields.types.FieldForRoles;
import com.supeyou.crudie.web.client.fields.types.FieldForSingleLineString256Type;
import com.supeyou.crudie.web.client.resources.i18n.Text;
import com.supeyou.crudie.web.client.rpc.user.form.fields.field.FieldWidget;
import com.supeyou.crudie.web.client.rpc.user2group.assobeditor.AssoBEditorWidget;

public class FieldsWidget extends WidgetView {

	private final UserDTO thisDto;

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	public FieldsWidget(UserDTO dto) {

		this.thisDto = dto;

		put(

				Text.i.FIELD_NAME_LoginId(),

				new FieldForSingleLineString256Type(thisDto.getLoginId(), true) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setLoginId(value);
						hasChanged();
					}

				}

		);

		put(

				Text.i.FIELD_NAME_Active(),

				new FieldForBoolean(thisDto.getActive()) {

					@Override
					public void onHasChanged(Boolean value) {
						thisDto.setActive(value);
						hasChanged();
					}

				}

		);
		put(

				Text.i.FIELD_NAME_Locale(),

				new FieldForLocale(thisDto.getLocale()) {

					@Override
					public void onHasChanged(LOCALE value) {
						thisDto.setLocale(value);
						hasChanged();
					}

				}

		);

		put(

				Text.i.FIELD_NAME_Birthdate(),

				new FieldForDateType(thisDto.getBirthday()) {

					@Override
					public void onHasChanged(DateType value) {
						thisDto.setBirthday(value);
						hasChanged();
					}

				}

		);

		put(

				Text.i.FIELD_NAME_Amount(),

				new FieldForAmountType(thisDto.getAmount()) {

					@Override
					public void onHasChanged(AmountType value) {
						thisDto.setAmount(value);
						hasChanged();
					}

				}

		);

		ROLE defaultRole = null;

		if (dto.getRoles().size() > 0) {
			defaultRole = dto.getRoles().iterator().next();
		}

		put(

				Text.i.FIELD_NAME_Roles(),
				new FieldForRoles(defaultRole) {
					@Override
					public void onHasChanged(ROLE value) {
						List<ROLE> list = new ArrayList<>();
						list.add(value);
						thisDto.setRoles(list);
						hasChanged();
					}
				}

		);

		if (dto.getId() != null) {

			FlowPanel formColumn = new FlowPanel();

			formColumn.add(new Label(Text.i.FIELD_NAME_Groups()));
			formColumn.add(new AssoBEditorWidget(thisDto, false));

			formRoot.add(formColumn);
		}

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
