package com.supeyou.crudie.web.client.rpc.abstr.crud;

import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.FetchQuery;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.datatype.types.FileIDType;
import com.supeyou.crudie.iface.dto.DTOFetchList;

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