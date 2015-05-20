package com.supeyou.crudie.web.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.FetchQuery;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.datatype.types.FileIDType;
import com.supeyou.crudie.iface.dto.AbstrDTO;
import com.supeyou.crudie.iface.dto.DTOFetchList;
import com.supeyou.crudie.iface.dto.UserDTO;

public abstract class RPCAbstrCRUDServiceImpl<D extends AbstrDTO<?>, F extends FetchQuery> extends RemoteServiceServlet {

	private static final long serialVersionUID = -27013432633344054L;

	private final CRUDService<D, F> crudService;

	public RPCAbstrCRUDServiceImpl() {

		crudService = getCRUDService();

	}

	public abstract CRUDService<D, F> getCRUDService();

	public DTOFetchList<D> fetchList(
			Page page,
			F query
			) throws CRUDException {

		return crudService.fetchList(getActor(), page, query);

	}

	public D get(AbstrType<Long> dtoId) throws CRUDException {

		return crudService.get(getActor(), dtoId);

	}

	public D updadd(
			D dto
			) throws CRUDException {

		return crudService.updadd(getActor(), dto);

	}

	public void delete(
			AbstrType<Long> dtoId
			) throws CRUDException {

		crudService.delete(getActor(), dtoId);

	}

	public FileIDType exportData(
			F query
			) throws CRUDException {

		return crudService.exportData(getActor(), query);

	}

	public void importData(FileIDType fileIDType) throws CRUDException {

		crudService.importData(getActor(), fileIDType);

	}

	public UserDTO getActor() {
		return SessionStore.getActor(this.getThreadLocalRequest().getSession());
	}
}
