package com.hotelorga.core.web.client.rpc.guest2guestgroup.imexport;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hotelorga.core.web.client.rpc.guest2guestgroup.RPCCRUDServiceAsync;
import com.hotelorga.foundation.iface.dto.FileDTO;
import com.hotelorga.foundation.web.client.resources.i18n.Text;
import com.hotelorga.foundation.web.client.rpc.file.RPCCRUDProxy;
import com.hotelorga.foundation.web.client.uiorga.linkbutton.LinkButtonWidget;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;
import com.hotelorga.foundation.web.client.uiorga.upload.FileUploadGWT;

public class ImportLink extends LinkButtonWidget {

	public ImportLink() {

		final ImportLink thisImportLink = this;

		setText(Text.i.LINK_Import());

		this.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) { 

				event.stopPropagation();

				com.hotelorga.foundation.web.client.rpc.file.RPCCRUDServiceAsync.i.updadd(new FileDTO(), new AsyncCallback<FileDTO>() {

					PopupWidget popupWidget;

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(final FileDTO result) {

						popupWidget = new PopupWidget(

								new FileUploadGWT(result.getId(), new FileUploadGWT.FileUploadListener() {

									@Override
									public void fileUploadStarted() {
										// Window.alert("file upload started");

									}

									@Override
									public void fileUploadCompleted() {
										RPCCRUDProxy.i().wasCreated(result);
										popupWidget.closePopup();
										RPCCRUDServiceAsync.i.importData(result.getId(), new AsyncCallback<Void>() {

											@Override
											public void onFailure(Throwable caught) {
												caught.printStackTrace();

											}

											@Override
											public void onSuccess(Void result) {

												importFinished();

											}

										});

									}

									@Override
									public void fileUploadCanceled() {
										Window.alert("file upload canceled");
										popupWidget.closePopup();

									}
								}), thisImportLink.getAbsoluteTop(), thisImportLink.getAbsoluteLeft(), true
								);

					}
				});

			}
		});
	}

	public void importFinished() {
		// can be overwritten

	}

}
