package com.hotelorga.foundation.web.client.rpc.user2group.assobeditor;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.foundation.iface.dto.GroupDTO;
import com.hotelorga.foundation.iface.dto.User2GroupDTO;
import com.hotelorga.foundation.iface.dto.User2GroupFetchQuery;
import com.hotelorga.foundation.iface.dto.UserDTO;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.rpc.user2group.ListDataProvider;
import com.hotelorga.foundation.web.client.rpc.user2group.RPCCRUDProxy;
import com.hotelorga.foundation.web.client.rpc.user2group.assobeditor.item.ItemWidget;
import com.hotelorga.foundation.web.client.rpc.user2group.form.FormWidget;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class AssoBEditorWidget extends WidgetView {

	private final UserDTO thisDTO;

	public AssoBEditorWidget(UserDTO dto, final boolean showFormOnCreation) {

		thisDTO = dto;

		final AbstrListDataProvider<User2GroupDTO, User2GroupFetchQuery> dataProvider = new ListDataProvider(new User2GroupFetchQuery());
		dataProvider.getFetchQuery().setIdA(thisDTO.getId());
		dataProvider.setPageSize(Integer.MAX_VALUE);

		final AbstrListWidgetList<User2GroupDTO, User2GroupFetchQuery> listAbstrWidgetList = new AbstrListWidgetList<User2GroupDTO, User2GroupFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final User2GroupDTO dto) {

				return new ItemWidget(dto);

			}

		};

		listAbstrWidgetList.getSelectionModel().addListener(new SelectionListener<User2GroupDTO>() {

			@Override
			public void onHasChanged(List<User2GroupDTO> selection) {

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
					popup.init(new com.hotelorga.foundation.web.client.rpc.group.choosersmall.ChooserSmallWidget(new SelectionListener<GroupDTO>() {

						@Override
						public void onHasChanged(List<GroupDTO> selection) {

							popup.closePopup();

							for (GroupDTO dto : selection) {

								User2GroupDTO assoDTO = new User2GroupDTO();
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
