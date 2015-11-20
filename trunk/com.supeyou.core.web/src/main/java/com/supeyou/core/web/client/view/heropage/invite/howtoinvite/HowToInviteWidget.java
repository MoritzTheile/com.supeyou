package com.supeyou.core.web.client.view.heropage.invite.howtoinvite;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.actor.web.client.login.ActorStatics;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDServiceAsync;
import com.supeyou.core.web.client.view.heropage.invite.hintmanual.HintManualWidget;
import com.supeyou.core.web.client.view.heropage.invite.manually.ManuallyWidget;
import com.supeyou.core.web.client.view.heropage.invite.manually.ManuallyWidget.RENDERMODE;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;
import com.supeyou.crudie.web.client.uiorga.popup.contentwrapper.ContentWrapperWidget;

public abstract class HowToInviteWidget extends WidgetView {

	private final SupporterDTO thisSupporterDTO;

	/*
	 * Attention: invitationDTO can't be created lazy. It takes to much time so that the browsers popup blocker will kick in: http://theandystratton.com/2012/how-to-bypass-google-chromes-javascript-popup-blocker
	 */
	private InvitationDTO invitationDTO;

	public HowToInviteWidget(SupporterDTO supporterDTO) {

		this.thisSupporterDTO = supporterDTO;

		createInvitation("Email-Link", thisSupporterDTO, new InvitationCallback() {

			@Override
			public void invitationCreated(InvitationDTO result) {

				invitationDTO = result;

			}
		});

		emailButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Window.open("mailto:?subject=SupeYou invitation&body=%0A%0A%20%20%20%20" + URL.encode(ManuallyWidget.getInvitURL(invitationDTO)) + "%0A(if you can%27t click copy to your browser)%0A", "_self", "");// "status=0,toolbar=0,menubar=0,location=0");

				ActorStatics.fireActorEvent("click", "emailInvitation");

				copyAndPasteButton.add(new HintManualWidget());

			}

		}, ClickEvent.getType());

		whatsAppButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Window.open("whatsapp://send?text=" + URL.encodePathSegment(ManuallyWidget.getInvitURL(invitationDTO)), "_self", "");// "status=0,toolbar=0,menubar=0,location=0");

				copyAndPasteButton.add(new HintManualWidget());

				ActorStatics.fireActorEvent("click", "whatsappInvitation");

			}

		}, ClickEvent.getType());
		googlePlusButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Window.open("https://plus.google.com/share?url=" + URL.encode(ManuallyWidget.getInvitURL(invitationDTO)), "_blank", "");// "status=0,toolbar=0,menubar=0,location=0");

				onDismiss();
				ActorStatics.fireActorEvent("click", "googleplusInvitation");

			}

		}, ClickEvent.getType());

		facebookButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.open("http://www.facebook.com/share.php?u=" + URL.encode(ManuallyWidget.getInvitURL(invitationDTO)), "_blank", "");// "status=0,toolbar=0,menubar=0,location=0");

				onDismiss();

				ActorStatics.fireActorEvent("click", "facebookInvitation");

			}

		}, ClickEvent.getType());

		twitterButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Window.open("https://twitter.com/intent/tweet?text=" + URL.encode(ManuallyWidget.getInvitURL(invitationDTO)), "_blank", "");// "status=0,toolbar=0,menubar=0,location=0");

				onDismiss();
				ActorStatics.fireActorEvent("click", "twitterInvitation");

			}

		}, ClickEvent.getType());

		copyAndPasteButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				ActorStatics.fireActorEvent("click", "copyandpasteInvitation");

				final PopupWidget popupWidget = new PopupWidget() {
					@Override
					public void onClose() {

					}
				};

				ManuallyWidget contentWidget = new ManuallyWidget(thisSupporterDTO, RENDERMODE.SIMPLE) {

					@Override
					protected void onDismiss() {

						popupWidget.closePopup();

					}

				};

				popupWidget.init(new ContentWrapperWidget(Text.i.INVITE_HeaderLabel(), contentWidget));

			}

		}, ClickEvent.getType());

	}

	public static void createInvitation(final String name, SupporterDTO supporterDTO, final InvitationCallback action) {

		RPCCRUDServiceAsync.i.create(supporterDTO, new AsyncCallback<InvitationDTO>() {

			@Override
			public void onSuccess(InvitationDTO result) {

				result.setComment(new SingleLineString256Type(name));

				action.invitationCreated(result);

				// writing to server
				RPCCRUDProxy.i().updadd(result);

			}

			@Override
			public void onFailure(Throwable caught) {

				caught.printStackTrace();

			}

		});
	}

	public interface InvitationCallback {

		void invitationCreated(InvitationDTO invitationDTO);

	}

	protected abstract void onDismiss();

}
