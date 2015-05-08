package com.hotelorga.core.web.client.rpc.guest2acceptance.form;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hotelorga.core.iface.dto.AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2AcceptanceFetchQuery;
import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.web.client.rpc.guest2acceptance.RPCCRUDProxy;
import com.hotelorga.core.web.client.rpc.guest2acceptance.form.fields.FieldsWidget;
import com.hotelorga.foundation.web.client.model.AbstrObservable.Observer;
import com.hotelorga.foundation.web.client.resources.GWTSTATICS;
import com.hotelorga.foundation.web.client.resources.i18n.Text;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;

public class FormWidget extends WidgetView {

	// needed for change detection
	private GuestDTO origDtoA;
	private AcceptanceDTO origDtoB;

	public FormWidget(AbstrListDataProvider<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery> dataProvider, Guest2AcceptanceDTO userDTO, boolean showAssoAField, boolean showAssoBField) {

		init(dataProvider, userDTO, showAssoAField, showAssoBField);

	}

	public FormWidget(AbstrListDataProvider<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery> dataProvider, boolean showAssoAField, boolean showAssoBField) {
		Guest2AcceptanceDTO dto = new Guest2AcceptanceDTO();

		init(dataProvider, dto, showAssoAField, showAssoBField);

	}

	private void init(AbstrListDataProvider<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery> dataProvider, final Guest2AcceptanceDTO userDTO, boolean showAssoAField, boolean showAssoBField) {

		origDtoA = userDTO.getDtoA();
		origDtoB = userDTO.getDtoB();

		dataProvider.addObserver(new Observer<Void>() {

			@Override
			public void modelHasChanged(Void changes) {

				cancel();

			}

		});

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

	private void save(final Guest2AcceptanceDTO dto) {
		RPCCRUDProxy.i().updadd(dto, new AsyncCallback<Guest2AcceptanceDTO>() {

			@Override
			public void onSuccess(Guest2AcceptanceDTO result) {

				if (!GWTSTATICS.nullsafeEquals(origDtoA, dto.getDtoA())) {
					com.hotelorga.core.web.client.rpc.guest.RPCCRUDProxy.i().wasUpdated(origDtoA);
					com.hotelorga.core.web.client.rpc.guest.RPCCRUDProxy.i().wasUpdated(dto.getDtoA());
				}
				if (!GWTSTATICS.nullsafeEquals(origDtoB, dto.getDtoB())) {
					com.hotelorga.core.web.client.rpc.acceptance.RPCCRUDProxy.i().wasUpdated(origDtoB);
					com.hotelorga.core.web.client.rpc.acceptance.RPCCRUDProxy.i().wasUpdated(dto.getDtoB());
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
