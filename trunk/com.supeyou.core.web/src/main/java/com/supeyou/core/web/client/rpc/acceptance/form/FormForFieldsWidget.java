package com.supeyou.core.web.client.rpc.acceptance.form;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.AcceptanceFetchQuery;
import com.supeyou.core.web.client.rpc.acceptance.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.acceptance.form.fields.FieldsWidget;
import com.supeyou.crudie.web.client.model.AbstrObservable.Observer;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class FormForFieldsWidget extends WidgetView {

	private AcceptanceDTO thisDTO;

	public FormForFieldsWidget(AbstrListDataProvider<AcceptanceDTO, AcceptanceFetchQuery> dataProvider, AcceptanceDTO dto) {
		thisDTO = dto;
		init(dataProvider);

	}

	private void init(AbstrListDataProvider<AcceptanceDTO, AcceptanceFetchQuery> dataProvider) {

		if (dataProvider != null) {
			dataProvider.addObserver(new Observer<Void>() {

				@Override
				public void modelHasChanged(Void changes) {

					// cancel();

				}

			});
		}

		deleteButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				if (!Window.confirm("ACHTUNG, wollen Sie tatsächlich die Kostenübernahme für alle Gäste löschen? (Um einen einzelnen Gast aus der Kostenübernahme zu entfernen müssen Sie den Gast wählen.")) {
					return;
				}

				RPCCRUDProxy.i().delete(thisDTO);
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

				save(thisDTO);

			}

		});

		this.addDomHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {

				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {

					save(thisDTO);

				}

				if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {

					cancel();

				}

			}

		}, KeyDownEvent.getType());

		render();
	}

	private void render() {

		if (thisDTO.getId() == null) {
			deleteButton.setVisible(false);
		}

		final FieldsWidget formWidget = new FieldsWidget(thisDTO);
		formSlot.clear();
		formSlot.add(formWidget);

		if (thisDTO.getId() == null) {
			saveButton.setText("Weiter >>");
		} else {
			saveButton.setText("Speichern");
		}
	}

	private void save(AcceptanceDTO dto) {

		RPCCRUDProxy.i().updadd(dto, new AsyncCallback<AcceptanceDTO>() {

			@Override
			public void onSuccess(AcceptanceDTO result) {

				if (thisDTO.getId() != null) {
					thisDTO = result;
					updadded(result);
					close();
				} else {
					thisDTO = result;
					render();
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				// nothing

			}
		});

	}

	private void cancel() {

		close();
	}

	protected void close() {
		// can be overwritten
	}

	protected void updadded(AcceptanceDTO dto) {
		// can be overwritten
	}

}
