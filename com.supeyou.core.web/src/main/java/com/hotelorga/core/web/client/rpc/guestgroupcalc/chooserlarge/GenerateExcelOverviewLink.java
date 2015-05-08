package com.hotelorga.core.web.client.rpc.guestgroupcalc.chooserlarge;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.iface.datatype.types.FileIDType;
import com.hotelorga.foundation.iface.dto.FileDTO;
import com.hotelorga.foundation.web.client.ui.fileviewer.FileViewerWidget;
import com.hotelorga.foundation.web.client.uiorga.linkbutton.LinkButtonWidget;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public abstract class GenerateExcelOverviewLink extends LinkButtonWidget {

	public GenerateExcelOverviewLink() {

		setText("Als Excel");

		this.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				com.hotelorga.core.web.client.rpc.guest.RPCCRUDServiceAsync.i.renderExcelOverview(getFrom(), getTo(), new AsyncCallback<FileIDType>() {

					@Override
					public void onSuccess(FileIDType fileId) {

						com.hotelorga.foundation.web.client.rpc.file.RPCCRUDServiceAsync.i.get(fileId, new AsyncCallback<FileDTO>() {

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

	public abstract DateType getFrom();

	public abstract DateType getTo();

}
