package com.supeyou.core.web.client.rpc.guest;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.iface.dto.GuestFetchQuery;
import com.supeyou.core.iface.dto.GuestGroupDTO;
import com.supeyou.core.iface.dto.paying.GuestCalculationDTO;
import com.supeyou.core.iface.dto.paying.GuestsCalculationDTO;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.iface.datatype.types.FileIDType;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCGuestCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<GuestDTO, GuestFetchQuery> {

	GuestCalculationDTO getGuestCalculationDTO(GuestDTO guestDTO, DateType from, DateType to) throws CRUDException;

	GuestsCalculationDTO getGuestsCalculationDTO(GuestGroupDTO guestGroupDTO, DateType from, DateType to) throws CRUDException;

	FileIDType renderBill(GuestsCalculationDTO guestsCalculationDTO, DateType from, DateType to) throws CRUDException;

	FileIDType renderExcelOverview(DateType from, DateType to) throws CRUDException;

}