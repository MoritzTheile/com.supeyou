package com.supeyou.core.web.client.rpc.payer;

import com.supeyou.core.iface.dto.PayerDTO;
import com.supeyou.core.iface.dto.PayerFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<PayerDTO, PayerFetchQuery> {

	public ListDataProvider(PayerFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<PayerDTO>() {

			@Override
			public void wasUpdated(PayerDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(PayerDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(PayerDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<PayerDTO, PayerFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
