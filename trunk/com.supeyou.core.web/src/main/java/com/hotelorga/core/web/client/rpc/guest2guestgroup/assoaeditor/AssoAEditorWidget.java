package com.hotelorga.core.web.client.rpc.guest2guestgroup.assoaeditor;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.core.iface.dto.Guest2GuestGroupDTO;
import com.hotelorga.core.iface.dto.Guest2GuestGroupFetchQuery;
import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.iface.dto.GuestGroupDTO;
import com.hotelorga.core.web.client.rpc.guest.formmulti.FormMultiWidget;
import com.hotelorga.core.web.client.rpc.guest2guestgroup.ListDataProvider;
import com.hotelorga.core.web.client.rpc.guest2guestgroup.RPCCRUDProxy;
import com.hotelorga.core.web.client.rpc.guest2guestgroup.assoaeditor.item.ItemWidget;
import com.hotelorga.core.web.client.rpc.guest2guestgroup.form.FormWidget;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListWidgetList.SELECTIONMODE;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.uiorga.linkbutton.LinkButtonWidget;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class AssoAEditorWidget extends WidgetView {

	private final GuestGroupDTO thisDTO;

	public AssoAEditorWidget(GuestGroupDTO dto, final boolean showFormOnCreation) {
		thisDTO = dto;

		final AbstrListDataProvider<Guest2GuestGroupDTO, Guest2GuestGroupFetchQuery> dataProvider = new ListDataProvider(new Guest2GuestGroupFetchQuery());
		dataProvider.getFetchQuery().setIdB(thisDTO.getId());
		dataProvider.setPageSize(Integer.MAX_VALUE);

		final AbstrListWidgetList<Guest2GuestGroupDTO, Guest2GuestGroupFetchQuery> listAbstrWidgetList = new AbstrListWidgetList<Guest2GuestGroupDTO, Guest2GuestGroupFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Guest2GuestGroupDTO dto) {

				ItemWidget itemWidget = new ItemWidget(dto);

				itemWidget.addDomHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						if (event.getNativeEvent().getCtrlKey()) {
						}

					}
				}, ClickEvent.getType());

				return itemWidget;

			}

		};

		listAbstrWidgetList.getSelectionModel().addListener(new SelectionListener<Guest2GuestGroupDTO>() {

			@Override
			public void onHasChanged(List<Guest2GuestGroupDTO> selection) {

				if (selection.size() > 0) {

					if (SELECTIONMODE.MULTISELECT.equals(listAbstrWidgetList.getSelectionmode())) {
						Button multiEditButton = new Button("Alle bearbeiten");
						multiEditButton.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								showFormMulti(listAbstrWidgetList);

							}
						});

						assoAddSlot.add(multiEditButton);
					} else {

						final PopupWidget formPopup = new PopupWidget(false);
						formPopup.setPosition(100, 700);
						formPopup.init(
								new FormWidget(dataProvider, selection.iterator().next(), false, true) {
									@Override
									protected void close() {
										listAbstrWidgetList.getSelectionModel().clearSelection();
										formPopup.closePopup();
									}
								}
								);
					}

				}

			}

		});

		assoListSlot.add(listAbstrWidgetList);

		{// adding Link

			final Label addLink = new Label();
			assoAddSlot.add(addLink);

			final LinkButtonWidget editSelectionLinkButton = new LinkButtonWidget();
			assoAddSlot.add(editSelectionLinkButton);

			addLink.addDomHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					event.stopPropagation();

					final PopupWidget popup = new PopupWidget(true);
					popup.setPosition(addLink.getAbsoluteTop(), addLink.getAbsoluteLeft());
					popup.init(new com.hotelorga.core.web.client.rpc.guest.choosersmall.ChooserSmallWidget(new SelectionListener<GuestDTO>() {

						@Override
						public void onHasChanged(List<GuestDTO> selection) {

							popup.closePopup();

							for (GuestDTO dto : selection) {

								Guest2GuestGroupDTO assoDTO = new Guest2GuestGroupDTO();
								assoDTO.setDtoA(dto);
								assoDTO.setDtoB(thisDTO);

								if (showFormOnCreation) {

									final PopupWidget formPopup = new PopupWidget(false);
									formPopup.setPosition(100, 700);
									formPopup.init(
											new FormWidget(dataProvider, assoDTO, true, false) {
												@Override
												protected void close() {
													formPopup.closePopup();
												}
											});
								} else {

									RPCCRUDProxy.i().updadd(assoDTO);

								}

								break;
							}

						}

					}));

				}

			}, ClickEvent.getType());

		}

		this.addDomHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {

				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					showFormMulti(listAbstrWidgetList);
				}

			}
		}, KeyDownEvent.getType());

		dataProvider.fetchData();

	}

	private void showFormMulti(final AbstrListWidgetList<Guest2GuestGroupDTO, Guest2GuestGroupFetchQuery> listAbstrWidgetList) {
		if (listAbstrWidgetList.getSelectionModel().getSelection().size() > 1) {
			List<GuestDTO> guestDTOs = new ArrayList<GuestDTO>();
			for (Guest2GuestGroupDTO guest2GuestGroupDTO : listAbstrWidgetList.getSelectionModel().getSelection()) {
				guestDTOs.add(guest2GuestGroupDTO.getDtoA());
			}
			final PopupWidget formPopup = new PopupWidget(false);
			formPopup.setPosition(100, 700);
			formPopup.init(
					new FormMultiWidget(guestDTOs) {
						@Override
						protected void close() {
							listAbstrWidgetList.getSelectionModel().clearSelection();
							formPopup.closePopup();
						}
					}
					);
		} else {
			Window.alert("Waelen Sie zunaechst mehrere Elemente mit gedrueckter Shift aus");
		}
	}

}
