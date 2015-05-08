package com.hotelorga.core.web.client.rpc.guest.form;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.iface.dto.GuestFetchQuery;
import com.hotelorga.core.web.client.rpc.guest.RPCCRUDProxy;
import com.hotelorga.core.web.client.rpc.guest.form.fields.FieldsWidget;
import com.hotelorga.foundation.web.client.model.AbstrObservable.Observer;
import com.hotelorga.foundation.web.client.resources.i18n.Text;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;

public class FormWidget extends WidgetView {

	public FormWidget(AbstrListDataProvider<GuestDTO, GuestFetchQuery> dataProvider, GuestDTO userDTO) {

		init(dataProvider, userDTO);

	}

	public FormWidget(AbstrListDataProvider<GuestDTO, GuestFetchQuery> dataProvider) {

		init(dataProvider, new GuestDTO());

	}

	private void init(AbstrListDataProvider<GuestDTO, GuestFetchQuery> dataProvider, final GuestDTO dto) {

		if (dataProvider != null) {
			dataProvider.addObserver(new Observer<Void>() {

				@Override
				public void modelHasChanged(Void changes) {

					cancel();

				}

			});
		}
		final FieldsWidget formWidget = new FieldsWidget(dto);

		formSlot.add(formWidget);

		if (dto.getId() == null) {
			saveButton.setText(Text.i.MULTIUSE_Create());
		}

		deleteButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				RPCCRUDProxy.i().delete(dto);
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

				save(dto);

			}

		});

		this.addDomHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {

				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {

					save(dto);

				}

				if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {

					cancel();

				}

			}

		}, KeyDownEvent.getType());

		if (dto.getId() == null) {
			deleteButton.setVisible(false);
		}
	}

	private void save(GuestDTO userDTO) {
		RPCCRUDProxy.i().updadd(userDTO, new AsyncCallback<GuestDTO>() {

			@Override
			public void onSuccess(GuestDTO result) {
				updadded(result);

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

	protected void updadded(GuestDTO dto) {
		// can be overwritten
	}

}
