package com.hotelorga.core.web.client.rpc.payer.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.hotelorga.core.iface.dto.PayerDTO;
import com.hotelorga.core.web.client.rpc.payer.form.fields.field.FieldWidget;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.web.client.fields.base.AbstrCompositeField;
import com.hotelorga.foundation.web.client.fields.base.Field;
import com.hotelorga.foundation.web.client.fields.types.FieldForSingleLineString256Type;

public class FieldsWidget extends WidgetView {

	private final PayerDTO thisDto;

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	public FieldsWidget(PayerDTO dto) {

		this.thisDto = dto;

		put(

				"Kostenträger",

				new FieldForSingleLineString256Type(thisDto.getName(), true) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						thisDto.setName(value);
						hasChanged();
					}

				}

		);

		// put(
		//
		// "Ansprechpartner",
		//
		// new FieldForSingleLineString256Type(thisDto.getConfideTo()) {
		//
		// @Override
		// public void onHasChanged(SingleLineString256Type value) {
		// thisDto.setConfideTo(value);
		// hasChanged();
		// }
		//
		// }
		//
		// );
		//
		// put(
		//
		// "Name",
		//
		// new FieldForSingleLineString256Type(thisDto.getBillAddrName()) {
		//
		// @Override
		// public void onHasChanged(SingleLineString256Type value) {
		// thisDto.setBillAddrName(value);
		// hasChanged();
		// }
		//
		// }
		//
		// );
		//
		// put(
		//
		// "Strasse",
		//
		// new FieldForSingleLineString256Type(thisDto.getBillAddrStreet()) {
		//
		// @Override
		// public void onHasChanged(SingleLineString256Type value) {
		// thisDto.setBillAddrStreet(value);
		// hasChanged();
		// }
		//
		// }
		//
		// );
		//
		// put(
		//
		// "ZIP",
		//
		// new FieldForSingleLineString256Type(thisDto.getBillAddrPostalCode()) {
		//
		// @Override
		// public void onHasChanged(SingleLineString256Type value) {
		// thisDto.setBillAddrPostalCode(value);
		// hasChanged();
		// }
		//
		// }
		//
		// );
		//
		// put(
		//
		// "Ort",
		//
		// new FieldForSingleLineString256Type(thisDto.getBillAddrCity()) {
		//
		// @Override
		// public void onHasChanged(SingleLineString256Type value) {
		// thisDto.setBillAddrCity(value);
		// hasChanged();
		// }
		//
		// }
		//
		// );
		//
		// put(
		//
		// "Bemerkung",
		//
		// new FieldForSingleLineString256Type(thisDto.getComment()) {
		//
		// @Override
		// public void onHasChanged(SingleLineString256Type value) {
		// thisDto.setComment(value);
		// hasChanged();
		// }
		//
		// }
		//
		// );

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
