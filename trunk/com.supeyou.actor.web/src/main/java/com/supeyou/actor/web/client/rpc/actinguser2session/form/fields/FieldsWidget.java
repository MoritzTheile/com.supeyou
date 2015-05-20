package com.supeyou.actor.web.client.rpc.actinguser2session.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.actor.iface.dto.ActingUser2SessionDTO;
import com.supeyou.actor.iface.dto.ActingUserDTO;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.web.client.rpc.actinguser2session.form.fields.field.FieldWidget;
import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;
import com.supeyou.crudie.web.client.fields.base.Field;
import com.supeyou.crudie.web.client.fields.types.AbstrDTOField;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class FieldsWidget extends WidgetView {

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	private final ActingUser2SessionDTO thisDTO;

	public FieldsWidget(ActingUser2SessionDTO dto) {

		thisDTO = dto;

		put(

				"ActingUser",

				new AbstrDTOField<ActingUserDTO>(thisDTO.getDtoA()) {

					@Override
					public void onHasChanged(ActingUserDTO value) {
						thisDTO.setDtoA(value);
						hasChanged();
					}

					PopupWidget popup;

					@Override
					protected void showChooser(final ChooserAction<ActingUserDTO> chooserAction) {
						popup = new PopupWidget(new com.supeyou.actor.web.client.rpc.actinguser.choosersmall.ChooserSmallWidget(new SelectionListener<ActingUserDTO>() {

							@Override
							public void onHasChanged(List<ActingUserDTO> selection) {

								for (ActingUserDTO dto : selection) {
									chooserAction.chosen(dto);
									break;
								}

								popup.closePopup();
							}

						}), true);

						popup.setPosition(this.getAbsoluteTop() - 4, this.getAbsoluteLeft());

					}

					@Override
					public Widget getWidget(ActingUserDTO dto) {
						String label = "- - -";
						if (dto != null && dto.getLoginId() != null) {
							label = dto.getLoginId().value();
						}
						return new Label(label);
					}

				}

		);
		put(

				"Session",

				new AbstrDTOField<SessionDTO>(thisDTO.getDtoB()) {

					@Override
					public void onHasChanged(SessionDTO value) {
						thisDTO.setDtoB(value);
						hasChanged();
					}

					PopupWidget popup;

					@Override
					protected void showChooser(final ChooserAction<SessionDTO> chooserAction) {
						popup = new PopupWidget(new com.supeyou.actor.web.client.rpc.session.choosersmall.ChooserSmallWidget(new SelectionListener<SessionDTO>() {

							@Override
							public void onHasChanged(List<SessionDTO> selection) {

								for (SessionDTO dto : selection) {
									chooserAction.chosen(dto);
									break;
								}

								popup.closePopup();
							}

						}), true);

						popup.setPosition(this.getAbsoluteTop() - 4, this.getAbsoluteLeft());

					}

					@Override
					public Widget getWidget(SessionDTO dto) {
						String label = "- - -";
						if (dto != null && dto.getHttpSessionId() != null) {
							label = dto.getHttpSessionId().value();
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
