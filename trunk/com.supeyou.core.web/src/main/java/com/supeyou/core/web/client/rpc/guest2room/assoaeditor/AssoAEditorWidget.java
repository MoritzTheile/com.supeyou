package com.supeyou.core.web.client.rpc.guest2room.assoaeditor;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.Guest2RoomDTO;
import com.supeyou.core.iface.dto.Guest2RoomFetchQuery;
import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.iface.dto.RoomDTO;
import com.supeyou.core.web.client.rpc.guest2room.ListDataProvider;
import com.supeyou.core.web.client.rpc.guest2room.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.guest2room.assoaeditor.item.ItemWidget;
import com.supeyou.core.web.client.rpc.guest2room.form.FormWidget;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class AssoAEditorWidget extends WidgetView {

	private final RoomDTO thisDTO;

	final AbstrListDataProvider<Guest2RoomDTO, Guest2RoomFetchQuery> dataProvider;

	public AssoAEditorWidget(RoomDTO dto, final boolean showFormOnCreation, DateType fromDate, DateType toDate) {
		this(dto, showFormOnCreation);
		dataProvider.getFetchQuery().setFrom(fromDate);
		dataProvider.getFetchQuery().setTo(toDate);
		dataProvider.fetchData();
	}

	public AssoAEditorWidget(RoomDTO dto, final boolean showFormOnCreation) {

		thisDTO = dto;

		dataProvider = new ListDataProvider(new Guest2RoomFetchQuery());
		dataProvider.getFetchQuery().setIdB(thisDTO.getId());
		dataProvider.setPageSize(Integer.MAX_VALUE);

		final AbstrListWidgetList<Guest2RoomDTO, Guest2RoomFetchQuery> listAbstrWidgetList = new AbstrListWidgetList<Guest2RoomDTO, Guest2RoomFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Guest2RoomDTO data) {
				FlowPanel cell = new FlowPanel();
				cell.add(new ItemWidget(data, dataProvider.getFetchQuery().getFrom(), dataProvider.getFetchQuery().getTo()));
				return cell;

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

					popup = new PopupWidget(new com.supeyou.core.web.client.rpc.guest.choosersmall.ChooserSmallWidget(new SelectionListener<GuestDTO>() {

						@Override
						public void onHasChanged(List<GuestDTO> selection) {

							popup.closePopup();

							for (GuestDTO dto : selection) {

								Guest2RoomDTO assoDTO = new Guest2RoomDTO();
								assoDTO.setDtoA(dto);
								assoDTO.setDtoB(thisDTO);

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
