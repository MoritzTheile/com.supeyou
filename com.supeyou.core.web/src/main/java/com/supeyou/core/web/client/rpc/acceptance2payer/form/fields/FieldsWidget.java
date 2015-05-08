package com.supeyou.core.web.client.rpc.acceptance2payer.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.Acceptance2PayerDTO;
import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.PayerDTO;
import com.supeyou.core.web.client.rpc.acceptance.Statics;
import com.supeyou.core.web.client.rpc.acceptance2payer.form.fields.field.FieldWidget;
import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;
import com.supeyou.crudie.web.client.fields.base.Field;
import com.supeyou.crudie.web.client.fields.types.AbstrDTOField;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class FieldsWidget extends WidgetView {

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	private final Acceptance2PayerDTO thisDTO;

	public FieldsWidget(Acceptance2PayerDTO dto, boolean showAssoAField, boolean showAssoBField) {

		thisDTO = dto;

		put(

				"Name",

				new AbstrDTOField<AcceptanceDTO>(thisDTO.getDtoA()) {

					@Override
					public void onHasChanged(AcceptanceDTO value) {
						thisDTO.setDtoA(value);
						hasChanged();
					}

					PopupWidget popup;

					@Override
					protected void showChooser(final ChooserAction<AcceptanceDTO> chooserAction) {
						popup = new PopupWidget(new com.supeyou.core.web.client.rpc.acceptance.choosersmall.ChooserSmallWidget(new SelectionListener<AcceptanceDTO>() {

							@Override
							public void onHasChanged(List<AcceptanceDTO> selection) {

								for (AcceptanceDTO dto : selection) {
									chooserAction.chosen(dto);
									break;
								}

								popup.closePopup();
							}

						}, true), true);

						popup.setPosition(this.getAbsoluteTop() - 4, this.getAbsoluteLeft());

					}

					@Override
					public Widget getWidget(AcceptanceDTO dto) {

						return new Label(Statics.renderDescription(thisDTO.getDtoA()));
					}

				}

		);

		put(

				"Kostenträger",

				new AbstrDTOField<PayerDTO>(thisDTO.getDtoB()) {

					@Override
					public void onHasChanged(PayerDTO value) {
						thisDTO.setDtoB(value);
						hasChanged();
					}

					PopupWidget popup;

					@Override
					protected void showChooser(final ChooserAction<PayerDTO> chooserAction) {
						popup = new PopupWidget(new com.supeyou.core.web.client.rpc.payer.choosersmall.ChooserSmallWidget(new SelectionListener<PayerDTO>() {

							@Override
							public void onHasChanged(List<PayerDTO> selection) {

								for (PayerDTO dto : selection) {
									chooserAction.chosen(dto);
									break;
								}

								popup.closePopup();
							}

						}), true);

						popup.setPosition(this.getAbsoluteTop() - 4, this.getAbsoluteLeft());

					}

					@Override
					public Widget getWidget(PayerDTO dto) {
						String label = "- - -";
						if (dto != null && dto.getName() != null) {
							label = dto.getName().value();
						}
						return new Label(label);
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
