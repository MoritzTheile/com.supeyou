package com.supeyou.core.web.client.rpc.invitation2supporter.assoaeditor;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterFetchQuery;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.rpc.invitation2supporter.ListDataProvider;
import com.supeyou.core.web.client.rpc.invitation2supporter.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.invitation2supporter.assoaeditor.item.ItemWidget;
import com.supeyou.core.web.client.rpc.invitation2supporter.form.FormWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList.SELECTIONMODE;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.uiorga.linkbutton.LinkButtonWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class AssoAEditorWidget extends WidgetView {

	private final SupporterDTO thisDTO;

	public AssoAEditorWidget(SupporterDTO dto, final boolean showFormOnCreation) {
		thisDTO = dto;

		final AbstrListDataProvider<Invitation2SupporterDTO, Invitation2SupporterFetchQuery> dataProvider = new ListDataProvider(new Invitation2SupporterFetchQuery());
		dataProvider.getFetchQuery().setIdB(thisDTO.getId());
		dataProvider.setPageSize(Integer.MAX_VALUE);

		final AbstrListWidgetList<Invitation2SupporterDTO, Invitation2SupporterFetchQuery> listAbstrWidgetList = new AbstrListWidgetList<Invitation2SupporterDTO, Invitation2SupporterFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Invitation2SupporterDTO dto) {

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

		listAbstrWidgetList.getSelectionModel().addListener(new SelectionListener<Invitation2SupporterDTO>() {

			@Override
			public void onHasChanged(List<Invitation2SupporterDTO> selection) {

				if (selection.size() > 0) {

					if (SELECTIONMODE.MULTISELECT.equals(listAbstrWidgetList.getSelectionmode())) {
						Window.alert("selection size = " + selection.size());
					} else {
						showForm(dataProvider, listAbstrWidgetList, selection);
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
					popup.init(new com.supeyou.core.web.client.rpc.invitation.choosersmall.ChooserSmallWidget(new SelectionListener<InvitationDTO>() {

						@Override
						public void onHasChanged(List<InvitationDTO> selection) {

							popup.closePopup();

							for (InvitationDTO dto : selection) {

								Invitation2SupporterDTO assoDTO = new Invitation2SupporterDTO();
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

		dataProvider.fetchData();

	}

	private void showForm(final AbstrListDataProvider<Invitation2SupporterDTO, Invitation2SupporterFetchQuery> dataProvider, final AbstrListWidgetList<Invitation2SupporterDTO, Invitation2SupporterFetchQuery> listAbstrWidgetList, List<Invitation2SupporterDTO> selection) {
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
			Window.alert("selection.size() = " + selection.size());
		}
	}

}
