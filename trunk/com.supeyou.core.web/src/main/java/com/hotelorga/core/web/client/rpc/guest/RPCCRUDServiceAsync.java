package com.hotelorga.core.web.client.rpc.guest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.iface.dto.GuestFetchQuery;
import com.hotelorga.core.iface.dto.GuestGroupDTO;
import com.hotelorga.core.iface.dto.paying.GuestCalculationDTO;
import com.hotelorga.core.iface.dto.paying.GuestsCalculationDTO;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.iface.datatype.types.FileIDType;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<GuestDTO, GuestFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

	void getGuestCalculationDTO(GuestDTO guestDTO, DateType from, DateType to, AsyncCallback<GuestCalculationDTO> callback);

	void getGuestsCalculationDTO(GuestGroupDTO guestGroupDTO, DateType from, DateType to, AsyncCallback<GuestsCalculationDTO> callback);

	void renderBill(GuestsCalculationDTO guestsCalculationDTO, DateType from, DateType to, AsyncCallback<FileIDType> callback);

	void renderExcelOverview(DateType from, DateType to, AsyncCallback<FileIDType> callback);

}