package com.supeyou.core.web.client.view.heropage.invite.askforemail;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.supeyou.actor.web.client.login.ActorStatics;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.crudie.iface.datatype.types.EmailAddressType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.web.client.fields.types.FieldForEmailAddressType;
import com.supeyou.crudie.web.client.model.LoginStateModel;
import com.supeyou.crudie.web.client.rpc.user.RPCCRUDProxy;
import com.supeyou.crudie.web.client.uiorga.flatbutton.FlatButtonWidget;

public abstract class AskForEmailWidget extends WidgetView {

	private final FieldForEmailAddressType field;

	public AskForEmailWidget(final SupporterDTO supporterDTO) {

		field = new FieldForEmailAddressType() {
			@Override
			public void onHasChanged(EmailAddressType value) {

				super.onHasChanged(value);
			}
		};

		text1.setHTML(Text.i.ASK_FOR_EMAIL_Text_HTML());

		emailInputSlot.add(field);

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

		ActorStatics.fireActorEvent("click", "saveEmailButton", field.getValue() + "");

		UserDTO userDTO = LoginStateModel.i().getLoggedInUser();

		userDTO.setLoginId(new SingleLineString256Type(field.getValue().value()));

		// useEmailFirstPartAsName
		if (LoginStateModel.i().getLoggedInUser().getName() == null || LoginStateModel.i().getLoggedInUser().getName().equals("")) {

			String[] splittedEmail = field.getValue().value().split("@");

			userDTO.setName(new SingleLineString256Type(splittedEmail[0]));

		}

		RPCCRUDProxy.i().updadd(userDTO);

		onDismiss();
	}

	protected abstract void onDismiss();

	@Override
	protected void onLoad() {

		field.setFocus(true);

	}
}
