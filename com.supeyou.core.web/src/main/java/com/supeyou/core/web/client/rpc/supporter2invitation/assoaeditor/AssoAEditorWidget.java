package com.supeyou.core.web.client.rpc.supporter2invitation.assoaeditor;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.Supporter2InvitationDTO;
import com.supeyou.core.iface.dto.Supporter2InvitationFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.rpc.supporter2invitation.ListDataProvider;
import com.supeyou.core.web.client.rpc.supporter2invitation.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.supporter2invitation.assoaeditor.item.ItemWidget;
import com.supeyou.core.web.client.rpc.supporter2invitation.form.FormWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList.SELECTIONMODE;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.uiorga.linkbutton.LinkButtonWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class AssoAEditorWidget extends WidgetView {

	private final InvitationDTO thisDTO;

	public AssoAEditorWidget(InvitationDTO dto, final boolean showFormOnCreation) {
		thisDTO = dto;

		final AbstrListDataProvider<Supporter2InvitationDTO, Supporter2InvitationFetchQuery> dataProvider = new ListDataProvider(new Supporter2InvitationFetchQuery());
		dataProvider.getFetchQuery().setIdB(thisDTO.getId());
		dataProvider.setPageSize(Integer.MAX_VALUE);

		final AbstrListWidgetList<Supporter2InvitationDTO, Supporter2InvitationFetchQuery> listAbstrWidgetList = new AbstrListWidgetList<Supporter2InvitationDTO, Supporter2InvitationFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Supporter2InvitationDTO dto) {

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

		listAbstrWidgetList.getSelectionModel().addListener(new SelectionListener<Supporter2InvitationDTO>() {

			@Override
			public void onHasChanged(List<Supporter2InvitationDTO> selection) {

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
					popup.init(new com.supeyou.core.web.client.rpc.supporter.choosersmall.ChooserSmallWidget(new SelectionListener<SupporterDTO>() {

						@Override
						public void onHasChanged(List<SupporterDTO> selection) {

							popup.closePopup();

							for (SupporterDTO dto : selection) {

								Supporter2InvitationDTO assoDTO = new Supporter2InvitationDTO();
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

	private void showForm(final AbstrListDataProvider<Supporter2InvitationDTO, Supporter2InvitationFetchQuery> dataProvider, final AbstrListWidgetList<Supporter2InvitationDTO, Supporter2InvitationFetchQuery> listAbstrWidgetList, List<Supporter2InvitationDTO> selection) {
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
