package com.hotelorga.foundation.web.client.rpc.abstr.crud;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hotelorga.foundation.iface.datatype.FetchQuery;
import com.hotelorga.foundation.iface.datatype.Page;
import com.hotelorga.foundation.iface.datatype.types.AbstrType;
import com.hotelorga.foundation.iface.datatype.types.FileIDType;
import com.hotelorga.foundation.iface.dto.DTOFetchList;

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