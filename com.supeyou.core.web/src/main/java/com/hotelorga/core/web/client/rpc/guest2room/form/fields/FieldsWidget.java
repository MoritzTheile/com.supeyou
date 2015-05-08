package com.hotelorga.core.web.client.rpc.guest2room.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.core.iface.dto.Guest2RoomDTO;
import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.iface.dto.RoomDTO;
import com.hotelorga.core.web.client.rpc.guest.chooserlarge.item.ItemWidget;
import com.hotelorga.core.web.client.rpc.guest2room.form.fields.field.FieldWidget;
import com.hotelorga.foundation.iface.datatype.types.AmountType;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.web.client.fields.base.AbstrCompositeField;
import com.hotelorga.foundation.web.client.fields.base.Field;
import com.hotelorga.foundation.web.client.fields.types.AbstrDTOField;
import com.hotelorga.foundation.web.client.fields.types.FieldForAmountType;
import com.hotelorga.foundation.web.client.fields.types.FieldForDateType;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class FieldsWidget extends WidgetView {

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	private final Guest2RoomDTO thisDTO;

	public FieldsWidget(final Guest2RoomDTO dto, boolean showAssoAField, boolean showAssoBField) {

		thisDTO = dto;

		// private DateType fromDate;
		put(

				"Von",

				new FieldForDateType(dto.getFromDate()) {

					@Override
					public void onHasChanged(DateType value) {
						dto.setFromDate(value);
						;
						hasChanged();
					}

				}

		);
		// private DateType toDate;
		put(

				"Bis",

				new FieldForDateType(dto.getToDate()) {

					@Override
					public void onHasChanged(DateType value) {
						dto.setToDate(value);
						;
						hasChanged();
					}

				}

		);

		// private PositivIntegerType monthlyCosts;
		put(

				"Preis pro Monat",

				new FieldForAmountType(dto.getMonthlyCosts()) {

					@Override
					public void onHasChanged(AmountType value) {
						dto.setMonthlyCosts(value);
						hasChanged();
					}

				}

		);

		// private PositivIntegerType ownCosts;
		// put(
		//
		// "Eigenanteil",
		//
		// new FieldForAmountType(dto.getOwnCosts()) {
		//
		// @Override
		// public void onHasChanged(AmountType value) {
		// dto.setOwnCosts(value);
		// hasChanged();
		// }
		//
		// }
		//
		// );

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

					new AbstrDTOField<RoomDTO>(thisDTO.getDtoB()) {

						@Override
						public void onHasChanged(RoomDTO value) {
							thisDTO.setDtoB(value);
							hasChanged();
						}

						PopupWidget popup;

						@Override
						protected void showChooser(final ChooserAction<RoomDTO> chooserAction) {
							popup = new PopupWidget(new com.hotelorga.core.web.client.rpc.room.choosersmall.ChooserSmallWidget(new SelectionListener<RoomDTO>() {

								@Override
								public void onHasChanged(List<RoomDTO> selection) {

									for (RoomDTO dto : selection) {
										chooserAction.chosen(dto);
										break;
									}

									popup.closePopup();
								}

							}), true);

							popup.setPosition(this.getAbsoluteTop() - 4, this.getAbsoluteLeft());

						}

						@Override
						public Widget getWidget(RoomDTO dto) {
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
