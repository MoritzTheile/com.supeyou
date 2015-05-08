package com.hotelorga.foundation.web.client.rpc.user2group.form;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hotelorga.foundation.iface.dto.GroupDTO;
import com.hotelorga.foundation.iface.dto.User2GroupDTO;
import com.hotelorga.foundation.iface.dto.User2GroupFetchQuery;
import com.hotelorga.foundation.iface.dto.UserDTO;
import com.hotelorga.foundation.web.client.model.AbstrObservable.Observer;
import com.hotelorga.foundation.web.client.resources.GWTSTATICS;
import com.hotelorga.foundation.web.client.resources.i18n.Text;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.user2group.RPCCRUDProxy;
import com.hotelorga.foundation.web.client.rpc.user2group.form.fields.FieldsWidget;

public class FormWidget extends WidgetView {

	// needed for change detection
	private UserDTO origDtoA;
	private GroupDTO origDtoB;

	public FormWidget(AbstrListDataProvider<User2GroupDTO, User2GroupFetchQuery> dataProvider, User2GroupDTO userDTO, boolean showAssoAField, boolean showAssoBField) {

		init(dataProvider, userDTO, showAssoAField, showAssoBField);

	}

	public FormWidget(AbstrListDataProvider<User2GroupDTO, User2GroupFetchQuery> dataProvider, boolean showAssoAField, boolean showAssoBField) {
		User2GroupDTO dto = new User2GroupDTO();

		init(dataProvider, dto, showAssoAField, showAssoBField);

	}

	private void init(AbstrListDataProvider<User2GroupDTO, User2GroupFetchQuery> dataProvider, final User2GroupDTO userDTO, boolean showAssoAField, boolean showAssoBField) {

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

	private void save(final User2GroupDTO dto) {
		RPCCRUDProxy.i().updadd(dto, new AsyncCallback<User2GroupDTO>() {

			@Override
			public void onSuccess(User2GroupDTO result) {

				if (!GWTSTATICS.nullsafeEquals(origDtoA, dto.getDtoA())) {
					com.hotelorga.foundation.web.client.rpc.user.RPCCRUDProxy.i().wasUpdated(origDtoA);
					com.hotelorga.foundation.web.client.rpc.user.RPCCRUDProxy.i().wasUpdated(dto.getDtoA());
				}
				if (!GWTSTATICS.nullsafeEquals(origDtoB, dto.getDtoB())) {
					com.hotelorga.foundation.web.client.rpc.group.RPCCRUDProxy.i().wasUpdated(origDtoB);
					com.hotelorga.foundation.web.client.rpc.group.RPCCRUDProxy.i().wasUpdated(dto.getDtoB());
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
