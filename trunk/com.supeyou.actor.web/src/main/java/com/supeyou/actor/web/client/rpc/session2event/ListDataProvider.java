package com.supeyou.actor.web.client.rpc.session2event;

import com.supeyou.actor.iface.dto.Session2EventDTO;
import com.supeyou.actor.iface.dto.Session2EventFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<Session2EventDTO, Session2EventFetchQuery> {

	public ListDataProvider(Session2EventFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<Session2EventDTO>() {

			@Override
			public void wasUpdated(Session2EventDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(Session2EventDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(Session2EventDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<Session2EventDTO, Session2EventFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
