package com.supeyou.actor.web.client.rpc.session;

import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.iface.dto.SessionFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<SessionDTO, SessionFetchQuery> {

	public ListDataProvider(SessionFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<SessionDTO>() {

			@Override
			public void wasUpdated(SessionDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(SessionDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(SessionDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<SessionDTO, SessionFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
