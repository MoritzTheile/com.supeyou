package com.supeyou.core.web.client.view.heropage.invite;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.CoreStatics;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDServiceAsync;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.web.client.fields.types.FieldForSingleLineString256Type;
import com.supeyou.crudie.web.client.resources.URLHelper;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class InviteWidget extends WidgetView {

	private InvitationDTO invitationDTO;

	public InviteWidget(final SupporterDTO supporterDTO) {

		createInvitation(supporterDTO);

		reloadButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createInvitation(supporterDTO);

			}
		}, ClickEvent.getType());

		linkLabel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				markText(linkLabel.getElement());

			}
		});

		shareButtonMail.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Window.open("mailto:?subject=Hi&body=Please go to: " + getInvitURL(invitationDTO), "_self", "status=0,toolbar=0,menubar=0,location=0");

			}

		}, ClickEvent.getType());

		shareButtonWhatsapp.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Window.open("whatsapp://send?text=Please go to: " + getInvitURL(invitationDTO), "_self", "status=0,toolbar=0,menubar=0,location=0");

			}

		}, ClickEvent.getType());

		shareButtonGoogle.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Window.open("https://plus.google.com/share?url==" + getInvitURL(invitationDTO), "_blank", "status=0,toolbar=0,menubar=0,location=0");

			}

		}, ClickEvent.getType());

		shareButtonFacebook.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Window.open("http://www.facebook.com/share.php?u=" + getInvitURL(invitationDTO), "_blank", "status=0,toolbar=0,menubar=0,location=0");

			}

		}, ClickEvent.getType());

		shareButtonTwitter.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Window.open("https://twitter.com/intent/tweet?text=Please go to: " + getInvitURL(invitationDTO), "_blank", "status=0,toolbar=0,menubar=0,location=0");

			}

		}, ClickEvent.getType());

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

				render(result);

				RPCCRUDProxy.i().addListenersForSpecifiDTO(new CRUDProxyListener<InvitationDTO>() {

					@Override
					public void wasUpdated(InvitationDTO dto) {
						render(dto);

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

	private void render(final InvitationDTO invitationDTO) {

		this.invitationDTO = invitationDTO;

		String url = getInvitURL(invitationDTO);
		linkLabel.setText(url);

		linkNameTextBoxSlot.clear();
		final FieldForSingleLineString256Type name = new FieldForSingleLineString256Type(invitationDTO.getComment()) {
			public void onHasChanged(com.supeyou.crudie.iface.datatype.types.SingleLineString256Type value) {
				invitationDTO.setComment(value);
				RPCCRUDProxy.i().updadd(invitationDTO);
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

	private String getInvitURL(final InvitationDTO invitationDTO) {
		String url = URLHelper.getCurrentURL() + "?" + CoreStatics.INVITTOKEN_KEY + "=" + invitationDTO.getToken();
		return url;
	}
}
