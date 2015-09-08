package com.supeyou.core.web.client.view.heropage.invite.askforname;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.web.client.fields.types.FieldForSingleLineString256Type;
import com.supeyou.crudie.web.client.model.LoginStateModel;
import com.supeyou.crudie.web.client.resources.GoogleAnalytics;
import com.supeyou.crudie.web.client.rpc.user.RPCCRUDProxy;
import com.supeyou.crudie.web.client.uiorga.flatbutton.FlatButtonWidget;

public abstract class AskForNameWidget extends WidgetView {

	private final FieldForSingleLineString256Type field = new FieldForSingleLineString256Type();

	public AskForNameWidget(final SupporterDTO supporterDTO) {

		text1.setHTML(Text.i.ASK_FOR_EMAIL_Name_HTML());

		inputSlot.add(field);

		FlatButtonWidget flatButtonWidget = new FlatButtonWidget();
		flatButtonWidget.setText(Text.i.MULTIUSE_Save());
		flatButtonWidget.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				if (field.getValue() != null && !field.getValue().value().equals("")) {

					save();

				}

			}

		});

		this.addDomHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {

				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {

					save();

				}

			}
		}, KeyUpEvent.getType());
		saveButtonSlot.add(flatButtonWidget);

	}

	private void save() {
		GoogleAnalytics.i.sendEvent("click", "saveNameButton");

		UserDTO userDTO = LoginStateModel.i().getLoggedInUser();
		userDTO.setName(field.getValue());

		RPCCRUDProxy.i().updadd(userDTO);

		onDismiss();

	}

	protected abstract void onDismiss();

	@Override
	protected void onLoad() {

		field.setFocus(true);

	}
}
