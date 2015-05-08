package com.hotelorga.core.web.client.rpc.guest;

import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.iface.dto.GuestFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;

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
