package com.supeyou.core.web.client.view.heropage.invite;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
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
		String url = URLHelper.getCurrentURL() + "?" + CoreStatics.INVITTOKEN_KEY + "=" + invitationDTO.getToken();
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

		shareButton.clear();

		shareButton.add(new HTMLPanel("<a href=\"whatsapp://send?text=" + url + "\" data-action=\"share/whatsapp/share\"> </a>"));

	}
}
