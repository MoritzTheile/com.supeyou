package com.supeyou.crudie.web.client.rpc.file.form;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.supeyou.crudie.iface.dto.FileDTO;
import com.supeyou.crudie.iface.dto.FileFetchQuery;
import com.supeyou.crudie.web.client.model.AbstrObservable.Observer;
import com.supeyou.crudie.web.client.resources.i18n.Text;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.file.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.file.RPCCRUDServiceAsync;
import com.supeyou.crudie.web.client.rpc.file.form.fields.FieldsWidget;
import com.supeyou.crudie.web.client.ui.fileviewer.FileViewerWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;
import com.supeyou.crudie.web.client.uiorga.upload.FileUploadGWT;

public class FormWidget extends WidgetView {

	public FormWidget(AbstrListDataProvider<FileDTO, FileFetchQuery> dataProvider, FileDTO userDTO) {

		init(dataProvider, userDTO);

	}

	public FormWidget(AbstrListDataProvider<FileDTO, FileFetchQuery> dataProvider) {

		init(dataProvider, new FileDTO());

	}

	private void init(final AbstrListDataProvider<FileDTO, FileFetchQuery> dataProvider, final FileDTO dto) {

		if (dto.getId() == null) {

			RPCCRUDServiceAsync.i.updadd(dto, new AsyncCallback<FileDTO>() {

				@Override
				public void onFailure(Throwable caught) {
					caught.printStackTrace();
				}

				@Override
				public void onSuccess(final FileDTO result) {

					formSlot.add(new FileUploadGWT(result.getId(), new FileUploadGWT.FileUploadListener() {

						@Override
						public void fileUploadStarted() {
							// Window.alert("file upload started");

						}

						@Override
						public void fileUploadCompleted() {
							RPCCRUDProxy.i().wasCreated(result);
							close();

						}

						@Override
						public void fileUploadCanceled() {
							Window.alert("file upload canceled");
							close();

						}
					}));

					cancelButton.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {

				event.stopPropagation();

							RPCCRUDProxy.i().delete(result);

						}
					});

				}
			});

			deleteButton.removeFromParent();

			cancelButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

				event.stopPropagation();

					cancel();

				}
			});

			saveButton.removeFromParent();

		} else {
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

			Button viewerButton = new Button("show");
			viewerButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

				event.stopPropagation();

					new PopupWidget(new FileViewerWidget(dto), true);

				}
			});
			formSlot.add(viewerButton);

		}
	}

	private void save(FileDTO userDTO) {
		RPCCRUDProxy.i().updadd(userDTO, new AsyncCallback<FileDTO>() {

			@Override
			public void onSuccess(FileDTO result) {
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

	protected void updadded(FileDTO dto) {
		// can be overwritten
	}

}
