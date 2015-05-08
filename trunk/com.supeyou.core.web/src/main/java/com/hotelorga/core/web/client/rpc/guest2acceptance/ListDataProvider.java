package com.hotelorga.core.web.client.rpc.guest2acceptance;

import com.hotelorga.core.iface.dto.Guest2AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2AcceptanceFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;

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
