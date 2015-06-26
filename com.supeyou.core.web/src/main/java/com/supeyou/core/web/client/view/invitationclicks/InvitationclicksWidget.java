package com.supeyou.core.web.client.view.invitationclicks;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterFetchQuery;
import com.supeyou.core.iface.dto.Supporter2InvitationDTO;
import com.supeyou.core.iface.dto.Supporter2InvitationFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.dto.DTOFetchList;

public class InvitationclicksWidget extends WidgetView {

	public InvitationclicksWidget(final SupporterDTO supporterDTO) {

		nameLabel.setText(supporterDTO.getComment().value());

		Supporter2InvitationFetchQuery supporter2InvitationFetchQuery = new Supporter2InvitationFetchQuery();

		supporter2InvitationFetchQuery.setIdA(supporterDTO.getId());

		com.supeyou.core.web.client.rpc.supporter2invitation.RPCCRUDServiceAsync.i.fetchList(new Page(), supporter2InvitationFetchQuery, new AsyncCallback<DTOFetchList<Supporter2InvitationDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();

			}

			@Override
			public void onSuccess(DTOFetchList<Supporter2InvitationDTO> result) {

				for (Supporter2InvitationDTO supporter2InvitationDTO : result) {

					Invitation2SupporterFetchQuery query = new Invitation2SupporterFetchQuery();

					query.setIdA(supporter2InvitationDTO.getDtoB().getId());

					com.supeyou.core.web.client.rpc.invitation2supporter.RPCCRUDServiceAsync.i.fetchList(new Page(), query, new AsyncCallback<DTOFetchList<Invitation2SupporterDTO>>() {

						@Override
						public void onSuccess(DTOFetchList<Invitation2SupporterDTO> result) {

							for (Invitation2SupporterDTO invitation2SupporterDTO : result) {

								childrenSlot.add(new InvitationclicksWidget(invitation2SupporterDTO.getDtoB()));

							}

						}

						@Override
						public void onFailure(Throwable caught) {

							caught.printStackTrace();

						}

					});

				}
			}
		});

	}

}
