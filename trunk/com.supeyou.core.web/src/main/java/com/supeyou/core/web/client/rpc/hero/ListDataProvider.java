package com.supeyou.core.web.client.rpc.hero;

import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.HeroFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<HeroDTO, HeroFetchQuery> {

	public ListDataProvider(HeroFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<HeroDTO>() {

			@Override
			public void wasUpdated(HeroDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(HeroDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(HeroDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<HeroDTO, HeroFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
