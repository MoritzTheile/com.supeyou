package com.supeyou.core.web.client.rpc.invitation2supporter;

import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<Invitation2SupporterDTO, Invitation2SupporterFetchQuery> {

	public ListDataProvider(Invitation2SupporterFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<Invitation2SupporterDTO>() {

			@Override
			public void wasUpdated(Invitation2SupporterDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(Invitation2SupporterDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(Invitation2SupporterDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<Invitation2SupporterDTO, Invitation2SupporterFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
