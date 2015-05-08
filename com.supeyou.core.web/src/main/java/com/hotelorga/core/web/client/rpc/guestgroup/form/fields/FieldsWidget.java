package com.hotelorga.core.web.client.rpc.guestgroup.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.hotelorga.core.iface.dto.GuestGroupDTO;
import com.hotelorga.core.web.client.rpc.guestgroup.form.fields.field.FieldWidget;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.web.client.fields.base.AbstrCompositeField;
import com.hotelorga.foundation.web.client.fields.base.Field;
import com.hotelorga.foundation.web.client.fields.types.FieldForSingleLineString256Type;

public class FieldsWidget extends WidgetView {

	private final GuestGroupDTO thisDto;

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	public FieldsWidget(GuestGroupDTO dto) {

		this.thisDto = dto;

		put(

				"Rechnungsgruppe",

				new FieldForSingleLineString256Type(thisDto.getName(), true) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setName(value);
						hasChanged();
					}

				}

		);

		if (dto.getId() != null) {

			FlowPanel formColumn = new FlowPanel();

			formColumn.add(new Label("Gast"));
			formColumn.add(new com.hotelorga.core.web.client.rpc.guest2guestgroup.assoaeditor.AssoAEditorWidget(thisDto, false));

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
