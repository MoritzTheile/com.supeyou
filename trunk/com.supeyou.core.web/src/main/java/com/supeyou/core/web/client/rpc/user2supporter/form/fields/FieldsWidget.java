package com.supeyou.core.web.client.rpc.user2supporter.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.User2SupporterDTO;
import com.supeyou.core.web.client.rpc.user2supporter.form.fields.field.FieldWidget;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;
import com.supeyou.crudie.web.client.fields.base.Field;
import com.supeyou.crudie.web.client.fields.types.AbstrDTOField;
import com.supeyou.crudie.web.client.resources.i18n.Text;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class FieldsWidget extends WidgetView {

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	private final User2SupporterDTO thisDTO;

	public FieldsWidget(User2SupporterDTO dto) {

		thisDTO = dto;

		put(

				Text.i.FIELD_NAME_User(),

				new AbstrDTOField<UserDTO>(thisDTO.getDtoA()) {

					@Override
					public void onHasChanged(UserDTO value) {
						thisDTO.setDtoA(value);
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
		put(

				"Supporter",

				new AbstrDTOField<SupporterDTO>(thisDTO.getDtoB()) {

					@Override
					public void onHasChanged(SupporterDTO value) {
						thisDTO.setDtoB(value);
						hasChanged();
					}

					PopupWidget popup;

					@Override
					protected void showChooser(final ChooserAction<SupporterDTO> chooserAction) {
						popup = new PopupWidget(new com.supeyou.core.web.client.rpc.supporter.choosersmall.ChooserSmallWidget(new SelectionListener<SupporterDTO>() {

							@Override
							public void onHasChanged(List<SupporterDTO> selection) {

								for (SupporterDTO dto : selection) {
									chooserAction.chosen(dto);
									break;
								}

								popup.closePopup();
							}

						}), true);

						popup.setPosition(this.getAbsoluteTop() - 4, this.getAbsoluteLeft());

					}

					@Override
					public Widget getWidget(SupporterDTO dto) {
						String label = "- - -";
						if (dto != null && dto.getTmpHeroName() != null) {
							label = dto.getTmpHeroName().value();
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
