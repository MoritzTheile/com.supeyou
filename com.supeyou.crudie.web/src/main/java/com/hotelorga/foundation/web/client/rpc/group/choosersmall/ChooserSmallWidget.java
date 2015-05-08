package com.hotelorga.foundation.web.client.rpc.group.choosersmall;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.foundation.iface.dto.GroupDTO;
import com.hotelorga.foundation.iface.dto.GroupFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.hotelorga.foundation.web.client.rpc.group.ListDataProvider;
import com.hotelorga.foundation.web.client.rpc.group.choosersmall.item.ItemWidget;
import com.hotelorga.foundation.web.client.rpc.group.form.FormWidget;
import com.hotelorga.foundation.web.client.ui.searchtext.SearchTextWidget;
import com.hotelorga.foundation.web.client.ui.searchtext.SearchTextWidget.Listener;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class ChooserSmallWidget extends WidgetView {

	private final AbstrListDataProvider<GroupDTO, GroupFetchQuery> dataProvider;
	private final SelectionListener<GroupDTO> selectionListener;

	public ChooserSmallWidget(SelectionListener<GroupDTO> selectionListener) {

		this.selectionListener = selectionListener;

		dataProvider = new ListDataProvider(new GroupFetchQuery());
		dataProvider.setPageSize(5);

		searchSlot.add(new SearchTextWidget(dataProvider.getFetchQuery().getSearchString(), new Listener() {

			@Override
			public void search(String searchString) {
				dataProvider.getFetchQuery().setSearchString(searchString);
				dataProvider.fetchData();
			}
		}));

		AbstrListWidgetList<GroupDTO, GroupFetchQuery> widgetList = new AbstrListWidgetList<GroupDTO, GroupFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final GroupDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(selectionListener);

		itemsSlot.add(widgetList);

		pagerSlot.add(new ListPagerWidget(dataProvider));

		createLink.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				showCreationFormInPopup(new GroupDTO());
			}

		}, ClickEvent.getType());

		createLink.add(new Label());

		dataProvider.fetchData();

	}

	private PopupWidget popup;

	private void showCreationFormInPopup(GroupDTO dto) {
		popup = new PopupWidget(

				new FormWidget(dataProvider, dto) {

					@Override
					protected void close() {
						popup.closePopup();
					}

					@Override
					protected void updadded(GroupDTO dto) {
						List<GroupDTO> selection = new ArrayList<GroupDTO>();
						selection.add(dto);
						selectionListener.onHasChanged(selection);
					}
				}, 100, 700, true
				);
	}

}
