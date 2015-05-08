package com.hotelorga.foundation.web.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.hotelorga.foundation.iface.CRUDService;
import com.hotelorga.foundation.iface.datatype.CRUDException;
import com.hotelorga.foundation.iface.datatype.FetchQuery;
import com.hotelorga.foundation.iface.datatype.Page;
import com.hotelorga.foundation.iface.datatype.types.AbstrType;
import com.hotelorga.foundation.iface.datatype.types.FileIDType;
import com.hotelorga.foundation.iface.dto.AbstrDTO;
import com.hotelorga.foundation.iface.dto.DTOFetchList;
import com.hotelorga.foundation.iface.dto.UserDTO;

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
		return SessionStore.getAuthenticatedActor(this.getThreadLocalRequest().getSession());
	}
}
