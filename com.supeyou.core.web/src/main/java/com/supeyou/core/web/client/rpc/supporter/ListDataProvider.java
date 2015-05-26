package com.supeyou.core.web.client.rpc.supporter;

import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<SupporterDTO, SupporterFetchQuery> {

	public ListDataProvider(SupporterFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<SupporterDTO>() {

			@Override
			public void wasUpdated(SupporterDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(SupporterDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(SupporterDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<SupporterDTO, SupporterFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
