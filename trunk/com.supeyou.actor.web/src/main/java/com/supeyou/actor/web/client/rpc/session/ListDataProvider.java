package com.supeyou.actor.web.client.rpc.session;

import com.supeyou.crudie.iface.dto.GroupDTO;
import com.supeyou.crudie.iface.dto.GroupFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<GroupDTO, GroupFetchQuery> {

	public ListDataProvider(GroupFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<GroupDTO>() {

			@Override
			public void wasUpdated(GroupDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(GroupDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(GroupDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<GroupDTO, GroupFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
