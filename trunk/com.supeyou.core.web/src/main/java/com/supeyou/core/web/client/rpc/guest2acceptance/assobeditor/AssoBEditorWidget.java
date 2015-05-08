package com.supeyou.core.web.client.rpc.guest2acceptance.assobeditor;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.Guest2AcceptanceDTO;
import com.supeyou.core.iface.dto.Guest2AcceptanceFetchQuery;
import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.web.client.rpc.guest2acceptance.ListDataProvider;
import com.supeyou.core.web.client.rpc.guest2acceptance.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.guest2acceptance.assobeditor.item.ItemWidget;
import com.supeyou.core.web.client.rpc.guest2acceptance.form.FormWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class AssoBEditorWidget extends WidgetView {

	private final AcceptanceDTO thisDTO;

	public AssoBEditorWidget(AcceptanceDTO dto, final boolean showFormOnCreation) {

		thisDTO = dto;

		final AbstrListDataProvider<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery> dataProvider = new ListDataProvider(new Guest2AcceptanceFetchQuery());
		dataProvider.getFetchQuery().setIdB(thisDTO.getId());
		dataProvider.setPageSize(Integer.MAX_VALUE);

		final AbstrListWidgetList<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery> listAbstrWidgetList = new AbstrListWidgetList<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Guest2AcceptanceDTO data) {

				return new ItemWidget(data) {
					@Override
					protected String renderInfoHTML(Guest2AcceptanceDTO dto) {

						return ""
								+ dto.getDtoA().getFirstname() + " " + dto.getDtoA().getLastname()
								+ "";

					}
				};

			}

		};

		listAbstrWidgetList.getSelectionModel().addListener(new SelectionListener<Guest2AcceptanceDTO>() {

			@Override
			public void onHasChanged(List<Guest2AcceptanceDTO> selection) {

				if (selection.size() == 1) {

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

								Guest2AcceptanceDTO assoDTO = new Guest2AcceptanceDTO();
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
