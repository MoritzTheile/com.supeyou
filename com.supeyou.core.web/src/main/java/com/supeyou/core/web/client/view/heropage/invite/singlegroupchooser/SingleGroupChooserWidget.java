package com.supeyou.core.web.client.view.heropage.invite.singlegroupchooser;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.view.heropage.invite.groupinvitor.GroupInvitorWidget;
import com.supeyou.core.web.client.view.heropage.invite.singleinvitor.SingleInvitorWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;
import com.supeyou.crudie.web.client.uiorga.popup.contentwrapper.ContentWrapperWidget;

public abstract class SingleGroupChooserWidget extends WidgetView {

	public SingleGroupChooserWidget(final SupporterDTO supporterDTO) {

		singleButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				final PopupWidget popupWidget = new PopupWidget();

				SingleInvitorWidget contentWidget = new SingleInvitorWidget(supporterDTO) {

					@Override
					protected void onDismiss() {

						popupWidget.closePopup();

					}

				};

				popupWidget.init(new ContentWrapperWidget(Text.i.INVITE_HeaderLabel(), contentWidget));

			}

		}, ClickEvent.getType());

		groupButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				final PopupWidget popupWidget = new PopupWidget();

				GroupInvitorWidget contentWidget = new GroupInvitorWidget(supporterDTO) {

					@Override
					protected void onDismiss() {

						popupWidget.closePopup();

					}

				};

				popupWidget.init(new ContentWrapperWidget(Text.i.INVITE_HeaderLabel(), contentWidget));

			}

		}, ClickEvent.getType());

	}

	protected abstract void onDismiss();

}
