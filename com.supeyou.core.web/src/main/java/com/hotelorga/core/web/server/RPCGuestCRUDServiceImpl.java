package com.hotelorga.core.web.server;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.iface.dto.GuestFetchQuery;
import com.hotelorga.core.iface.dto.GuestGroupDTO;
import com.hotelorga.core.iface.dto.GuestGroupFetchQuery;
import com.hotelorga.core.iface.dto.paying.GuestCalculationDTO;
import com.hotelorga.core.iface.dto.paying.GuestsCalculationDTO;
import com.hotelorga.core.impl.GuestCRUDServiceImpl;
import com.hotelorga.core.impl.GuestGroupCRUDServiceImpl;
import com.hotelorga.core.web.client.rpc.guest.RPCCRUDService;
import com.hotelorga.foundation.iface.CRUDService;
import com.hotelorga.foundation.iface.datatype.CRUDException;
import com.hotelorga.foundation.iface.datatype.FetchQuery.SORTDIRECTION;
import com.hotelorga.foundation.iface.datatype.Page;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.iface.datatype.types.FileIDType;
import com.hotelorga.foundation.web.server.RPCAbstrCRUDServiceImpl;
import com.hotelorga.foundation.web.server.SessionStore;

@WebServlet("/RPCGuestCRUDServiceImpl")
public class RPCGuestCRUDServiceImpl extends RPCAbstrCRUDServiceImpl<GuestDTO, GuestFetchQuery> implements RPCCRUDService {

	private static final long serialVersionUID = 893034576566110L;

	@SuppressWarnings("unchecked")
	@Override
	public CRUDService<GuestDTO, GuestFetchQuery> getCRUDService() {

		// this reflection approach prevents compile-dependency to module com.hotelorga.foundation.impl
		// it could be replaced by spring eventually
		try {
			Class<?> clazz = Class.forName("com.hotelorga.core.impl.GuestCRUDServiceImpl");
			Class<?>[] params = new Class<?>[0];
			Method method_i = clazz.getMethod("i", params);
			Object[] objects = new Object[0];
			return (CRUDService<GuestDTO, GuestFetchQuery>) method_i.invoke(null, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public GuestCalculationDTO getGuestCalculationDTO(GuestDTO guestDTO, DateType from, DateType to) throws CRUDException {

		return GuestCRUDServiceImpl.i().getGuestCalculationDTO(SessionStore.getAuthenticatedActor(this.getThreadLocalRequest().getSession()), guestDTO, from, to);

	}

	@Override
	public GuestsCalculationDTO getGuestsCalculationDTO(GuestGroupDTO guestGroupDTO, DateType from, DateType to) throws CRUDException {

		return GuestCRUDServiceImpl.i().getGuestsCalculationDTO(SessionStore.getAuthenticatedActor(this.getThreadLocalRequest().getSession()), guestGroupDTO, from, to);

	}

	@Override
	public FileIDType renderBill(GuestsCalculationDTO guestsCalculationDTO, DateType from, DateType to) throws CRUDException {

		return GuestCRUDServiceImpl.i().renderBill(SessionStore.getAuthenticatedActor(this.getThreadLocalRequest().getSession()), guestsCalculationDTO);

	}

	@Override
	public FileIDType renderExcelOverview(DateType from, DateType to) throws CRUDException {

		List<GuestsCalculationDTO> guestsCalculationDTOs = new ArrayList<>();

		GuestGroupFetchQuery query = new GuestGroupFetchQuery();
		query.setOrderByColumn("name");
		query.setSortDirection(SORTDIRECTION.ASC);
		for (GuestGroupDTO guestGroup : GuestGroupCRUDServiceImpl.i().fetchList(SessionStore.getAuthenticatedActor(this.getThreadLocalRequest().getSession()), new Page(), query)) {

			guestsCalculationDTOs.add(GuestCRUDServiceImpl.i().getGuestsCalculationDTO(SessionStore.getAuthenticatedActor(this.getThreadLocalRequest().getSession()), guestGroup, from, to));

		}

		return GuestCRUDServiceImpl.i().renderExcelOverview(SessionStore.getAuthenticatedActor(this.getThreadLocalRequest().getSession()), guestsCalculationDTOs);

	}
}
