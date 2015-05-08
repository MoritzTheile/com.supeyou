package com.supeyou.crudie.web.client.rpc.file;

import com.supeyou.crudie.iface.dto.FileDTO;
import com.supeyou.crudie.iface.dto.FileFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<FileDTO, FileFetchQuery> {

	public ListDataProvider(FileFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<FileDTO>() {

			@Override
			public void wasUpdated(FileDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(FileDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(FileDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<FileDTO, FileFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
