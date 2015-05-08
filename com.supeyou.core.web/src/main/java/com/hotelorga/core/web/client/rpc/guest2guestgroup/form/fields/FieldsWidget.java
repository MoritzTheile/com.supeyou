package com.hotelorga.core.web.client.rpc.guest2guestgroup.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.core.iface.datatype.enums.GROUPROLE;
import com.hotelorga.core.iface.dto.Guest2GuestGroupDTO;
import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.iface.dto.GuestGroupDTO;
import com.hotelorga.core.web.client.fields.types.FieldForGroupRole;
import com.hotelorga.core.web.client.rpc.guest.chooserlarge.item.ItemWidget;
import com.hotelorga.core.web.client.rpc.guest2guestgroup.form.fields.field.FieldWidget;
import com.hotelorga.foundation.web.client.fields.base.AbstrCompositeField;
import com.hotelorga.foundation.web.client.fields.base.Field;
import com.hotelorga.foundation.web.client.fields.types.AbstrDTOField;
import com.hotelorga.foundation.web.client.fields.types.FieldForBoolean;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class FieldsWidget extends WidgetView {

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	private final Guest2GuestGroupDTO thisDTO;

	public FieldsWidget(final Guest2GuestGroupDTO dto, boolean showAssoAField, boolean showAssoBField) {

		thisDTO = dto;

		put(

				"Rolle",

				new FieldForGroupRole(dto.getGroupRole()) {

					@Override
					public void onHasChanged(GROUPROLE value) {
						dto.setGroupRole(value);
						hasChanged();
					}

				}

		);

		put(

				"Gruppenführer",

				new FieldForBoolean(dto.getGroupLeader()) {

					@Override
					public void onHasChanged(Boolean value) {
						dto.setGroupLeader(value);
						hasChanged();
					}

				}

		);

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

					"Zimmer",

					new AbstrDTOField<GuestGroupDTO>(thisDTO.getDtoB()) {

						@Override
						public void onHasChanged(GuestGroupDTO value) {
							thisDTO.setDtoB(value);
							hasChanged();
						}

						PopupWidget popup;

						@Override
						protected void showChooser(final ChooserAction<GuestGroupDTO> chooserAction) {
							popup = new PopupWidget(new com.hotelorga.core.web.client.rpc.guestgroup.choosersmall.ChooserSmallWidget(new SelectionListener<GuestGroupDTO>() {

								@Override
								public void onHasChanged(List<GuestGroupDTO> selection) {

									for (GuestGroupDTO dto : selection) {
										chooserAction.chosen(dto);
										break;
									}

									popup.closePopup();
								}

							}), true);

							popup.setPosition(this.getAbsoluteTop() - 4, this.getAbsoluteLeft());

						}

						@Override
						public Widget getWidget(GuestGroupDTO dto) {
							String label = "- - -";
							if (dto != null && dto.getName() != null) {
								label = dto.getName().value();
							}
							return new Label(label);
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
