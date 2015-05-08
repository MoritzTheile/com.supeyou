package com.hotelorga.core.web.client.rpc.guest2guestgroup;

import com.hotelorga.core.iface.dto.Guest2GuestGroupDTO;
import com.hotelorga.core.iface.dto.Guest2GuestGroupFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<Guest2GuestGroupDTO, Guest2GuestGroupFetchQuery> {

	public ListDataProvider(Guest2GuestGroupFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<Guest2GuestGroupDTO>() {

			@Override
			public void wasUpdated(Guest2GuestGroupDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(Guest2GuestGroupDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(Guest2GuestGroupDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<Guest2GuestGroupDTO, Guest2GuestGroupFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
