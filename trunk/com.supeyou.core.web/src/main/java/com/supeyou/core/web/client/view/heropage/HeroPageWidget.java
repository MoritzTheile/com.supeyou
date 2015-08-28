package com.supeyou.core.web.client.view.heropage;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.view.herocard.HeroCardWidget;
import com.supeyou.core.web.client.view.herocard.HeroCardWidget.VIEW;
import com.supeyou.core.web.client.view.heropage.donate.DonateWidget;
import com.supeyou.core.web.client.view.heropage.howitworks.HowItWorksWidget;
import com.supeyou.core.web.client.view.heropage.invite.howtoinvite.HowToInviteWidget;
import com.supeyou.core.web.client.view.heropage.supportertree.SupporterTreeWidget;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.dto.DTOFetchList;
import com.supeyou.crudie.web.client.resources.GoogleAnalytics;
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

		GoogleAnalytics.i.sendEvent("view", "heroPage");

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
			flatButtonWidget.addClickHandler(inviteClickHandler);

			invitationButtonSlot.add(flatButtonWidget);
		}
		Invitation2SupporterFetchQuery supporterFetchQuery = new Invitation2SupporterFetchQuery();

		supporterFetchQuery.setInvitor(supporterDTO);

		com.supeyou.core.web.client.rpc.invitation2supporter.RPCCRUDServiceAsync.i.fetchList(new Page(), supporterFetchQuery, new AsyncCallback<DTOFetchList<Invitation2SupporterDTO>>() {

			@Override
			public void onFailure(Throwable caught) {

				caught.printStackTrace();

			}

			@Override
			public void onSuccess(DTOFetchList<Invitation2SupporterDTO> childrenDTO) {

				if (childrenDTO.size() > 0) {

					supporterTreeSlot.add(new SupporterTreeWidget(supporterDTO));

				} else {

					HowItWorksWidget howItWorksWidget = new HowItWorksWidget();
					howItWorksWidget.addDomHandler(inviteClickHandler, ClickEvent.getType());
					supporterTreeSlot.add(howItWorksWidget);

				}

			}

		});

	}

	private ClickHandler inviteClickHandler = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {

			final PopupWidget popupWidget = new PopupWidget();

			HowToInviteWidget content = new HowToInviteWidget(supporterDTO) {

				@Override
				protected void onDismiss() {

					popupWidget.closePopup();

				}

			};

			popupWidget.init(new ContentWrapperWidget(Text.i.INVITE_HeaderLabel(), content));

		}

	};
}
