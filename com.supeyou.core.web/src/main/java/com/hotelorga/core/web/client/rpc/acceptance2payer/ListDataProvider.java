package com.hotelorga.core.web.client.rpc.acceptance2payer;

import com.hotelorga.core.iface.dto.Acceptance2PayerDTO;
import com.hotelorga.core.iface.dto.Acceptance2PayerFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<Acceptance2PayerDTO, Acceptance2PayerFetchQuery> {

	public ListDataProvider(Acceptance2PayerFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<Acceptance2PayerDTO>() {

			@Override
			public void wasUpdated(Acceptance2PayerDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(Acceptance2PayerDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(Acceptance2PayerDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<Acceptance2PayerDTO, Acceptance2PayerFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
