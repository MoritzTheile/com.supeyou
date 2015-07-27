package com.supeyou.core.web.client.view.heropage;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.view.heropage.donate.DonateWidget;
import com.supeyou.core.web.client.view.heropage.invite.InviteWidget;
import com.supeyou.core.web.client.view.heropage.supportertree.SupporterTreeWidget;
import com.supeyou.core.web.client.view.supportercard.SupporterCardWidget;
import com.supeyou.crudie.web.client.uiorga.flatbutton.FlatButtonWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class HeroPageWidget extends WidgetView {

	private SupporterDTO supporterDTO;

	public HeroPageWidget(SupporterDTO supporterDTO) {

		this.supporterDTO = supporterDTO;
		render();

	}

	private void render() {

		supporterCardSlot.add(new SupporterCardWidget(supporterDTO));

		{
			FlatButtonWidget flatButtonWidget = new FlatButtonWidget();
			flatButtonWidget.setText(Text.i.BUTTON_Donate());
			flatButtonWidget.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					new PopupWidget(new DonateWidget(supporterDTO), true);

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

					new PopupWidget(new InviteWidget(supporterDTO), true);

				}
			});

			invitationButtonSlot.add(flatButtonWidget);
		}
		supporterTreeSlot.add(new SupporterTreeWidget(supporterDTO));

		// tmp hack:
		// if (heroDTO.getName().value().toLowerCase().contains("theile")) {
		// obsInvSlot.add(new HTML(
		// "<form action=\"https://www.paypal.com/cgi-bin/webscr\" method=\"post\" target=\"_top\">" +
		// "<input type=\"hidden\" name=\"cmd\" value=\"_s-xclick\">" +
		// "<input type=\"hidden\" name=\"hosted_button_id\" value=\"UAPLQRHYK7GV4\">" +
		// "<input type=\"image\" src=\"https://www.paypalobjects.com/de_DE/DE/i/btn/btn_donateCC_LG.gif\" border=\"0\" name=\"submit\" alt=\"Jetzt einfach, schnell und sicher online bezahlen – mit PayPal.\">" +
		// "<img alt=\"\" border=\"0\" src=\"https://www.paypalobjects.com/de_DE/i/scr/pixel.gif\" width=\"1\" height=\"1\">" +
		// "</form>"
		//
		// ));
		// }

	}
}
