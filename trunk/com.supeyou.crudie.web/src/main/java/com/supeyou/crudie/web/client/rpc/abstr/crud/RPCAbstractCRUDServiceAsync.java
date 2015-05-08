package com.supeyou.crudie.web.client.rpc.abstr.crud;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.crudie.iface.datatype.FetchQuery;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.datatype.types.FileIDType;
import com.supeyou.crudie.iface.dto.DTOFetchList;

public interface RPCAbstractCRUDServiceAsync<D, F extends FetchQuery> {

	void fetchList(
			Page page,
			F query,
			AsyncCallback<DTOFetchList<D>> callback
			);

	void get(AbstrType<Long> dtoId,
			AsyncCallback<D> callback
			);

	void updadd(
			D dto,
			AsyncCallback<D> callback
			);

	void delete(
			AbstrType<Long> dtoId,
			AsyncCallback<Void> callback
			);

	void exportData(
			F query,
			AsyncCallback<FileIDType> callback
			);

	void importData(
			FileIDType fileIDType,
			AsyncCallback<Void> callback
			);
}