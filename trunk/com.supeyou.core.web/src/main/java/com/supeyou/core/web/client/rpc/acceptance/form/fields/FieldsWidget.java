package com.supeyou.core.web.client.rpc.acceptance.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.web.client.fields.types.FieldForPositivIntegerType;
import com.supeyou.core.web.client.rpc.acceptance.form.fields.field.FieldWidget;
import com.supeyou.crudie.iface.datatype.types.AmountType;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.iface.datatype.types.PositivIntegerType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;
import com.supeyou.crudie.web.client.fields.base.Field;
import com.supeyou.crudie.web.client.fields.types.FieldForAmountType;
import com.supeyou.crudie.web.client.fields.types.FieldForDateType;
import com.supeyou.crudie.web.client.fields.types.FieldForSingleLineString256Type;

public class FieldsWidget extends WidgetView {

	private final AcceptanceDTO thisDto;

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	public FieldsWidget(AcceptanceDTO dto) {

		this.thisDto = dto;

		put(

				"Beschreibung",

				new FieldForSingleLineString256Type(thisDto.getComment()) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setComment(value);
						hasChanged();
					}

				}

		);

		put(

				"Von",

				new FieldForDateType(dto.getFromDate()) {

					@Override
					public void onHasChanged(DateType value) {
						thisDto.setFromDate(value);
						hasChanged();
					}

				}

		);

		put(

				"Bis",

				new FieldForDateType(dto.getToDate()) {

					@Override
					public void onHasChanged(DateType value) {
						thisDto.setToDate(value);
						hasChanged();
					}

				}

		);

		put(

				"Kostenübernahme",

				new FieldForAmountType(thisDto.getAcceptedCosts()) {

					@Override
					public void onHasChanged(AmountType value) {
						thisDto.setAcceptedCosts(value);
						hasChanged();
					}

				}

		);

		put(

				"Tage insgesamt",

				new FieldForPositivIntegerType(thisDto.getAcceptedDays()) {

					@Override
					public void onHasChanged(PositivIntegerType value) {
						thisDto.setAcceptedDays(value);
						hasChanged();
					}

				}

		);

		put(

				"Fixe Eigenbeteiligung",

				new FieldForAmountType(thisDto.getFixOwnCosts()) {

					@Override
					public void onHasChanged(AmountType value) {
						thisDto.setFixOwnCosts(value);
						hasChanged();
					}

				}

		);

		if (dto.getId() != null) {

			FlowPanel formColumn = new FlowPanel();

			formColumn.add(new Label("Kostenträger"));

			formColumn.add(new com.supeyou.core.web.client.rpc.acceptance2payer.assobeditor.AssoBEditorWidget(dto, false));

			formRoot.add(formColumn);
		}

		if (dto.getId() != null) {

			FlowPanel formColumn = new FlowPanel();

			formColumn.add(new Label("Gäste"));

			formColumn.add(new com.supeyou.core.web.client.rpc.guest2acceptance.assobeditor.AssoBEditorWidget(dto, false));

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
