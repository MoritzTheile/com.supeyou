package com.supeyou.core.web.client.rpc.supporter2invitation;

import com.supeyou.core.iface.dto.Supporter2InvitationDTO;
import com.supeyou.core.iface.dto.Supporter2InvitationFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<Supporter2InvitationDTO, Supporter2InvitationFetchQuery> {

	public ListDataProvider(Supporter2InvitationFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<Supporter2InvitationDTO>() {

			@Override
			public void wasUpdated(Supporter2InvitationDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(Supporter2InvitationDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(Supporter2InvitationDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<Supporter2InvitationDTO, Supporter2InvitationFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
