package com.supeyou.core.web.client.view.heropage.invite.manually;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.actor.web.client.login.ActorStatics;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.CoreStatics;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDServiceAsync;
import com.supeyou.core.web.client.view.heropage.invite.howtoinvite.HowToInviteWidget;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.web.client.fields.types.FieldForSingleLineString256Type;
import com.supeyou.crudie.web.client.resources.URLHelper;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public abstract class ManuallyWidget extends WidgetView {

	private InvitationDTO thisInvitationDTO;

	public enum RENDERMODE {
		ADVANCED, SIMPLE
	}

	private RENDERMODE rendermode;

	public ManuallyWidget(final SupporterDTO supporterDTO, RENDERMODE rendermode) {

		this.rendermode = rendermode;

		String defaultLinkName = "";

		text1.setHTML(Text.i.INVITE_MANUALLY_COPYANDPASTE_Text1_HTML());
		text2.setHTML(Text.i.INVITE_MANUALLY_COPYANDPASTE_Text2_HTML());
		text3.setHTML(Text.i.INVITE_MANUALLY_COPYANDPASTE_Text3_HTML());

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
				ActorStatics.fireActorEvent("click", "createNewInvitationButton");

				createInvitation(supporterDTO);

			}

		}, ClickEvent.getType());

		linkLabel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				ActorStatics.fireActorEvent("click", "markInvitationLink");

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

		if (RENDERMODE.SIMPLE.equals(rendermode)) {

			root.addStyleName("simple");

		}

		linkLabel.setText(getInvitURL(thisInvitationDTO));

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

		return URLHelper.getCurrentURL() + "?" + CoreStatics.INVITTOKEN_KEY + "=" + invitationDTO.getToken();

	}

	protected abstract void onDismiss();

}
