package com.supeyou.core.web.client.view.heropage.invite.groupinvitor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDServiceAsync;
import com.supeyou.core.web.client.view.heropage.invite.copyandpaste.CopyAndPasteWidget;
import com.supeyou.core.web.client.view.heropage.invite.singleinvitor.SingleInvitorWidget;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;
import com.supeyou.crudie.web.client.uiorga.popup.contentwrapper.ContentWrapperWidget;

public abstract class GroupInvitorWidget extends WidgetView {

	private InvitationDTO invitationDTO;

	private final SupporterDTO supporterDTO;

	public GroupInvitorWidget(final SupporterDTO supporterDTO) {

		this.supporterDTO = supporterDTO;

		createInvitation(supporterDTO);

		googlePlusButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Window.open("https://plus.google.com/share?url==" + CopyAndPasteWidget.getInvitURL(invitationDTO), "_blank", "status=0,toolbar=0,menubar=0,location=0");

				onDismiss();

			}

		}, ClickEvent.getType());

		facebookButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Window.open("http://www.facebook.com/share.php?u=" + CopyAndPasteWidget.getInvitURL(invitationDTO), "_blank", "status=0,toolbar=0,menubar=0,location=0");

				onDismiss();

			}

		}, ClickEvent.getType());

		twitterButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Window.open("https://twitter.com/intent/tweet?text=" + getText(), "_blank", "status=0,toolbar=0,menubar=0,location=0");

				onDismiss();

			}

		}, ClickEvent.getType());

		copyAndPasteButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				final PopupWidget popupWidget = new PopupWidget();

				CopyAndPasteWidget contentWidget = new CopyAndPasteWidget(supporterDTO, invitationDTO) {

					@Override
					protected void onDismiss() {

						popupWidget.closePopup();

					}

				};

				popupWidget.init(new ContentWrapperWidget(Text.i.INVITE_HeaderLabel(), contentWidget));

			}

		}, ClickEvent.getType());
	}

	private void render() {
		// nothing to render
	}

	private String getText() {

		return "Do you also want to help " + SingleInvitorWidget.toStringNullsafe(supporterDTO.getHeroDTO().getName()) + "? " + CopyAndPasteWidget.getInvitURL(invitationDTO);

	}

	private void createInvitation(SupporterDTO supporterDTO) {

		RPCCRUDServiceAsync.i.create(supporterDTO, new AsyncCallback<InvitationDTO>() {

			@Override
			public void onSuccess(InvitationDTO result) {

				invitationDTO = result;
				render();

				RPCCRUDProxy.i().addListenersForSpecifiDTO(new CRUDProxyListener<InvitationDTO>() {

					@Override
					public void wasUpdated(InvitationDTO dto) {

						invitationDTO = dto;
						render();

					}

					@Override
					public void wasDeleted(InvitationDTO dto) {
						// not defined

					}

					@Override
					public void wasCreated(InvitationDTO dto) {
						// not possible

					}
				}, result);

			}

			@Override
			public void onFailure(Throwable caught) {

				caught.printStackTrace();

			}

		});
	}

	protected abstract void onDismiss();

}
