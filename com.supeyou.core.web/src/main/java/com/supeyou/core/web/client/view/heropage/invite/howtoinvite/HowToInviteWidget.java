package com.supeyou.core.web.client.view.heropage.invite.howtoinvite;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDServiceAsync;
import com.supeyou.core.web.client.view.heropage.invite.manually.ManuallyWidget;
import com.supeyou.core.web.client.view.heropage.invite.manually.ManuallyWidget.RENDERMODE;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.web.client.resources.GoogleAnalytics;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;
import com.supeyou.crudie.web.client.uiorga.popup.contentwrapper.ContentWrapperWidget;

public abstract class HowToInviteWidget extends WidgetView {

	private final SupporterDTO thisSupporterDTO;

	public HowToInviteWidget(SupporterDTO supporterDTO) {

		this.thisSupporterDTO = supporterDTO;

		emailButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				GoogleAnalytics.i.sendEvent("click", "emailInvitation");

				final PopupWidget popupWidget = new PopupWidget();

				createInvitation("Email-Link", thisSupporterDTO, new InvitationCallback() {

					@Override
					public void invitationCreated(InvitationDTO invitationDTO) {

						ManuallyWidget contentWidget = new ManuallyWidget(thisSupporterDTO, RENDERMODE.EMAIL) {

							@Override
							protected void onDismiss() {

								popupWidget.closePopup();

							}

						};

						popupWidget.init(new ContentWrapperWidget(Text.i.INVITE_HeaderLabel(), contentWidget));

					}
				});

				onDismiss();
			}

		}, ClickEvent.getType());

		whatsAppButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				GoogleAnalytics.i.sendEvent("click", "whatsappInvitation");

				final PopupWidget popupWidget = new PopupWidget();

				createInvitation("WhatsApp-Link", thisSupporterDTO, new InvitationCallback() {

					@Override
					public void invitationCreated(InvitationDTO invitationDTO) {

						ManuallyWidget contentWidget = new ManuallyWidget(thisSupporterDTO, RENDERMODE.WHATSAPP) {

							@Override
							protected void onDismiss() {

								popupWidget.closePopup();

							}

						};

						popupWidget.init(new ContentWrapperWidget(Text.i.INVITE_HeaderLabel(), contentWidget));

					}
				});

				onDismiss();
			}

		}, ClickEvent.getType());

		googlePlusButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				GoogleAnalytics.i.sendEvent("click", "googleplusInvitation");

				createInvitation("GooglePlus-Link", thisSupporterDTO, new InvitationCallback() {

					@Override
					public void invitationCreated(InvitationDTO invitationDTO) {

						Window.open("https://plus.google.com/share?url==" + ManuallyWidget.getInvitURL(invitationDTO), "_blank", "status=0,toolbar=0,menubar=0,location=0");

					}
				});

				onDismiss();

			}

		}, ClickEvent.getType());

		facebookButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				GoogleAnalytics.i.sendEvent("click", "facebookInvitation");

				createInvitation("Facebook-Link", thisSupporterDTO, new InvitationCallback() {

					@Override
					public void invitationCreated(InvitationDTO invitationDTO) {

						Window.open("http://www.facebook.com/share.php?u=" + ManuallyWidget.getInvitURL(invitationDTO), "_blank", "status=0,toolbar=0,menubar=0,location=0");

					}
				});

				onDismiss();

			}

		}, ClickEvent.getType());

		twitterButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				GoogleAnalytics.i.sendEvent("click", "twitterInvitation");

				createInvitation("Twitter-Link", thisSupporterDTO, new InvitationCallback() {

					@Override
					public void invitationCreated(InvitationDTO invitationDTO) {

						Window.open("https://twitter.com/intent/tweet?text=" + ManuallyWidget.getInvitURL(invitationDTO), "_blank", "status=0,toolbar=0,menubar=0,location=0");

					}
				});

				onDismiss();

			}

		}, ClickEvent.getType());

		copyAndPasteButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				GoogleAnalytics.i.sendEvent("click", "copyandpasteInvitation");

				final PopupWidget popupWidget = new PopupWidget();

				createInvitation("CopyAndPaste-Link", thisSupporterDTO, new InvitationCallback() {

					@Override
					public void invitationCreated(InvitationDTO invitationDTO) {

						ManuallyWidget contentWidget = new ManuallyWidget(thisSupporterDTO, RENDERMODE.COPYANDPASTE) {

							@Override
							protected void onDismiss() {

								popupWidget.closePopup();

							}

						};

						popupWidget.init(new ContentWrapperWidget(Text.i.INVITE_HeaderLabel(), contentWidget));

					}
				});

				onDismiss();
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
