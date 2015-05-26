package com.supeyou.core.web.client.rpc.user2supporter.assoaeditor;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.User2SupporterDTO;
import com.supeyou.core.iface.dto.User2SupporterFetchQuery;
import com.supeyou.core.web.client.rpc.user2supporter.ListDataProvider;
import com.supeyou.core.web.client.rpc.user2supporter.RPCCRUDProxy;
import com.supeyou.core.web.client.rpc.user2supporter.assoaeditor.item.ItemWidget;
import com.supeyou.core.web.client.rpc.user2supporter.form.FormWidget;
import com.supeyou.crudie.iface.dto.UserDTO;
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

		final AbstrListDataProvider<User2SupporterDTO, User2SupporterFetchQuery> dataProvider = new ListDataProvider(new User2SupporterFetchQuery());
		dataProvider.getFetchQuery().setIdB(thisDTO.getId());
		dataProvider.setPageSize(Integer.MAX_VALUE);

		final AbstrListWidgetList<User2SupporterDTO, User2SupporterFetchQuery> listAbstrWidgetList = new AbstrListWidgetList<User2SupporterDTO, User2SupporterFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final User2SupporterDTO dto) {

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

		listAbstrWidgetList.getSelectionModel().addListener(new SelectionListener<User2SupporterDTO>() {

			@Override
			public void onHasChanged(List<User2SupporterDTO> selection) {

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
					popup.init(new com.supeyou.crudie.web.client.rpc.user.choosersmall.ChooserSmallWidget(new SelectionListener<UserDTO>() {

						@Override
						public void onHasChanged(List<UserDTO> selection) {

							popup.closePopup();

							for (UserDTO dto : selection) {

								User2SupporterDTO assoDTO = new User2SupporterDTO();
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

	private void showForm(final AbstrListDataProvider<User2SupporterDTO, User2SupporterFetchQuery> dataProvider, final AbstrListWidgetList<User2SupporterDTO, User2SupporterFetchQuery> listAbstrWidgetList, List<User2SupporterDTO> selection) {
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
