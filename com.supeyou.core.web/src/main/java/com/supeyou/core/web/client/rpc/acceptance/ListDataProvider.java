package com.supeyou.core.web.client.rpc.acceptance;

import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.AcceptanceFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<AcceptanceDTO, AcceptanceFetchQuery> {

	public ListDataProvider(AcceptanceFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<AcceptanceDTO>() {

			@Override
			public void wasUpdated(AcceptanceDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(AcceptanceDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(AcceptanceDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<AcceptanceDTO, AcceptanceFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
