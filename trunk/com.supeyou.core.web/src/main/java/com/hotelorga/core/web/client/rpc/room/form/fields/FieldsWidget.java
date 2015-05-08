package com.hotelorga.core.web.client.rpc.room.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.hotelorga.core.iface.dto.RoomDTO;
import com.hotelorga.core.web.client.fields.types.FieldForPositivIntegerType;
import com.hotelorga.core.web.client.rpc.room.form.fields.field.FieldWidget;
import com.hotelorga.foundation.iface.datatype.types.PositivIntegerType;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.web.client.fields.base.AbstrCompositeField;
import com.hotelorga.foundation.web.client.fields.base.Field;
import com.hotelorga.foundation.web.client.fields.types.FieldForSingleLineString256Type;
import com.hotelorga.foundation.web.client.resources.i18n.Text;

public class FieldsWidget extends WidgetView {

	private final RoomDTO thisDto;

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	public FieldsWidget(RoomDTO dto) {

		this.thisDto = dto;

		put(

				Text.i.FIELD_NAME_RoomName(),

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

		put(

				"Betten",

				new FieldForPositivIntegerType(thisDto.getNumberOfBeds()) {

					@Override
					public void onHasChanged(PositivIntegerType value) {
						thisDto.setNumberOfBeds(value);
						hasChanged();
					}

				}

		);

		// if (dto.getId() != null) {
		//
		// FlowPanel formColumn = new FlowPanel();
		//
		// formColumn.add(new Label("Belegung"));
		// formColumn.add(new com.hotelorga.core.web.client.rpc.guest2room.assoaeditor.AssoAEditorWidget(thisDto, true));
		//
		// formRoot.add(formColumn);
		// }

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
