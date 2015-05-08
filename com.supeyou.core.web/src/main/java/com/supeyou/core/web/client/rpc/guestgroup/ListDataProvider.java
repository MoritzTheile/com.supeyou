package com.supeyou.core.web.client.rpc.guestgroup;

import com.supeyou.core.iface.dto.GuestGroupDTO;
import com.supeyou.core.iface.dto.GuestGroupFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<GuestGroupDTO, GuestGroupFetchQuery> {

	public ListDataProvider(GuestGroupFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<GuestGroupDTO>() {

			@Override
			public void wasUpdated(GuestGroupDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(GuestGroupDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(GuestGroupDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<GuestGroupDTO, GuestGroupFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
