package com.supeyou.core.web.client.view.heropage;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.HeroIDType;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.rpc.hero.RPCCRUDServiceAsync;
import com.supeyou.core.web.client.rpc.hero.chooserlarge.item.ItemWidget;
import com.supeyou.core.web.client.view.heropage.invite.InviteWidget;
import com.supeyou.core.web.client.view.heropage.supporter.SupporterWidget;
import com.supeyou.crudie.web.client.model.LoginStateModel;
import com.supeyou.crudie.web.client.uiorga.flatbutton.FlatButtonWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class HeroPageWidget extends WidgetView {

	private HeroDTO heroDTO;

	public HeroPageWidget(HeroIDType dtoId) {

		RPCCRUDServiceAsync.i.get(dtoId, new AsyncCallback<HeroDTO>() {

			@Override
			public void onFailure(Throwable caught) {

				Window.alert(caught.getMessage());
				caught.printStackTrace();

			}

			@Override
			public void onSuccess(HeroDTO result) {

				heroDTO = result;
				render();

			}
		});

	}

	private void render() {

		driverSlot.add(new ItemWidget(heroDTO));

		com.supeyou.core.web.client.rpc.supporter.RPCCRUDServiceAsync.i.getOrCreate(LoginStateModel.i().getLoggedInUser(), heroDTO, new AsyncCallback<SupporterDTO>() {

			@Override
			public void onFailure(Throwable caught) {

				caught.printStackTrace();

			}

			@Override
			public void onSuccess(final SupporterDTO result) {

				supporterSlot.add(new SupporterWidget(result));

				FlatButtonWidget flatButtonWidget = new FlatButtonWidget();
				flatButtonWidget.setText(Text.i.BUTTON_Invite());
				flatButtonWidget.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						new PopupWidget(new InviteWidget(heroDTO, result), true);

					}
				});

				invitationButtonSlot.add(flatButtonWidget);

			}
		});

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
