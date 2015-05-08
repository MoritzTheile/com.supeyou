package com.hotelorga.core.web.client.rpc.guest2acceptance.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.core.iface.dto.AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2AcceptanceDTO;
import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.web.client.rpc.acceptance.Statics;
import com.hotelorga.core.web.client.rpc.guest.chooserlarge.item.ItemWidget;
import com.hotelorga.core.web.client.rpc.guest2acceptance.form.fields.field.FieldWidget;
import com.hotelorga.foundation.web.client.fields.base.AbstrCompositeField;
import com.hotelorga.foundation.web.client.fields.base.Field;
import com.hotelorga.foundation.web.client.fields.types.AbstrDTOField;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class FieldsWidget extends WidgetView {

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	private final Guest2AcceptanceDTO thisDTO;

	public FieldsWidget(final Guest2AcceptanceDTO dto, boolean showAssoAField, boolean showAssoBField) {

		thisDTO = dto;

		if (showAssoAField) {
			put(

					"Gast",

					new AbstrDTOField<GuestDTO>(thisDTO.getDtoA()) {

						@Override
						public void onHasChanged(GuestDTO value) {
							thisDTO.setDtoA(value);
							hasChanged();
						}

						PopupWidget popup;

						@Override
						protected void showChooser(final ChooserAction<GuestDTO> chooserAction) {
							popup = new PopupWidget(new com.hotelorga.core.web.client.rpc.guest.choosersmall.ChooserSmallWidget(new SelectionListener<GuestDTO>() {

								@Override
								public void onHasChanged(List<GuestDTO> selection) {

									for (GuestDTO dto : selection) {
										chooserAction.chosen(dto);
										break;
									}

									popup.closePopup();
								}

							}), true);

							popup.setPosition(this.getAbsoluteTop() - 4, this.getAbsoluteLeft());

						}

						@Override
						public Widget getWidget(GuestDTO dto) {

							return new Label(ItemWidget.renderHeaderString(dto));

						}

					}

			);
		}
		if (showAssoBField) {

			put(

					"Kostenuebernahme",

					new AbstrDTOField<AcceptanceDTO>(thisDTO.getDtoB()) {

						@Override
						public void onHasChanged(AcceptanceDTO value) {
							thisDTO.setDtoB(value);
							hasChanged();
						}

						PopupWidget popup;

						@Override
						protected void showChooser(final ChooserAction<AcceptanceDTO> chooserAction) {
							popup = new PopupWidget(new com.hotelorga.core.web.client.rpc.acceptance.choosersmall.ChooserSmallWidget(new SelectionListener<AcceptanceDTO>() {

								@Override
								public void onHasChanged(List<AcceptanceDTO> selection) {

									for (AcceptanceDTO dto : selection) {
										chooserAction.chosen(dto);
										break;
									}

									popup.closePopup();
								}

							}, false), true);

							popup.setPosition(this.getAbsoluteTop() - 4, this.getAbsoluteLeft());

						}

						@Override
						public Widget getWidget(AcceptanceDTO dto) {

							return new Label(Statics.renderDescription(dto));
						}

					}

			);
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
