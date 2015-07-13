package com.supeyou.core.web.client.view.heropage.invite;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.rpc.invitation.RPCCRUDServiceAsync;

public class InviteWidget extends WidgetView {

	public InviteWidget(HeroDTO heroDTO, SupporterDTO supporterDTO) {

		RPCCRUDServiceAsync.i.create(supporterDTO, new AsyncCallback<InvitationDTO>() {

			@Override
			public void onSuccess(InvitationDTO result) {
				invitationLabel.setText("http://supeyou.com/invit=" + result.getToken());

			}

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();

			}
		});
	}

}
