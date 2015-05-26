package com.supeyou.core.web.client.rpc.supporter2donation.form;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.DonationDTO;
import com.supeyou.core.iface.dto.Supporter2DonationDTO;
import com.supeyou.core.iface.dto.Supporter2DonationFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.rpc.supporter2donation.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.supporter2donation.form.fields.FieldsWidget;
import com.supeyou.crudie.web.client.model.AbstrObservable.Observer;
import com.supeyou.crudie.web.client.resources.GWTSTATICS;
import com.supeyou.crudie.web.client.resources.i18n.Text;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;

public class FormWidget extends WidgetView {

	// needed for change detection
	private SupporterDTO origDtoA;
	private DonationDTO origDtoB;

	public FormWidget(AbstrListDataProvider<Supporter2DonationDTO, Supporter2DonationFetchQuery> dataProvider, Supporter2DonationDTO userDTO, boolean showAssoAField, boolean showAssoBField) {

		init(dataProvider, userDTO, showAssoAField, showAssoBField);

	}

	public FormWidget(AbstrListDataProvider<Supporter2DonationDTO, Supporter2DonationFetchQuery> dataProvider, boolean showAssoAField, boolean showAssoBField) {
		Supporter2DonationDTO dto = new Supporter2DonationDTO();

		init(dataProvider, dto, showAssoAField, showAssoBField);

	}

	private void init(AbstrListDataProvider<Supporter2DonationDTO, Supporter2DonationFetchQuery> dataProvider, final Supporter2DonationDTO userDTO, boolean showAssoAField, boolean showAssoBField) {

		origDtoA = userDTO.getDtoA();
		origDtoB = userDTO.getDtoB();

		dataProvider.addObserver(new Observer<Void>() {

			@Override
			public void modelHasChanged(Void changes) {

				cancel();

			}

		});

		final FieldsWidget fieldsWidget = new FieldsWidget(userDTO);

		formSlot.add(fieldsWidget);

		if (userDTO.getId() == null) {
			saveButton.setText(Text.i.MULTIUSE_Create());
		}

		deleteButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				event.stopPropagation();

				RPCCRUDProxy.i().delete(userDTO);
				close();

			}
		});

		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				event.stopPropagation();

				cancel();

			}
		});

		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

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

	private void save(final Supporter2DonationDTO dto) {
		RPCCRUDProxy.i().updadd(dto, new AsyncCallback<Supporter2DonationDTO>() {

			@Override
			public void onSuccess(Supporter2DonationDTO result) {

				if (!GWTSTATICS.nullsafeEquals(origDtoA, dto.getDtoA())) {
					com.supeyou.core.web.client.rpc.supporter.RPCCRUDProxy.i().wasUpdated(origDtoA);
					com.supeyou.core.web.client.rpc.supporter.RPCCRUDProxy.i().wasUpdated(dto.getDtoA());
				}
				if (!GWTSTATICS.nullsafeEquals(origDtoB, dto.getDtoB())) {
					com.supeyou.core.web.client.rpc.donation.RPCCRUDProxy.i().wasUpdated(origDtoB);
					com.supeyou.core.web.client.rpc.donation.RPCCRUDProxy.i().wasUpdated(dto.getDtoB());
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
