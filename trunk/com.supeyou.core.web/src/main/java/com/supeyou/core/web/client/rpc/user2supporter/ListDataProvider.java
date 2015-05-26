package com.supeyou.core.web.client.rpc.user2supporter;

import com.supeyou.core.iface.dto.User2SupporterDTO;
import com.supeyou.core.iface.dto.User2SupporterFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<User2SupporterDTO, User2SupporterFetchQuery> {

	public ListDataProvider(User2SupporterFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<User2SupporterDTO>() {

			@Override
			public void wasUpdated(User2SupporterDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(User2SupporterDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(User2SupporterDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<User2SupporterDTO, User2SupporterFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
