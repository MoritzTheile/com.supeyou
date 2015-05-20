package com.supeyou.actor.web.client.rpc.actinguser2session.assoaeditor;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.actor.iface.dto.ActingUser2SessionDTO;
import com.supeyou.actor.iface.dto.ActingUser2SessionFetchQuery;
import com.supeyou.actor.iface.dto.ActingUserDTO;
import com.supeyou.actor.iface.dto.SessionDTO;
import com.supeyou.actor.web.client.rpc.actinguser2session.ListDataProvider;
import com.supeyou.actor.web.client.rpc.actinguser2session.RPCCRUDProxy;
import com.supeyou.actor.web.client.rpc.actinguser2session.assoaeditor.item.ItemWidget;
import com.supeyou.actor.web.client.rpc.actinguser2session.form.FormWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList.SELECTIONMODE;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.uiorga.linkbutton.LinkButtonWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class AssoAEditorWidget extends WidgetView {

	private final SessionDTO thisDTO;

	public AssoAEditorWidget(SessionDTO dto, final boolean showFormOnCreation) {
		thisDTO = dto;

		final AbstrListDataProvider<ActingUser2SessionDTO, ActingUser2SessionFetchQuery> dataProvider = new ListDataProvider(new ActingUser2SessionFetchQuery());
		dataProvider.getFetchQuery().setIdB(thisDTO.getId());
		dataProvider.setPageSize(Integer.MAX_VALUE);

		final AbstrListWidgetList<ActingUser2SessionDTO, ActingUser2SessionFetchQuery> listAbstrWidgetList = new AbstrListWidgetList<ActingUser2SessionDTO, ActingUser2SessionFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final ActingUser2SessionDTO dto) {

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

		listAbstrWidgetList.getSelectionModel().addListener(new SelectionListener<ActingUser2SessionDTO>() {

			@Override
			public void onHasChanged(List<ActingUser2SessionDTO> selection) {

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
					popup.init(new com.supeyou.actor.web.client.rpc.actinguser.choosersmall.ChooserSmallWidget(new SelectionListener<ActingUserDTO>() {

						@Override
						public void onHasChanged(List<ActingUserDTO> selection) {

							popup.closePopup();

							for (ActingUserDTO dto : selection) {

								ActingUser2SessionDTO assoDTO = new ActingUser2SessionDTO();
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

	private void showForm(final AbstrListDataProvider<ActingUser2SessionDTO, ActingUser2SessionFetchQuery> dataProvider, final AbstrListWidgetList<ActingUser2SessionDTO, ActingUser2SessionFetchQuery> listAbstrWidgetList, List<ActingUser2SessionDTO> selection) {
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
