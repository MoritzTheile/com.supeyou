package com.supeyou.crudie.web.client.rpc.group.imexport;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.crudie.iface.datatype.types.FileIDType;
import com.supeyou.crudie.iface.dto.FileDTO;
import com.supeyou.crudie.iface.dto.GroupFetchQuery;
import com.supeyou.crudie.web.client.resources.i18n.Text;
import com.supeyou.crudie.web.client.rpc.group.RPCCRUDServiceAsync;
import com.supeyou.crudie.web.client.ui.fileviewer.FileViewerWidget;
import com.supeyou.crudie.web.client.uiorga.linkbutton.LinkButtonWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class ExportLink extends LinkButtonWidget {

	public ExportLink(final GroupFetchQuery fetchQuery) {

		setText(Text.i.LINK_Export());

		this.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				RPCCRUDServiceAsync.i.exportData(fetchQuery, new AsyncCallback<FileIDType>() {

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
