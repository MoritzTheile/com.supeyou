package com.hotelorga.core.web.client.rpc.acceptance2payer.assoaeditor;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.core.iface.dto.Acceptance2PayerDTO;
import com.hotelorga.core.iface.dto.Acceptance2PayerFetchQuery;
import com.hotelorga.core.iface.dto.AcceptanceDTO;
import com.hotelorga.core.iface.dto.PayerDTO;
import com.hotelorga.core.web.client.rpc.acceptance2payer.ListDataProvider;
import com.hotelorga.core.web.client.rpc.acceptance2payer.RPCCRUDProxy;
import com.hotelorga.core.web.client.rpc.acceptance2payer.choosersmall.item.ItemWidget;
import com.hotelorga.core.web.client.rpc.acceptance2payer.form.FormWidget;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class AssoAEditorWidget extends WidgetView {

	private final PayerDTO thisDTO;

	public AssoAEditorWidget(PayerDTO dto, final boolean showFormOnCreation) {
		thisDTO = dto;

		final AbstrListDataProvider<Acceptance2PayerDTO, Acceptance2PayerFetchQuery> dataProvider = new ListDataProvider(new Acceptance2PayerFetchQuery());
		dataProvider.getFetchQuery().setIdB(thisDTO.getId());
		dataProvider.setPageSize(Integer.MAX_VALUE);

		AbstrListWidgetList<Acceptance2PayerDTO, Acceptance2PayerFetchQuery> listAbstrWidgetList = new AbstrListWidgetList<Acceptance2PayerDTO, Acceptance2PayerFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Acceptance2PayerDTO data) {

				return new ItemWidget(data) {
					@Override
					protected String renderInfoString(Acceptance2PayerDTO dto) {

						return dto.getDtoB().getName() + "";

					}
				};

			}

		};

		listAbstrWidgetList.getSelectionModel().addListener(new SelectionListener<Acceptance2PayerDTO>() {

			@Override
			public void onHasChanged(List<Acceptance2PayerDTO> selection) {

				// nothing

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
				popup = new PopupWidget(new com.hotelorga.core.web.client.rpc.acceptance.choosersmall.ChooserSmallWidget(new SelectionListener<AcceptanceDTO>() {

					@Override
					public void onHasChanged(List<AcceptanceDTO> selection) {

						popup.closePopup();

						for (AcceptanceDTO dto : selection) {

							Acceptance2PayerDTO assoDTO = new Acceptance2PayerDTO();
							assoDTO.setDtoB(thisDTO);
							assoDTO.setDtoA(dto);

							if (showFormOnCreation) {
								formPopup = new PopupWidget(
										new FormWidget(dataProvider, assoDTO, true, true) {
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

					}

				}, showFormOnCreation), true);

				popup.setPosition(addLink.getAbsoluteTop(), addLink.getAbsoluteLeft());

			}
		}, ClickEvent.getType());

		dataProvider.fetchData();
	}
}
