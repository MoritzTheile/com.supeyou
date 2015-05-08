package com.supeyou.crudie.web.client.rpc.user2group.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.crudie.iface.dto.User2GroupDTO;
import com.supeyou.crudie.iface.dto.User2GroupFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.supeyou.crudie.web.client.rpc.user2group.ListDataProvider;
import com.supeyou.crudie.web.client.rpc.user2group.chooserlarge.item.ItemWidget;
import com.supeyou.crudie.web.client.rpc.user2group.form.FormWidget;
import com.supeyou.crudie.web.client.rpc.user2group.imexport.ExportLink;
import com.supeyou.crudie.web.client.rpc.user2group.imexport.ImportLink;
import com.supeyou.crudie.web.client.rpc.user2group.query.QueryWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {

	private final AbstrListDataProvider<User2GroupDTO, User2GroupFetchQuery> dataProvider;

	public ChooserLargeWidget() {

		dataProvider = new ListDataProvider(new User2GroupFetchQuery());
		dataProvider.setPageSize(5);

		final AbstrListWidgetList<User2GroupDTO, User2GroupFetchQuery> widgetList = new AbstrListWidgetList<User2GroupDTO, User2GroupFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final User2GroupDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<User2GroupDTO>() {

			@Override
			public void onHasChanged(List<User2GroupDTO> selection) {

				if (selection.size() == 1) {

					final PopupWidget formPopup = new PopupWidget(false);
					formPopup.setPosition(100, 700);
					formPopup.init(
							new FormWidget(dataProvider, selection.iterator().next(), true, true) {
								@Override
								protected void close() {
									widgetList.getSelectionModel().clearSelection();
									formPopup.closePopup();
								}
							}
							);

				} else {
					// nothing yet
				}

			}

		});

		itemsSlot.add(widgetList);

		querySlot.add(new QueryWidget(dataProvider));

		pagerSlot.add(new ListPagerWidget(dataProvider));

		createLink.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				event.stopPropagation();

				final PopupWidget formPopup = new PopupWidget(false);
				formPopup.setPosition(100, 700);
				formPopup.init(
						new FormWidget(dataProvider, new User2GroupDTO(), true, true) {
							@Override
							protected void close() {
								formPopup.closePopup();
							}
						}
						);

			}

		}, ClickEvent.getType());

		dataProvider.fetchData();

		footer.add(new ExportLink(dataProvider.getFetchQuery()));

		footer.add(new ImportLink() {
			@Override
			public void importFinished() {
				dataProvider.fetchData();
			}
		});

	}

}
