package com.hotelorga.foundation.web.client.rpc.abstr.crud;

import com.hotelorga.foundation.iface.datatype.CRUDException;
import com.hotelorga.foundation.iface.datatype.FetchQuery;
import com.hotelorga.foundation.iface.datatype.Page;
import com.hotelorga.foundation.iface.datatype.types.AbstrType;
import com.hotelorga.foundation.iface.datatype.types.FileIDType;
import com.hotelorga.foundation.iface.dto.DTOFetchList;

public interface RPCAbstractCRUDService<D, F extends FetchQuery> {

	DTOFetchList<D> fetchList(
			Page page,
			F query
			) throws CRUDException;

	D get(AbstrType<Long> dtoId) throws CRUDException;

	D updadd(
			D dto
			) throws CRUDException;

	void delete(
			AbstrType<Long> dtoId
			) throws CRUDException;

	FileIDType exportData(
			F query
			) throws CRUDException;

	void importData(
			FileIDType fileIDType
			) throws CRUDException;

}