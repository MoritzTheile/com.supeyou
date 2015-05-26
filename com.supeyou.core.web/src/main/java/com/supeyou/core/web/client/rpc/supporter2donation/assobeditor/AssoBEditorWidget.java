package com.supeyou.core.web.client.rpc.supporter2donation.assobeditor;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.DonationDTO;
import com.supeyou.core.iface.dto.Supporter2DonationDTO;
import com.supeyou.core.iface.dto.Supporter2DonationFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.rpc.supporter2donation.ListDataProvider;
import com.supeyou.core.web.client.rpc.supporter2donation.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.supporter2donation.chooserlarge.item.ItemWidget;
import com.supeyou.core.web.client.rpc.supporter2donation.form.FormWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class AssoBEditorWidget extends WidgetView {

	private final SupporterDTO thisDTO;

	public AssoBEditorWidget(SupporterDTO dto, final boolean showFormOnCreation) {

		thisDTO = dto;

		final AbstrListDataProvider<Supporter2DonationDTO, Supporter2DonationFetchQuery> dataProvider = new ListDataProvider(new Supporter2DonationFetchQuery());
		dataProvider.getFetchQuery().setIdA(thisDTO.getId());
		dataProvider.setPageSize(Integer.MAX_VALUE);

		final AbstrListWidgetList<Supporter2DonationDTO, Supporter2DonationFetchQuery> listAbstrWidgetList = new AbstrListWidgetList<Supporter2DonationDTO, Supporter2DonationFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Supporter2DonationDTO dto) {

				return new ItemWidget(dto);

			}

		};

		listAbstrWidgetList.getSelectionModel().addListener(new SelectionListener<Supporter2DonationDTO>() {

			@Override
			public void onHasChanged(List<Supporter2DonationDTO> selection) {

				if (selection.size() == 1) {

					final PopupWidget formPopup = new PopupWidget(false);
					formPopup.setPosition(100, 700);
					formPopup.init(
							new FormWidget(dataProvider, selection.iterator().next(), true, false) {
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

				@Override
				public void onClick(ClickEvent event) {

					event.stopPropagation();

					event.stopPropagation();

					final PopupWidget popup = new PopupWidget(true);
					popup.setPosition(addLink.getAbsoluteTop(), addLink.getAbsoluteLeft());
					popup.init(new com.supeyou.core.web.client.rpc.donation.choosersmall.ChooserSmallWidget(new SelectionListener<DonationDTO>() {

						@Override
						public void onHasChanged(List<DonationDTO> selection) {

							popup.closePopup();

							for (DonationDTO dto : selection) {

								Supporter2DonationDTO assoDTO = new Supporter2DonationDTO();
								assoDTO.setDtoB(dto);
								assoDTO.setDtoA(thisDTO);

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

		dataProvider.fetchData();

	}
}
