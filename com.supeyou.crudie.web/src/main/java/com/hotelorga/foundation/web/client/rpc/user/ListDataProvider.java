package com.hotelorga.foundation.web.client.rpc.user;

import com.hotelorga.foundation.iface.dto.UserDTO;
import com.hotelorga.foundation.iface.dto.UserFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<UserDTO, UserFetchQuery> {

	public ListDataProvider(UserFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<UserDTO>() {

			@Override
			public void wasUpdated(UserDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(UserDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(UserDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<UserDTO, UserFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
