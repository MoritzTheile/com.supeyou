package com.supeyou.core.web.client.rpc.guest2room.form;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.Guest2RoomDTO;
import com.supeyou.core.iface.dto.Guest2RoomFetchQuery;
import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.iface.dto.RoomDTO;
import com.supeyou.core.web.client.rpc.guest2room.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.guest2room.form.fields.FieldsWidget;
import com.supeyou.crudie.web.client.model.AbstrObservable.Observer;
import com.supeyou.crudie.web.client.resources.GWTSTATICS;
import com.supeyou.crudie.web.client.resources.i18n.Text;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class FormWidget extends WidgetView {

	// needed for change detection
	private GuestDTO origDtoA;
	private RoomDTO origDtoB;

	public FormWidget(AbstrListDataProvider<Guest2RoomDTO, Guest2RoomFetchQuery> dataProvider, Guest2RoomDTO userDTO, boolean showAssoAField, boolean showAssoBField) {

		init(dataProvider, userDTO, showAssoAField, showAssoBField);

	}

	public FormWidget(AbstrListDataProvider<Guest2RoomDTO, Guest2RoomFetchQuery> dataProvider, boolean showAssoAField, boolean showAssoBField) {
		Guest2RoomDTO dto = new Guest2RoomDTO();

		init(dataProvider, dto, showAssoAField, showAssoBField);

	}

	private void init(AbstrListDataProvider<Guest2RoomDTO, Guest2RoomFetchQuery> dataProvider, final Guest2RoomDTO userDTO, boolean showAssoAField, boolean showAssoBField) {

		origDtoA = userDTO.getDtoA();
		origDtoB = userDTO.getDtoB();

		if (dataProvider != null) {
			dataProvider.addObserver(new Observer<Void>() {

				@Override
				public void modelHasChanged(Void changes) {

					cancel();

				}

			});
		}

		final FieldsWidget fieldsWidget = new FieldsWidget(userDTO, showAssoAField, showAssoBField);

		formSlot.add(fieldsWidget);

		if (userDTO.getId() == null) {
			saveButton.setText(Text.i.MULTIUSE_Create());
		}

		deleteButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				RPCCRUDProxy.i().delete(userDTO);
				close();

			}
		});

		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				cancel();

			}
		});

		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				save(userDTO);

			}

		});

		this.addDomHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {

				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {

					save(userDTO);

				}

				if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {

					cancel();

				}

			}

		}, KeyDownEvent.getType());

	}

	private void save(final Guest2RoomDTO dto) {
		RPCCRUDProxy.i().updadd(dto, new AsyncCallback<Guest2RoomDTO>() {

			@Override
			public void onSuccess(Guest2RoomDTO result) {

				if (!GWTSTATICS.nullsafeEquals(origDtoA, dto.getDtoA())) {
					com.supeyou.core.web.client.rpc.guest.RPCCRUDProxy.i().wasUpdated(origDtoA);
					com.supeyou.core.web.client.rpc.guest.RPCCRUDProxy.i().wasUpdated(dto.getDtoA());
				}
				if (!GWTSTATICS.nullsafeEquals(origDtoB, dto.getDtoB())) {
					com.supeyou.core.web.client.rpc.room.RPCCRUDProxy.i().wasUpdated(origDtoB);
					com.supeyou.core.web.client.rpc.room.RPCCRUDProxy.i().wasUpdated(dto.getDtoB());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// nothing

			}
		});
		close();
	}

	private void cancel() {

		close();
	}

	protected void close() {
		// can be overwritten
	}

}
