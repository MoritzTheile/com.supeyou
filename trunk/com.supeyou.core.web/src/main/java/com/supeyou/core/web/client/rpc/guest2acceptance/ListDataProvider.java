package com.supeyou.core.web.client.rpc.guest2acceptance;

import com.supeyou.core.iface.dto.Guest2AcceptanceDTO;
import com.supeyou.core.iface.dto.Guest2AcceptanceFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery> {

	public ListDataProvider(Guest2AcceptanceFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<Guest2AcceptanceDTO>() {

			@Override
			public void wasUpdated(Guest2AcceptanceDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(Guest2AcceptanceDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(Guest2AcceptanceDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
