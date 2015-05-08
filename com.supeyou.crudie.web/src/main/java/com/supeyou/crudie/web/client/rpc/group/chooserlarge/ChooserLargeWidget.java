package com.supeyou.crudie.web.client.rpc.group.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.crudie.iface.dto.GroupDTO;
import com.supeyou.crudie.iface.dto.GroupFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.supeyou.crudie.web.client.rpc.group.ListDataProvider;
import com.supeyou.crudie.web.client.rpc.group.chooserlarge.item.ItemWidget;
import com.supeyou.crudie.web.client.rpc.group.form.FormWidget;
import com.supeyou.crudie.web.client.rpc.group.imexport.ExportLink;
import com.supeyou.crudie.web.client.rpc.group.imexport.ImportLink;
import com.supeyou.crudie.web.client.rpc.group.query.QueryWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {
	private final AbstrListDataProvider<GroupDTO, GroupFetchQuery> dataProvider;

	public ChooserLargeWidget() {

		dataProvider = new ListDataProvider(new GroupFetchQuery());
		dataProvider.setPageSize(5);

		final AbstrListWidgetList<GroupDTO, GroupFetchQuery> widgetList = new AbstrListWidgetList<GroupDTO, GroupFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final GroupDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<GroupDTO>() {

			@Override
			public void onHasChanged(List<GroupDTO> selection) {

				if (selection.size() == 1) {

					final PopupWidget formPopup = new PopupWidget(false);
					formPopup.setPosition(100, 700);
					formPopup.init(
							new FormWidget(dataProvider, selection.iterator().next()) {
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
						new FormWidget(dataProvider, new GroupDTO()) {
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
