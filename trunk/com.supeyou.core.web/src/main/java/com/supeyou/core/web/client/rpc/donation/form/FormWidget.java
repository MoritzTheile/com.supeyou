package com.supeyou.core.web.client.rpc.donation.form;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.DonationDTO;
import com.supeyou.core.iface.dto.DonationFetchQuery;
import com.supeyou.core.web.client.rpc.donation.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.donation.form.fields.FieldsWidget;
import com.supeyou.crudie.web.client.model.LoginStateModel;
import com.supeyou.crudie.web.client.model.AbstrObservable.Observer;
import com.supeyou.crudie.web.client.resources.GWTSTATICS;
import com.supeyou.crudie.web.client.resources.i18n.Text;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class FormWidget extends WidgetView {

	public FormWidget(AbstrListDataProvider<DonationDTO, DonationFetchQuery> dataProvider, DonationDTO userDTO) {

		init(dataProvider, userDTO);

	}

	public FormWidget(AbstrListDataProvider<DonationDTO, DonationFetchQuery> dataProvider) {

		init(dataProvider, new DonationDTO());

	}

	private void init(AbstrListDataProvider<DonationDTO, DonationFetchQuery> dataProvider, final DonationDTO dto) {

		if (LoginStateModel.i().userIsAdmin()) {
			deleteButton.removeStyleName(GWTSTATICS.HIDESTYLE_CSS);
		} else {
			deleteButton.addStyleName(GWTSTATICS.HIDESTYLE_CSS);

		}

		dataProvider.addObserver(new Observer<Void>() {

			@Override
			public void modelHasChanged(Void changes) {

				cancel();

			}

		});

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

	private void save(DonationDTO userDTO) {
		RPCCRUDProxy.i().updadd(userDTO, new AsyncCallback<DonationDTO>() {

			@Override
			public void onSuccess(DonationDTO result) {
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

	protected void updadded(DonationDTO dto) {
		// can be overwritten
	}

}
