package com.supeyou.actor.web.client.rpc.actinguser2session;

import com.supeyou.actor.iface.dto.ActingUser2SessionDTO;
import com.supeyou.actor.iface.dto.ActingUser2SessionFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<ActingUser2SessionDTO, ActingUser2SessionFetchQuery> {

	public ListDataProvider(ActingUser2SessionFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<ActingUser2SessionDTO>() {

			@Override
			public void wasUpdated(ActingUser2SessionDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(ActingUser2SessionDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(ActingUser2SessionDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<ActingUser2SessionDTO, ActingUser2SessionFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
