package com.supeyou.core.web.client.rpc.invitation;

import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.InvitationFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<InvitationDTO, InvitationFetchQuery> {

	public ListDataProvider(InvitationFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<InvitationDTO>() {

			@Override
			public void wasUpdated(InvitationDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(InvitationDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(InvitationDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<InvitationDTO, InvitationFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
