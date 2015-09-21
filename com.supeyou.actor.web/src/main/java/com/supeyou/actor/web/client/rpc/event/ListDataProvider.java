package com.supeyou.actor.web.client.rpc.event;

import com.supeyou.actor.iface.dto.EventDTO;
import com.supeyou.actor.iface.dto.EventFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<EventDTO, EventFetchQuery> {

	public ListDataProvider(EventFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<EventDTO>() {

			@Override
			public void wasUpdated(EventDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(EventDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(EventDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<EventDTO, EventFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
