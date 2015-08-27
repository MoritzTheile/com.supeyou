package com.supeyou.core.web.client.view.heropage.invite.singleinvitor;

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
import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.web.client.fields.types.FieldForSingleLineString256Type;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;
import com.supeyou.crudie.web.client.uiorga.popup.contentwrapper.ContentWrapperWidget;

public abstract class SingleInvitorWidget extends WidgetView {

	private final SupporterDTO supporterDTO;
	private InvitationDTO invitationDTO;

	public SingleInvitorWidget(final SupporterDTO supporterDTO) {

		this.supporterDTO = supporterDTO;

		createInvitation(supporterDTO);

		emailButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Window.open("mailto:?"

						+ "subject="
						+ "Do you also want to help?"
						+ "&body="
						+ getLongText()
						, "_self"
						, "status=0,toolbar=0,menubar=0,location=0"

						);

				onDismiss();

			}

		}, ClickEvent.getType());

		whatsAppButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Window.open("whatsapp://send?text=" + getLongText(), "_self", "status=0,toolbar=0,menubar=0,location=0");

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
				onDismiss();
			}

		}, ClickEvent.getType());

	}

	private String getLongText() {

		return "Hi " + toStringNullsafe(invitationDTO.getComment()) + ",%0A" +
				"%0A" +
				"Do you also want to help " + toStringNullsafe(supporterDTO.getHeroDTO().getName()) + "?%0A" +
				"%0A" +
				"I created an invitation for you:%0A" +
				"%0A" +
				CopyAndPasteWidget.getInvitURL(invitationDTO) + "%0A" +
				"%0A" +
				"(if you can't click, please copy and paste to your browser)%0A" +
				"%0A" +
				"I think " + toStringNullsafe(supporterDTO.getHeroDTO().getName()) + " is doing a great job and deserves our support.%0A" +
				"%0A" +
				"Thanks a lot!";

	}

	public static String toStringNullsafe(AbstrType<?> type) {
		if (type == null) {
			return "";
		}
		return type + "";
	}

	private void render() {

		linkNameTextBoxSlot.clear();

		final FieldForSingleLineString256Type name = new FieldForSingleLineString256Type(invitationDTO.getComment()) {

			public void onHasChanged(com.supeyou.crudie.iface.datatype.types.SingleLineString256Type value) {

				invitationDTO.setComment(value);

				RPCCRUDProxy.i().updadd(invitationDTO);

			}

		};

		name.setFocus();

		name.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				name.setOrigValue(new SingleLineString256Type(""));

			}
		}, ClickEvent.getType());

		linkNameTextBoxSlot.add(name);

	}

	private void createInvitation(SupporterDTO supporterDTO) {

		RPCCRUDServiceAsync.i.create(supporterDTO, new AsyncCallback<InvitationDTO>() {

			@Override
			public void onSuccess(InvitationDTO result) {

				invitationDTO = result;

				render();

				RPCCRUDProxy.i().addListenersForSpecificDTO(new CRUDProxyListener<InvitationDTO>() {

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
