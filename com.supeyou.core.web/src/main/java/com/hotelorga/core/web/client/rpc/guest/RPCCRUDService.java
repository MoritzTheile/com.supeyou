package com.hotelorga.core.web.client.rpc.guest;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.iface.dto.GuestFetchQuery;
import com.hotelorga.core.iface.dto.GuestGroupDTO;
import com.hotelorga.core.iface.dto.paying.GuestCalculationDTO;
import com.hotelorga.core.iface.dto.paying.GuestsCalculationDTO;
import com.hotelorga.foundation.iface.datatype.CRUDException;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.iface.datatype.types.FileIDType;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDService;

@RemoteServiceRelativePath("../RPCGuestCRUDServiceImpl")
public interface RPCCRUDService extends RemoteService, RPCAbstractCRUDService<GuestDTO, GuestFetchQuery> {

	GuestCalculationDTO getGuestCalculationDTO(GuestDTO guestDTO, DateType from, DateType to) throws CRUDException;

	GuestsCalculationDTO getGuestsCalculationDTO(GuestGroupDTO guestGroupDTO, DateType from, DateType to) throws CRUDException;

	FileIDType renderBill(GuestsCalculationDTO guestsCalculationDTO, DateType from, DateType to) throws CRUDException;

	FileIDType renderExcelOverview(DateType from, DateType to) throws CRUDException;

}