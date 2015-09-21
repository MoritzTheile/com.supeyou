package com.supeyou.actor.web.client.rpc.session2event.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.actor.iface.dto.EventDTO;
import com.supeyou.actor.iface.dto.Session2EventDTO;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.web.client.rpc.session2event.form.fields.field.FieldWidget;
import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;
import com.supeyou.crudie.web.client.fields.base.Field;
import com.supeyou.crudie.web.client.fields.types.AbstrDTOField;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class FieldsWidget extends WidgetView {

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	private final Session2EventDTO thisDTO;

	public FieldsWidget(Session2EventDTO dto) {

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

				"Event",

				new AbstrDTOField<EventDTO>(thisDTO.getDtoB()) {

					@Override
					public void onHasChanged(EventDTO value) {
						thisDTO.setDtoB(value);
						hasChanged();
					}

					PopupWidget popup;

					@Override
					protected void showChooser(final ChooserAction<EventDTO> chooserAction) {
						popup = new PopupWidget(new com.supeyou.actor.web.client.rpc.event.choosersmall.ChooserSmallWidget(new SelectionListener<EventDTO>() {

							@Override
							public void onHasChanged(List<EventDTO> selection) {

								for (EventDTO dto : selection) {
									chooserAction.chosen(dto);
									break;
								}

								popup.closePopup();
							}

						}), true);

						popup.setPosition(this.getAbsoluteTop() - 4, this.getAbsoluteLeft());

					}

					@Override
					public Widget getWidget(EventDTO dto) {
						String label = "- - -";
						if (dto != null && dto.getAction() != null) {
							label = dto.getAction().value();
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
