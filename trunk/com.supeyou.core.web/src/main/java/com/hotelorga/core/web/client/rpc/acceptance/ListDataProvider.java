package com.hotelorga.core.web.client.rpc.acceptance;

import com.hotelorga.core.iface.dto.AcceptanceDTO;
import com.hotelorga.core.iface.dto.AcceptanceFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;

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
