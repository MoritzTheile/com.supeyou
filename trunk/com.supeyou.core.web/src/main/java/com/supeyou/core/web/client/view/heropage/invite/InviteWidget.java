package com.supeyou.core.web.client.view.heropage.invite;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.CoreStatics;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDServiceAsync;
import com.supeyou.crudie.web.client.fields.types.FieldForSingleLineString256Type;
import com.supeyou.crudie.web.client.resources.URLHelper;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class InviteWidget extends WidgetView {

	public InviteWidget(SupporterDTO supporterDTO) {

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

		linkLabel.setText(URLHelper.getCurrentURL() + "?" + CoreStatics.INVITTOKEN_KEY + "=" + invitationDTO.getToken());

		linkNameTextBoxSlot.clear();
		linkNameTextBoxSlot.add(new FieldForSingleLineString256Type(invitationDTO.getComment()) {
			public void onHasChanged(com.supeyou.crudie.iface.datatype.types.SingleLineString256Type value) {
				invitationDTO.setComment(value);
				RPCCRUDProxy.i().updadd(invitationDTO);
			}
		});

	}
}
