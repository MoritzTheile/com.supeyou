package com.supeyou.actor.web.client.rpc.session2user.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.actor.iface.dto.Session2UserDTO;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.web.client.rpc.session2user.form.fields.field.FieldWidget;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;
import com.supeyou.crudie.web.client.fields.base.Field;
import com.supeyou.crudie.web.client.fields.types.AbstrDTOField;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class FieldsWidget extends WidgetView {

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	private final Session2UserDTO thisDTO;

	public FieldsWidget(Session2UserDTO dto) {

		thisDTO = dto;

		put(

				"Session",

				new AbstrDTOField<SessionDTO>(thisDTO.getDtoA()) {

					@Override
					public void onHasChanged(SessionDTO value) {
						thisDTO.setDtoA(value);
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
		put(

				"User",

				new AbstrDTOField<UserDTO>(thisDTO.getDtoB()) {

					@Override
					public void onHasChanged(UserDTO value) {
						thisDTO.setDtoB(value);
						hasChanged();
					}

					PopupWidget popup;

					@Override
					protected void showChooser(final ChooserAction<UserDTO> chooserAction) {
						popup = new PopupWidget(new com.supeyou.crudie.web.client.rpc.user.choosersmall.ChooserSmallWidget(new SelectionListener<UserDTO>() {

							@Override
							public void onHasChanged(List<UserDTO> selection) {

								for (UserDTO dto : selection) {
									chooserAction.chosen(dto);
									break;
								}

								popup.closePopup();
							}

						}), true);

						popup.setPosition(this.getAbsoluteTop() - 4, this.getAbsoluteLeft());

					}

					@Override
					public Widget getWidget(UserDTO dto) {
						String label = "- - -";
						if (dto != null && dto.getLoginId() != null) {
							label = dto.getLoginId().value();
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
