package com.supeyou.core.web.client.rpc.invitation2supporter.assobeditor;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterFetchQuery;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.rpc.invitation2supporter.ListDataProvider;
import com.supeyou.core.web.client.rpc.invitation2supporter.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.invitation2supporter.chooserlarge.item.ItemWidget;
import com.supeyou.core.web.client.rpc.invitation2supporter.form.FormWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class AssoBEditorWidget extends WidgetView {

	private final InvitationDTO thisDTO;

	public AssoBEditorWidget(InvitationDTO dto, final boolean showFormOnCreation) {

		thisDTO = dto;

		final AbstrListDataProvider<Invitation2SupporterDTO, Invitation2SupporterFetchQuery> dataProvider = new ListDataProvider(new Invitation2SupporterFetchQuery());
		dataProvider.getFetchQuery().setIdA(thisDTO.getId());
		dataProvider.setPageSize(Integer.MAX_VALUE);

		final AbstrListWidgetList<Invitation2SupporterDTO, Invitation2SupporterFetchQuery> listAbstrWidgetList = new AbstrListWidgetList<Invitation2SupporterDTO, Invitation2SupporterFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Invitation2SupporterDTO dto) {

				return new ItemWidget(dto);

			}

		};

		listAbstrWidgetList.getSelectionModel().addListener(new SelectionListener<Invitation2SupporterDTO>() {

			@Override
			public void onHasChanged(List<Invitation2SupporterDTO> selection) {

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
					popup.init(new com.supeyou.core.web.client.rpc.supporter.choosersmall.ChooserSmallWidget(new SelectionListener<SupporterDTO>() {

						@Override
						public void onHasChanged(List<SupporterDTO> selection) {

							popup.closePopup();

							for (SupporterDTO dto : selection) {

								Invitation2SupporterDTO assoDTO = new Invitation2SupporterDTO();
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
