package com.hotelorga.core.web.client.rpc.acceptance2payer.form;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hotelorga.core.iface.dto.Acceptance2PayerDTO;
import com.hotelorga.core.iface.dto.Acceptance2PayerFetchQuery;
import com.hotelorga.core.iface.dto.AcceptanceDTO;
import com.hotelorga.core.iface.dto.PayerDTO;
import com.hotelorga.core.web.client.rpc.acceptance2payer.RPCCRUDProxy;
import com.hotelorga.core.web.client.rpc.acceptance2payer.form.fields.FieldsWidget;
import com.hotelorga.foundation.web.client.model.AbstrObservable.Observer;
import com.hotelorga.foundation.web.client.resources.GWTSTATICS;
import com.hotelorga.foundation.web.client.resources.i18n.Text;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;

public class FormWidget extends WidgetView {

	// needed for change detection
	private AcceptanceDTO origDtoA;
	private PayerDTO origDtoB;

	public FormWidget(AbstrListDataProvider<Acceptance2PayerDTO, Acceptance2PayerFetchQuery> dataProvider, Acceptance2PayerDTO userDTO, boolean showAssoAField, boolean showAssoBField) {

		init(dataProvider, userDTO, showAssoAField, showAssoBField);

	}

	public FormWidget(AbstrListDataProvider<Acceptance2PayerDTO, Acceptance2PayerFetchQuery> dataProvider, boolean showAssoAField, boolean showAssoBField) {
		Acceptance2PayerDTO dto = new Acceptance2PayerDTO();

		init(dataProvider, dto, showAssoAField, showAssoBField);

	}

	private void init(AbstrListDataProvider<Acceptance2PayerDTO, Acceptance2PayerFetchQuery> dataProvider, final Acceptance2PayerDTO userDTO, boolean showAssoAField, boolean showAssoBField) {

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

	private void save(final Acceptance2PayerDTO dto) {
		RPCCRUDProxy.i().updadd(dto, new AsyncCallback<Acceptance2PayerDTO>() {

			@Override
			public void onSuccess(Acceptance2PayerDTO result) {

				if (!GWTSTATICS.nullsafeEquals(origDtoA, dto.getDtoA())) {
					com.hotelorga.core.web.client.rpc.acceptance.RPCCRUDProxy.i().wasUpdated(origDtoA);
					com.hotelorga.core.web.client.rpc.acceptance.RPCCRUDProxy.i().wasUpdated(dto.getDtoA());
				}
				if (!GWTSTATICS.nullsafeEquals(origDtoB, dto.getDtoB())) {
					com.hotelorga.core.web.client.rpc.payer.RPCCRUDProxy.i().wasUpdated(origDtoB);
					com.hotelorga.core.web.client.rpc.payer.RPCCRUDProxy.i().wasUpdated(dto.getDtoB());
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
