package com.supeyou.core.web.client.view.heropage;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.view.herocard.HeroCardWidget;
import com.supeyou.core.web.client.view.herocard.HeroCardWidget.VIEW;
import com.supeyou.core.web.client.view.heropage.donate.DonateWidget;
import com.supeyou.core.web.client.view.heropage.howitworks.HowItWorksWidget;
import com.supeyou.core.web.client.view.heropage.invite.InviteWidget;
import com.supeyou.core.web.client.view.heropage.supportertree.SupporterTreeWidget;
import com.supeyou.crudie.web.client.uiorga.flatbutton.FlatButtonWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;
import com.supeyou.crudie.web.client.uiorga.popup.contentwrapper.ContentWrapperWidget;

public class HeroPageWidget extends WidgetView {

	private SupporterDTO supporterDTO;

	public HeroPageWidget(SupporterDTO supporterDTO) {

		this.supporterDTO = supporterDTO;
		render();

	}

	private void render() {

		supporterCardSlot.add(new HeroCardWidget(supporterDTO, VIEW.NODEVIEW));

		{
			FlatButtonWidget flatButtonWidget = new FlatButtonWidget();
			flatButtonWidget.setText(Text.i.BUTTON_Donate());
			flatButtonWidget.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					new PopupWidget(new ContentWrapperWidget(Text.i.DONATE_HeaderLabel(), new DonateWidget(supporterDTO)), true);

				}
			});

			donationButtonSlot.add(flatButtonWidget);
		}
		{
			FlatButtonWidget flatButtonWidget = new FlatButtonWidget();
			flatButtonWidget.setText(Text.i.BUTTON_Invite());
			flatButtonWidget.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					new PopupWidget(new ContentWrapperWidget(Text.i.INVITE_HeaderLabel(), new InviteWidget(supporterDTO)), true);

				}
			});

			invitationButtonSlot.add(flatButtonWidget);
		}

		if (supporterDTO.getDecendentCount() > 0) {
			supporterTreeSlot.add(new SupporterTreeWidget(supporterDTO));
		} else {
			supporterTreeSlot.add(new HowItWorksWidget());
		}

	}
}
