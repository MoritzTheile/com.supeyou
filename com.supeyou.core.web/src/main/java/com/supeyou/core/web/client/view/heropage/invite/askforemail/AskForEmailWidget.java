package com.supeyou.core.web.client.view.heropage.invite.askforemail;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.crudie.web.client.fields.types.FieldForEmailAddressType;
import com.supeyou.crudie.web.client.resources.GoogleAnalytics;
import com.supeyou.crudie.web.client.uiorga.flatbutton.FlatButtonWidget;

public abstract class AskForEmailWidget extends WidgetView {

	private final FieldForEmailAddressType emailField = new FieldForEmailAddressType();

	public AskForEmailWidget(final SupporterDTO supporterDTO) {

		text1.setHTML(Text.i.ASK_FOR_EMAIL_Text_HTML());

		emailInputSlot.add(emailField);

		FlatButtonWidget flatButtonWidget = new FlatButtonWidget();
		flatButtonWidget.setText(Text.i.MULTIUSE_Save());
		flatButtonWidget.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				GoogleAnalytics.i.sendEvent("click", "saveEmailButton");

				Window.alert("TODO save email");

			}
		});

		saveButtonSlot.add(flatButtonWidget);

	}

	protected abstract void onDismiss();

	@Override
	protected void onLoad() {

		emailField.setFocus();

	}
}
