package com.hotelorga.core.web.client.rpc.guest2room.imexport;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hotelorga.core.iface.dto.Guest2RoomFetchQuery;
import com.hotelorga.core.web.client.rpc.guest2room.RPCCRUDServiceAsync;
import com.hotelorga.foundation.iface.datatype.types.FileIDType;
import com.hotelorga.foundation.iface.dto.FileDTO;
import com.hotelorga.foundation.web.client.resources.i18n.Text;
import com.hotelorga.foundation.web.client.ui.fileviewer.FileViewerWidget;
import com.hotelorga.foundation.web.client.uiorga.linkbutton.LinkButtonWidget;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class ExportLink extends LinkButtonWidget {

	public ExportLink(final Guest2RoomFetchQuery fetchQuery) {

		setText(Text.i.LINK_Export());

		this.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) { 

				event.stopPropagation();

				RPCCRUDServiceAsync.i.exportData(fetchQuery, new AsyncCallback<FileIDType>() {

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

}
