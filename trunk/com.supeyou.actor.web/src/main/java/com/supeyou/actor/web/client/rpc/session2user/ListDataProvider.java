package com.supeyou.actor.web.client.rpc.session2user;

import com.supeyou.actor.iface.dto.Session2UserDTO;
import com.supeyou.actor.iface.dto.Session2UserFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<Session2UserDTO, Session2UserFetchQuery> {

	public ListDataProvider(Session2UserFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<Session2UserDTO>() {

			@Override
			public void wasUpdated(Session2UserDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(Session2UserDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(Session2UserDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<Session2UserDTO, Session2UserFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
