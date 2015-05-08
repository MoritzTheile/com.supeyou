package com.supeyou.core.web.client.rpc.guest;

import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.iface.dto.GuestFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<GuestDTO, GuestFetchQuery> {

	public ListDataProvider(GuestFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<GuestDTO>() {

			@Override
			public void wasUpdated(GuestDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(GuestDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(GuestDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<GuestDTO, GuestFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
