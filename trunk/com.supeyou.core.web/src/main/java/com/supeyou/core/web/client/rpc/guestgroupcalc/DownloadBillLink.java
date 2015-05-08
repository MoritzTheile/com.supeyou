package com.supeyou.core.web.client.rpc.guestgroupcalc;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.paying.GuestsCalculationDTO;
import com.supeyou.core.web.client.rpc.guest.RPCCRUDServiceAsync;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.iface.datatype.types.FileIDType;
import com.supeyou.crudie.iface.dto.FileDTO;
import com.supeyou.crudie.web.client.ui.fileviewer.FileViewerWidget;
import com.supeyou.crudie.web.client.uiorga.linkbutton.LinkButtonWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class DownloadBillLink extends LinkButtonWidget {

	public DownloadBillLink(final GuestsCalculationDTO guestsCalculationDTO, final DateType from, final DateType to) {

		setText("Rechnung herunterladen");

		this.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				RPCCRUDServiceAsync.i.renderBill(guestsCalculationDTO, from, to, new AsyncCallback<FileIDType>() {

					@Override
					public void onSuccess(FileIDType fileId) {

						com.supeyou.crudie.web.client.rpc.file.RPCCRUDServiceAsync.i.get(fileId, new AsyncCallback<FileDTO>() {

							@Override
							public void onFailure(Throwable caught) {

								caught.printStackTrace();

							}

							@Override
							public void onSuccess(FileDTO fileDTO) {

								new PopupWidget(new FileViewerWidget(fileDTO), true);

							}
						});

					}

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();

					}
				});

			}
		});
	}
}
