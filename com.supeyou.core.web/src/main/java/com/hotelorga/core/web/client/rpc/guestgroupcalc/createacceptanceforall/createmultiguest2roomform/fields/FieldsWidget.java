package com.hotelorga.core.web.client.rpc.guestgroupcalc.createacceptanceforall.createmultiguest2roomform.fields;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.core.iface.dto.PayerDTO;
import com.hotelorga.core.web.client.fields.types.FieldForPositivIntegerType;
import com.hotelorga.core.web.client.rpc.guestgroupcalc.createacceptanceforall.createmultiguest2roomform.fields.field.FieldWidget;
import com.hotelorga.foundation.iface.datatype.types.AmountType;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.iface.datatype.types.PositivIntegerType;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.web.client.fields.base.AbstrCompositeField;
import com.hotelorga.foundation.web.client.fields.base.Field;
import com.hotelorga.foundation.web.client.fields.types.AbstrDTOField;
import com.hotelorga.foundation.web.client.fields.types.FieldForAmountType;
import com.hotelorga.foundation.web.client.fields.types.FieldForDateType;
import com.hotelorga.foundation.web.client.fields.types.FieldForSingleLineString256Type;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class FieldsWidget extends WidgetView {

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	private SingleLineString256Type comment;
	private DateType from;
	private DateType to;
	private AmountType acceptedCosts;
	private PositivIntegerType acceptedDays;
	private AmountType fixedOwnCosts;
	private PayerDTO payer;

	public FieldsWidget(DateType from, DateType to) {
		this.from = from;
		this.to = to;
		init();
	}

	private void init() {

		put(

				"Beschreibung",

				new FieldForSingleLineString256Type(comment) {

					@Override
					public void onHasChanged(SingleLineString256Type value) {
						comment = value;
					}

				}

		);
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

		put(

				"Kostenübernahme",

				new FieldForAmountType(acceptedCosts) {

					@Override
					public void onHasChanged(AmountType value) {
						acceptedCosts = value;
					}

				}

		);

		put(

				"Tage insgesamt",

				new FieldForPositivIntegerType(acceptedDays) {

					@Override
					public void onHasChanged(PositivIntegerType value) {
						acceptedDays = value;
					}

				}

		);

		put(

				"Fixe Eigenbeteiligung",

				new FieldForAmountType(fixedOwnCosts) {

					@Override
					public void onHasChanged(AmountType value) {
						fixedOwnCosts = value;
					}

				}

		);

		put(

				"Kostenträger",

				new AbstrDTOField<PayerDTO>(payer) {

					@Override
					public void onHasChanged(PayerDTO value) {
						payer = value;
					}

					PopupWidget popup;

					@Override
					protected void showChooser(final ChooserAction<PayerDTO> chooserAction) {
						popup = new PopupWidget(new com.hotelorga.core.web.client.rpc.payer.choosersmall.ChooserSmallWidget(new SelectionListener<PayerDTO>() {

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

	public SingleLineString256Type getComment() {
		return comment;
	}

	public DateType getFrom() {
		return from;
	}

	public DateType getTo() {
		return to;
	}

	public AmountType getAcceptedCosts() {
		return acceptedCosts;
	}

	public PositivIntegerType getAcceptedDays() {
		return acceptedDays;
	}

	public AmountType getFixedOwnCosts() {
		return fixedOwnCosts;
	}

	public PayerDTO getPayer() {
		return payer;
	}

}
