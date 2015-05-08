package com.supeyou.core.web.client.rpc.guest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.iface.dto.GuestFetchQuery;
import com.supeyou.core.iface.dto.GuestGroupDTO;
import com.supeyou.core.iface.dto.paying.GuestCalculationDTO;
import com.supeyou.core.iface.dto.paying.GuestsCalculationDTO;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.iface.datatype.types.FileIDType;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public interface RPCCRUDServiceAsync extends RPCAbstractCRUDServiceAsync<GuestDTO, GuestFetchQuery> {

	static final RPCCRUDServiceAsync i = GWT.create(RPCCRUDService.class);

	void getGuestCalculationDTO(GuestDTO guestDTO, DateType from, DateType to, AsyncCallback<GuestCalculationDTO> callback);

	void getGuestsCalculationDTO(GuestGroupDTO guestGroupDTO, DateType from, DateType to, AsyncCallback<GuestsCalculationDTO> callback);

	void renderBill(GuestsCalculationDTO guestsCalculationDTO, DateType from, DateType to, AsyncCallback<FileIDType> callback);

	void renderExcelOverview(DateType from, DateType to, AsyncCallback<FileIDType> callback);

}