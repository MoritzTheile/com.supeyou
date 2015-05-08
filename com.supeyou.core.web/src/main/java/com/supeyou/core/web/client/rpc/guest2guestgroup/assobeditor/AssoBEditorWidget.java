package com.supeyou.core.web.client.rpc.guest2guestgroup.assobeditor;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.Guest2GuestGroupDTO;
import com.supeyou.core.iface.dto.Guest2GuestGroupFetchQuery;
import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.iface.dto.GuestGroupDTO;
import com.supeyou.core.web.client.rpc.guest2guestgroup.ListDataProvider;
import com.supeyou.core.web.client.rpc.guest2guestgroup.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.guest2guestgroup.assobeditor.item.ItemWidget;
import com.supeyou.core.web.client.rpc.guest2guestgroup.form.FormWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class AssoBEditorWidget extends WidgetView {

	private final GuestDTO thisDTO;

	public AssoBEditorWidget(GuestDTO dto, final boolean showFormOnCreation) {
		thisDTO = dto;

		final AbstrListDataProvider<Guest2GuestGroupDTO, Guest2GuestGroupFetchQuery> dataProvider = new ListDataProvider(new Guest2GuestGroupFetchQuery());
		dataProvider.getFetchQuery().setIdA(thisDTO.getId());
		dataProvider.setPageSize(Integer.MAX_VALUE);

		final AbstrListWidgetList<Guest2GuestGroupDTO, Guest2GuestGroupFetchQuery> listAbstrWidgetList = new AbstrListWidgetList<Guest2GuestGroupDTO, Guest2GuestGroupFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Guest2GuestGroupDTO data) {

				return new ItemWidget(data) {
					@Override
					protected String renderInfoHTML(Guest2GuestGroupDTO dto) {

						return dto.getDtoB().getName() + "";

					}
				};

			}

		};

		listAbstrWidgetList.getSelectionModel().addListener(new SelectionListener<Guest2GuestGroupDTO>() {

			@Override
			public void onHasChanged(List<Guest2GuestGroupDTO> selection) {

				if (selection.size() == 1) {

					final PopupWidget formPopup = new PopupWidget(false);
					formPopup.setPosition(100, 700);
					formPopup.init(
							new FormWidget(dataProvider, selection.iterator().next(), true, true) {
								@Override
								protected void close() {
									listAbstrWidgetList.getSelectionModel().clearSelection();
									formPopup.closePopup();
								}
							}
							);

				} else {
					// nothing yet
				}

			}
		});
		assoListSlot.add(listAbstrWidgetList);

		final Label addLink = new Label();
		assoAddSlot.add(addLink);
		addLink.addDomHandler(new ClickHandler() {

			PopupWidget popup;
			PopupWidget formPopup;

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();
				popup = new PopupWidget(new com.supeyou.core.web.client.rpc.guestgroup.choosersmall.ChooserSmallWidget(new SelectionListener<GuestGroupDTO>() {

					@Override
					public void onHasChanged(List<GuestGroupDTO> selection) {

						popup.closePopup();

						for (GuestGroupDTO dto : selection) {

							Guest2GuestGroupDTO assoDTO = new Guest2GuestGroupDTO();
							assoDTO.setDtoA(thisDTO);
							assoDTO.setDtoB(dto);

							if (showFormOnCreation) {
								formPopup = new PopupWidget(
										new FormWidget(dataProvider, assoDTO, false, true) {
											@Override
											protected void close() {
												formPopup.closePopup();
											}
										}, false);
								formPopup.setPosition(100, 700);
							} else {

								RPCCRUDProxy.i().updadd(assoDTO);

							}

							break;
						}

					}

				}), true);

				popup.setPosition(addLink.getAbsoluteTop(), addLink.getAbsoluteLeft());

			}
		}, ClickEvent.getType());

		dataProvider.fetchData();
	}
}
