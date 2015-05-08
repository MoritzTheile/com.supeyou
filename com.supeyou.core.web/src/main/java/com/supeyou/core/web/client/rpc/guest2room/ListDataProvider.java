package com.supeyou.core.web.client.rpc.guest2room;

import com.supeyou.core.iface.dto.Guest2RoomDTO;
import com.supeyou.core.iface.dto.Guest2RoomFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<Guest2RoomDTO, Guest2RoomFetchQuery> {

	public ListDataProvider(Guest2RoomFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<Guest2RoomDTO>() {

			@Override
			public void wasUpdated(Guest2RoomDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(Guest2RoomDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(Guest2RoomDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<Guest2RoomDTO, Guest2RoomFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
