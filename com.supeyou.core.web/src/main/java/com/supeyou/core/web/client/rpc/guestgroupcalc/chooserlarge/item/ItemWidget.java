package com.supeyou.core.web.client.rpc.guestgroupcalc.chooserlarge.item;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.Guest2AcceptanceDTO;
import com.supeyou.core.iface.dto.Guest2GuestGroupDTO;
import com.supeyou.core.iface.dto.Guest2RoomDTO;
import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.iface.dto.GuestGroupDTO;
import com.supeyou.core.iface.dto.paying.GuestsCalculationDTO;
import com.supeyou.core.web.client.rpc.guestgroup.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.guestgroupcalc.DownloadBillLink;
import com.supeyou.core.web.client.rpc.guestgroupcalc.chooserlarge.item.guestscalculation.GuestsCalculationWidget;
import com.supeyou.core.web.client.rpc.guestgroupcalc.createacceptanceforall.CreateAcceptanceForAll;
import com.supeyou.core.web.client.rpc.guestgroupcalc.createguestgroupforall.CreateGuest2RoomForAll;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.ListenerHandler;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private GuestGroupDTO thisDTO;

	private DateType from;
	private DateType to;

	public ItemWidget(GuestGroupDTO dto, DateType from, DateType to) {

		this.thisDTO = dto;

		this.from = from;
		this.to = to;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		getDataAndRender();

	}

	private void getDataAndRender() {

		com.supeyou.core.web.client.rpc.guest.RPCCRUDServiceAsync.i.getGuestsCalculationDTO(thisDTO, from, to, new AsyncCallback<GuestsCalculationDTO>() {

			@Override
			public void onSuccess(GuestsCalculationDTO result) {
				render(result);

			}

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();

			}
		});
	}

	private void render(GuestsCalculationDTO result) {

		slot1.clear();
		slot1.add(new Label(renderInfoString()));

		slot2.clear();
		slot2.add(new GuestsCalculationWidget(result));
		slot2.add(new CreateGuest2RoomForAll(thisDTO, from, to));
		slot2.add(new CreateAcceptanceForAll(thisDTO, from, to));
		slot2.add(new DownloadBillLink(result, from, to));

	}

	private String renderInfoString() {

		String infos = "Rechnungsgruppe ";

		if (thisDTO.getName() != null) {
			infos += thisDTO.getName().value();
		}

		return infos;

	}

	private CRUDProxyListener<GuestGroupDTO> listener = new CRUDProxyListener<GuestGroupDTO>() {

		@Override
		public void wasUpdated(GuestGroupDTO dto) {

			final String updatedStyle = "updated";

			thisWidget.removeStyleName(updatedStyle);

			if (thisDTO.equals(dto)) {

				thisDTO = dto;

				// without the delay the change in style doesn't get picked up
				new Timer() {

					@Override
					public void run() {

						thisWidget.addStyleName(updatedStyle);

					}
				}.schedule(100);

				getDataAndRender();
			}
		}

		@Override
		public void wasDeleted(GuestGroupDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(GuestGroupDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

	private List<ListenerHandler> listenerHandlers = new ArrayList<ListenerHandler>();

	@Override
	protected void onUnload() {

		super.onUnload();

		unregisterListeners();

	}

	private void unregisterListeners() {

		for (ListenerHandler listenerHandler : listenerHandlers) {
			listenerHandler.remove();
		}

	}

	@Override
	protected void onLoad() {

		listenerHandlers.add(com.supeyou.core.web.client.rpc.guest.RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<GuestDTO>() {

			@Override
			public void wasUpdated(GuestDTO dto) {
				getDataAndRender();

			}

			@Override
			public void wasDeleted(GuestDTO dto) {
				getDataAndRender();

			}

			@Override
			public void wasCreated(GuestDTO dto) {
				getDataAndRender();

			}
		}));

		listenerHandlers.add(com.supeyou.core.web.client.rpc.guest2guestgroup.RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<Guest2GuestGroupDTO>() {

			@Override
			public void wasUpdated(Guest2GuestGroupDTO dto) {
				getDataAndRender();

			}

			@Override
			public void wasDeleted(Guest2GuestGroupDTO dto) {
				getDataAndRender();

			}

			@Override
			public void wasCreated(Guest2GuestGroupDTO dto) {
				getDataAndRender();

			}
		}));

		listenerHandlers.add(com.supeyou.core.web.client.rpc.guestgroup.RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<GuestGroupDTO>() {

			@Override
			public void wasUpdated(GuestGroupDTO dto) {
				getDataAndRender();

			}

			@Override
			public void wasDeleted(GuestGroupDTO dto) {
				getDataAndRender();

			}

			@Override
			public void wasCreated(GuestGroupDTO dto) {
				getDataAndRender();

			}
		}));

		listenerHandlers.add(com.supeyou.core.web.client.rpc.guest2room.RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<Guest2RoomDTO>() {

			@Override
			public void wasUpdated(Guest2RoomDTO dto) {
				getDataAndRender();

			}

			@Override
			public void wasDeleted(Guest2RoomDTO dto) {
				getDataAndRender();
			}

			@Override
			public void wasCreated(Guest2RoomDTO dto) {
				getDataAndRender();

			}
		}));

		listenerHandlers.add(com.supeyou.core.web.client.rpc.guest2acceptance.RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<Guest2AcceptanceDTO>() {

			@Override
			public void wasUpdated(Guest2AcceptanceDTO dto) {
				getDataAndRender();

			}

			@Override
			public void wasDeleted(Guest2AcceptanceDTO dto) {
				getDataAndRender();
			}

			@Override
			public void wasCreated(Guest2AcceptanceDTO dto) {
				getDataAndRender();

			}
		}));

		listenerHandlers.add(com.supeyou.core.web.client.rpc.acceptance.RPCCRUDProxy.i().addListenerForAllDTOs(new CRUDProxyListener<AcceptanceDTO>() {

			@Override
			public void wasUpdated(AcceptanceDTO dto) {
				getDataAndRender();

			}

			@Override
			public void wasDeleted(AcceptanceDTO dto) {
				getDataAndRender();
			}

			@Override
			public void wasCreated(AcceptanceDTO dto) {
				getDataAndRender();

			}
		}));

		super.onLoad();
	}

}
