package com.supeyou.core.web.client.rpc.donation;

import com.supeyou.core.iface.dto.DonationDTO;
import com.supeyou.core.iface.dto.DonationFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<DonationDTO, DonationFetchQuery> {

	public ListDataProvider(DonationFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<DonationDTO>() {

			@Override
			public void wasUpdated(DonationDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(DonationDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(DonationDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<DonationDTO, DonationFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
