package com.supeyou.core.web.client.view.heropage.invite.manually;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.CoreStatics;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDServiceAsync;
import com.supeyou.core.web.client.view.heropage.invite.howtoinvite.HowToInviteWidget;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.web.client.fields.types.FieldForSingleLineString256Type;
import com.supeyou.crudie.web.client.resources.GoogleAnalytics;
import com.supeyou.crudie.web.client.resources.URLHelper;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public abstract class ManuallyWidget extends WidgetView {

	private InvitationDTO thisInvitationDTO;

	public enum RENDERMODE {
		COPYANDPASTE, EMAIL, WHATSAPP, SMS
	}

	public ManuallyWidget(final SupporterDTO supporterDTO, RENDERMODE rendermode) {

		String defaultLinkName = "";

		if (RENDERMODE.COPYANDPASTE.equals(rendermode)) {
			defaultLinkName = "CopyAndPaste-Link";
			text1.setHTML(Text.i.INVITE_MANUALLY_COPYANDPASTE_Text1_HTML());
			text2.setHTML(Text.i.INVITE_MANUALLY_COPYANDPASTE_Text2_HTML());
			text3.setHTML(Text.i.INVITE_MANUALLY_COPYANDPASTE_Text3_HTML());
		} else if (RENDERMODE.EMAIL.equals(rendermode)) {
			root.addStyleName("simple");
			defaultLinkName = "Email-Link";
			text1.setHTML(Text.i.INVITE_MANUALLY_EMAIL_Text1_HTML());
			text2.setHTML(Text.i.INVITE_MANUALLY_EMAIL_Text2_HTML());
			text3.setHTML(Text.i.INVITE_MANUALLY_EMAIL_Text3_HTML());
		} else if (RENDERMODE.WHATSAPP.equals(rendermode)) {
			root.addStyleName("simple");
			defaultLinkName = "WhatsApp-Link";
			text1.setHTML(Text.i.INVITE_MANUALLY_WHATSAPP_Text1_HTML());
			text2.setHTML(Text.i.INVITE_MANUALLY_WHATSAPP_Text2_HTML());
			text3.setHTML(Text.i.INVITE_MANUALLY_WHATSAPP_Text3_HTML());
		} else if (RENDERMODE.SMS.equals(rendermode)) {
			root.addStyleName("simple");
			defaultLinkName = "SMS-Link";
			text1.setHTML(Text.i.INVITE_MANUALLY_SMS_Text1_HTML());
			text2.setHTML(Text.i.INVITE_MANUALLY_SMS_Text2_HTML());
			text3.setHTML(Text.i.INVITE_MANUALLY_SMS_Text3_HTML());
		}

		HowToInviteWidget.createInvitation(defaultLinkName, supporterDTO, new HowToInviteWidget.InvitationCallback() {

			@Override
			public void invitationCreated(InvitationDTO invitationDTO) {

				thisInvitationDTO = invitationDTO;
				render();

			}

		});

		reloadButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				GoogleAnalytics.i.sendEvent("click", "createNewInvitationButton");

				createInvitation(supporterDTO);

			}

		}, ClickEvent.getType());

		linkLabel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				GoogleAnalytics.i.sendEvent("click", "markInvitationLink");

				markText(linkLabel.getElement());

			}
		});

	}

	private native void markText(Element elem) /*-{
		if ($doc.selection && $doc.selection.createRange) {
			var range = $doc.selection.createRange();
			range.moveToElementText(elem);
			range.select();
		} else if ($doc.createRange && $wnd.getSelection) {
			var range = $doc.createRange();
			range.selectNode(elem);
			var selection = $wnd.getSelection();
			selection.removeAllRanges();
			selection.addRange(range);
		}
	}-*/;

	private void createInvitation(SupporterDTO supporterDTO) {

		RPCCRUDServiceAsync.i.create(supporterDTO, new AsyncCallback<InvitationDTO>() {

			@Override
			public void onSuccess(InvitationDTO result) {

				thisInvitationDTO = result;

				render();

				RPCCRUDProxy.i().addListenersForSpecificDTO(new CRUDProxyListener<InvitationDTO>() {

					@Override
					public void wasUpdated(InvitationDTO dto) {
						thisInvitationDTO = dto;

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

	private void render() {

		String url = getInvitURL(thisInvitationDTO);
		linkLabel.setText(url);

		linkNameTextBoxSlot.clear();
		final FieldForSingleLineString256Type name = new FieldForSingleLineString256Type(thisInvitationDTO.getComment()) {
			public void onHasChanged(com.supeyou.crudie.iface.datatype.types.SingleLineString256Type value) {
				thisInvitationDTO.setComment(value);
				RPCCRUDProxy.i().updadd(thisInvitationDTO);
			}
		};

		name.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				name.setOrigValue(new SingleLineString256Type(""));

			}
		}, ClickEvent.getType());
		linkNameTextBoxSlot.add(name);

	}

	public static String getInvitURL(final InvitationDTO invitationDTO) {
		String url = URLHelper.getCurrentURL() + "?" + CoreStatics.INVITTOKEN_KEY + "=" + invitationDTO.getToken();
		return url;
	}

	protected abstract void onDismiss();

}
