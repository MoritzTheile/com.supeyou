package com.supeyou.core.web.client.rpc.supporter2donation;

import com.supeyou.core.iface.dto.Supporter2DonationDTO;
import com.supeyou.core.iface.dto.Supporter2DonationFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class ListDataProvider extends AbstrListDataProvider<Supporter2DonationDTO, Supporter2DonationFetchQuery> {

	public ListDataProvider(Supporter2DonationFetchQuery fetchQuery) {
		super(fetchQuery);

		RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<Supporter2DonationDTO>() {

			@Override
			public void wasUpdated(Supporter2DonationDTO dto) {
				// nothing

			}

			@Override
			public void wasDeleted(Supporter2DonationDTO dto) {

				fetchData();

			}

			@Override
			public void wasCreated(Supporter2DonationDTO dto) {
				fetchData();

			}
		});

	}

	@Override
	public RPCAbstractCRUDServiceAsync<Supporter2DonationDTO, Supporter2DonationFetchQuery> getAbstractCRUDService() {

		return RPCCRUDServiceAsync.i;
	}

}
