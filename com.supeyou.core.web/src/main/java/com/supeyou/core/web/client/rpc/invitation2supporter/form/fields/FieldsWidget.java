package com.supeyou.core.web.client.rpc.invitation2supporter.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.rpc.invitation2supporter.form.fields.field.FieldWidget;
import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;
import com.supeyou.crudie.web.client.fields.base.Field;
import com.supeyou.crudie.web.client.fields.types.AbstrDTOField;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class FieldsWidget extends WidgetView {

	private List<Field<?>> fields = new ArrayList<Field<?>>();

	private final Invitation2SupporterDTO thisDTO;

	public FieldsWidget(Invitation2SupporterDTO dto) {

		thisDTO = dto;

		put(

				"Invitation",

				new AbstrDTOField<InvitationDTO>(thisDTO.getDtoA()) {

					@Override
					public void onHasChanged(InvitationDTO value) {
						thisDTO.setDtoA(value);
						hasChanged();
					}

					PopupWidget popup;

					@Override
					protected void showChooser(final ChooserAction<InvitationDTO> chooserAction) {
						popup = new PopupWidget(new com.supeyou.core.web.client.rpc.invitation.choosersmall.ChooserSmallWidget(new SelectionListener<InvitationDTO>() {

							@Override
							public void onHasChanged(List<InvitationDTO> selection) {

								for (InvitationDTO dto : selection) {
									chooserAction.chosen(dto);
									break;
								}

								popup.closePopup();
							}

						}), true);

						popup.setPosition(this.getAbsoluteTop() - 4, this.getAbsoluteLeft());

					}

					@Override
					public Widget getWidget(InvitationDTO dto) {
						String label = "- - -";
						if (dto != null && dto.getComment() != null) {
							label = dto.getComment().value();
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
