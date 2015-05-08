package com.hotelorga.foundation.web.client.rpc.user2group.assoaeditor;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.foundation.iface.dto.GroupDTO;
import com.hotelorga.foundation.iface.dto.User2GroupDTO;
import com.hotelorga.foundation.iface.dto.User2GroupFetchQuery;
import com.hotelorga.foundation.iface.dto.UserDTO;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListWidgetList.SELECTIONMODE;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.rpc.user2group.ListDataProvider;
import com.hotelorga.foundation.web.client.rpc.user2group.RPCCRUDProxy;
import com.hotelorga.foundation.web.client.rpc.user2group.assoaeditor.item.ItemWidget;
import com.hotelorga.foundation.web.client.rpc.user2group.form.FormWidget;
import com.hotelorga.foundation.web.client.uiorga.linkbutton.LinkButtonWidget;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class AssoAEditorWidget extends WidgetView {

	private final GroupDTO thisDTO;

	public AssoAEditorWidget(GroupDTO dto, final boolean showFormOnCreation) {
		thisDTO = dto;

		final AbstrListDataProvider<User2GroupDTO, User2GroupFetchQuery> dataProvider = new ListDataProvider(new User2GroupFetchQuery());
		dataProvider.getFetchQuery().setIdB(thisDTO.getId());
		dataProvider.setPageSize(Integer.MAX_VALUE);

		final AbstrListWidgetList<User2GroupDTO, User2GroupFetchQuery> listAbstrWidgetList = new AbstrListWidgetList<User2GroupDTO, User2GroupFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final User2GroupDTO dto) {

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

		listAbstrWidgetList.getSelectionModel().addListener(new SelectionListener<User2GroupDTO>() {

			@Override
			public void onHasChanged(List<User2GroupDTO> selection) {

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
					popup.init(new com.hotelorga.foundation.web.client.rpc.user.choosersmall.ChooserSmallWidget(new SelectionListener<UserDTO>() {

						@Override
						public void onHasChanged(List<UserDTO> selection) {

							popup.closePopup();

							for (UserDTO dto : selection) {

								User2GroupDTO assoDTO = new User2GroupDTO();
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

	private void showForm(final AbstrListDataProvider<User2GroupDTO, User2GroupFetchQuery> dataProvider, final AbstrListWidgetList<User2GroupDTO, User2GroupFetchQuery> listAbstrWidgetList, List<User2GroupDTO> selection) {
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
