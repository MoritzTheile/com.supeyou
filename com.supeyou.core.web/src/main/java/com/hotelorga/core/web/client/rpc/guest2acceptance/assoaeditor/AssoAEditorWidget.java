package com.hotelorga.core.web.client.rpc.guest2acceptance.assoaeditor;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.core.iface.dto.AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2AcceptanceFetchQuery;
import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.web.client.rpc.acceptance.Statics;
import com.hotelorga.core.web.client.rpc.guest2acceptance.ListDataProvider;
import com.hotelorga.core.web.client.rpc.guest2acceptance.RPCCRUDProxy;
import com.hotelorga.core.web.client.rpc.guest2acceptance.assoaeditor.item.ItemWidget;
import com.hotelorga.core.web.client.rpc.guest2acceptance.form.FormWidget;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class AssoAEditorWidget extends WidgetView {

	private final GuestDTO thisDTO;

	public AssoAEditorWidget(GuestDTO dto, final boolean showFormOnCreation) {
		thisDTO = dto;

		final AbstrListDataProvider<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery> dataProvider = new ListDataProvider(new Guest2AcceptanceFetchQuery());
		dataProvider.getFetchQuery().setIdA(thisDTO.getId());
		dataProvider.setPageSize(Integer.MAX_VALUE);

		final AbstrListWidgetList<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery> listAbstrWidgetList = new AbstrListWidgetList<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Guest2AcceptanceDTO data) {

				return new ItemWidget(data) {
					@Override
					protected String renderInfoHTML(Guest2AcceptanceDTO dto) {

						return Statics.renderDescription(dto.getDtoB());

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
					popup = new PopupWidget(new com.hotelorga.core.web.client.rpc.acceptance.choosersmall.ChooserSmallWidget(new SelectionListener<AcceptanceDTO>() {

						@Override
						public void onHasChanged(List<AcceptanceDTO> selection) {

							popup.closePopup();

							for (AcceptanceDTO dto : selection) {

								Guest2AcceptanceDTO assoDTO = new Guest2AcceptanceDTO();
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

					}, true), true);

					popup.setPosition(addLink.getAbsoluteTop(), addLink.getAbsoluteLeft());

				}
			}, ClickEvent.getType());
		}
		dataProvider.fetchData();
	}
}
