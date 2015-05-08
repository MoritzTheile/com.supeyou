package com.supeyou.core.web.client.rpc.guestgroupcalc.createguestgroupforall.createmultiguest2roomform.fields;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.RoomDTO;
import com.supeyou.core.web.client.rpc.guestgroupcalc.createguestgroupforall.createmultiguest2roomform.fields.field.FieldWidget;
import com.supeyou.crudie.iface.datatype.types.AmountType;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;
import com.supeyou.crudie.web.client.fields.base.Field;
import com.supeyou.crudie.web.client.fields.types.AbstrDTOField;
import com.supeyou.crudie.web.client.fields.types.FieldForAmountType;
import com.supeyou.crudie.web.client.fields.types.FieldForDateType;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class FieldsWidget extends WidgetView {

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	private DateType from;
	private DateType to;
	private AmountType costsPerMonth = new AmountType(51000);
	private RoomDTO roomDTO;

	public FieldsWidget(DateType from, DateType to) {
		this.from = from;
		this.to = to;
		init();
	}

	private void init() {
		// private DateType fromDate;
		put(

				"Von",

				new FieldForDateType(from) {

					@Override
					public void onHasChanged(DateType value) {
						from = value;

					}

				}

		);
		// private DateType toDate;
		put(

				"Bis",

				new FieldForDateType(to) {

					@Override
					public void onHasChanged(DateType value) {
						to = value;
					}

				}

		);

		// private PositivIntegerType monthlyCosts;
		put(

				"Preis pro Monat",

				new FieldForAmountType(costsPerMonth) {

					@Override
					public void onHasChanged(AmountType value) {
						costsPerMonth = value;
					}

				}

		);

		put(

				"Zimmer",

				new AbstrDTOField<RoomDTO>(roomDTO) {

					@Override
					public void onHasChanged(RoomDTO value) {
						roomDTO = value;
					}

					PopupWidget popup;

					@Override
					protected void showChooser(final ChooserAction<RoomDTO> chooserAction) {
						popup = new PopupWidget(new com.supeyou.core.web.client.rpc.room.choosersmall.ChooserSmallWidget(new SelectionListener<RoomDTO>() {

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

	public DateType getFrom() {
		return from;
	}

	public DateType getTo() {
		return to;
	}

	public AmountType getCostsPerMonth() {
		return costsPerMonth;
	}

	public RoomDTO getRoomDTO() {
		return roomDTO;
	}

}
