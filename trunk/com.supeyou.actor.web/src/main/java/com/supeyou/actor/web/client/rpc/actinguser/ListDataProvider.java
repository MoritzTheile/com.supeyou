package com.supeyou.actor.web.client.rpc.actinguser;

import com.supeyou.actor.iface.dto.ActingUserDTO;
import com.supeyou.actor.iface.dto.ActingUserFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<ActingUserDTO, ActingUserFetchQuery> {

	public ListDataProvider(ActingUserFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<ActingUserDTO>() {

			@Override
			public void wasUpdated(ActingUserDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(ActingUserDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(ActingUserDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<ActingUserDTO, ActingUserFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
