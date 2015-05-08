package com.hotelorga.core.web.client.rpc.payer;

import com.hotelorga.core.iface.dto.PayerDTO;
import com.hotelorga.core.iface.dto.PayerFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;

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
