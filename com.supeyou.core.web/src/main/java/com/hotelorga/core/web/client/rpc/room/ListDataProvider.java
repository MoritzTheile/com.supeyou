package com.hotelorga.core.web.client.rpc.room;

import com.hotelorga.core.iface.dto.RoomDTO;
import com.hotelorga.core.iface.dto.RoomFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<RoomDTO, RoomFetchQuery> {

	public ListDataProvider(RoomFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<RoomDTO>() {

			@Override
			public void wasUpdated(RoomDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(RoomDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(RoomDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<RoomDTO, RoomFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
