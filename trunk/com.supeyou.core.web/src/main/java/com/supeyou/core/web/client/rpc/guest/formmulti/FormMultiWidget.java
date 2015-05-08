package com.supeyou.core.web.client.rpc.guest.formmulti;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.web.client.rpc.guest.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.guest.formmulti.fields.FieldsWidget;

public class FormMultiWidget extends WidgetView {

	private final List<GuestDTO> dtos;

	public FormMultiWidget(final List<GuestDTO> dtos) {

		this.dtos = dtos;

		final GuestDTO dto = new GuestDTO();
		final FieldsWidget formWidget = new FieldsWidget(dto);

		formSlot.add(formWidget);

		deleteButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();
				for (GuestDTO dto : dtos) {
					RPCCRUDProxy.i().delete(dto);
				}
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

		deleteButton.setVisible(false);
	}

	private void save(GuestDTO userDTO) {
		for (GuestDTO dto : dtos) {
			copyFromTo(userDTO, dto);
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
		}
		close();
	}

	private void copyFromTo(GuestDTO sourceDTO, GuestDTO targetDTO) {
		if (sourceDTO.getTitle() != null) {
			targetDTO.setTitle(sourceDTO.getTitle());
		}

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
