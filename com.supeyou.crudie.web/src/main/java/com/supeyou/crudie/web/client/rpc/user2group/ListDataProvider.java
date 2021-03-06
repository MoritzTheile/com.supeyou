package com.supeyou.crudie.web.client.rpc.user2group;

import com.supeyou.crudie.iface.dto.User2GroupDTO;
import com.supeyou.crudie.iface.dto.User2GroupFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<User2GroupDTO, User2GroupFetchQuery> {

	public ListDataProvider(User2GroupFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<User2GroupDTO>() {

			@Override
			public void wasUpdated(User2GroupDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(User2GroupDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(User2GroupDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<User2GroupDTO, User2GroupFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
