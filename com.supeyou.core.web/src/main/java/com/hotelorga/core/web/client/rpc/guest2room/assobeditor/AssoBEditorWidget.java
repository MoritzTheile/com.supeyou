package com.hotelorga.core.web.client.rpc.guest2room.assobeditor;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.core.iface.dto.Guest2RoomDTO;
import com.hotelorga.core.iface.dto.Guest2RoomFetchQuery;
import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.iface.dto.RoomDTO;
import com.hotelorga.core.web.client.rpc.guest2room.ListDataProvider;
import com.hotelorga.core.web.client.rpc.guest2room.RPCCRUDProxy;
import com.hotelorga.core.web.client.rpc.guest2room.assobeditor.item.ItemWidget;
import com.hotelorga.core.web.client.rpc.guest2room.form.FormWidget;
import com.hotelorga.foundation.iface.common.HELPER;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class AssoBEditorWidget extends WidgetView {

	private final GuestDTO thisDTO;

	public AssoBEditorWidget(GuestDTO dto, final boolean showFormOnCreation) {
		thisDTO = dto;

		final AbstrListDataProvider<Guest2RoomDTO, Guest2RoomFetchQuery> dataProvider = new ListDataProvider(new Guest2RoomFetchQuery());
		dataProvider.getFetchQuery().setIdA(thisDTO.getId());
		dataProvider.setPageSize(Integer.MAX_VALUE);

		final AbstrListWidgetList<Guest2RoomDTO, Guest2RoomFetchQuery> listAbstrWidgetList = new AbstrListWidgetList<Guest2RoomDTO, Guest2RoomFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Guest2RoomDTO data) {

				return new ItemWidget(data) {
					@Override
					protected String renderInfoHTML(Guest2RoomDTO dto) {

						return ""
								+ dto.getDtoB().getName() + "<br/>"
								+ "" + HELPER.date2string(dto.getFromDate()) + ""
								+ " - " + HELPER.date2string(dto.getToDate()) + ""
								+ "";

					}
				};

			}

		};

		listAbstrWidgetList.getSelectionModel().addListener(new SelectionListener<Guest2RoomDTO>() {

			@Override
			public void onHasChanged(List<Guest2RoomDTO> selection) {

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
		{// adding Link
			final Label addLink = new Label();
			assoAddSlot.add(addLink);
			addLink.addDomHandler(new ClickHandler() {

				PopupWidget popup;
				PopupWidget formPopup;

				@Override
				public void onClick(ClickEvent event) {

					event.stopPropagation();
					popup = new PopupWidget(new com.hotelorga.core.web.client.rpc.room.choosersmall.ChooserSmallWidget(new SelectionListener<RoomDTO>() {

						@Override
						public void onHasChanged(List<RoomDTO> selection) {

							popup.closePopup();

							for (RoomDTO dto : selection) {

								Guest2RoomDTO assoDTO = new Guest2RoomDTO();
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
								} else {

									RPCCRUDProxy.i().updadd(assoDTO);

								}

								break;
							}

							formPopup.setPosition(100, 700);

						}

					}), true);

					popup.setPosition(addLink.getAbsoluteTop(), addLink.getAbsoluteLeft());

				}
			}, ClickEvent.getType());
		}
		dataProvider.fetchData();
	}
}
