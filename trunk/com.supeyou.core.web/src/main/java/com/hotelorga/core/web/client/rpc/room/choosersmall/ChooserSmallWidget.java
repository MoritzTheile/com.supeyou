package com.hotelorga.core.web.client.rpc.room.choosersmall;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.core.iface.dto.RoomDTO;
import com.hotelorga.core.iface.dto.RoomFetchQuery;
import com.hotelorga.core.web.client.rpc.room.ListDataProvider;
import com.hotelorga.core.web.client.rpc.room.choosersmall.item.ItemWidget;
import com.hotelorga.core.web.client.rpc.room.form.FormWidget;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.hotelorga.foundation.web.client.ui.searchtext.SearchTextWidget;
import com.hotelorga.foundation.web.client.ui.searchtext.SearchTextWidget.Listener;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class ChooserSmallWidget extends WidgetView {

	private final AbstrListDataProvider<RoomDTO, RoomFetchQuery> dataProvider;
	private final SelectionListener<RoomDTO> selectionListener;

	public ChooserSmallWidget(SelectionListener<RoomDTO> selectionListener) {

		this.selectionListener = selectionListener;

		dataProvider = new ListDataProvider(new RoomFetchQuery());
		dataProvider.setPageSize(5);

		searchSlot.add(new SearchTextWidget(dataProvider.getFetchQuery().getSearchString(), new Listener() {

			@Override
			public void search(String searchString) {
				dataProvider.getFetchQuery().setSearchString(searchString);
				dataProvider.fetchData();
			}
		}));

		AbstrListWidgetList<RoomDTO, RoomFetchQuery> widgetList = new AbstrListWidgetList<RoomDTO, RoomFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final RoomDTO data) {

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

				showCreationFormInPopup(new RoomDTO());
			}

		}, ClickEvent.getType());

		createLink.add(new Label());

		dataProvider.fetchData();

	}

	private PopupWidget popup;

	private void showCreationFormInPopup(RoomDTO dto) {
		popup = new PopupWidget(

				new FormWidget(dataProvider, dto) {

					@Override
					protected void close() {
						popup.closePopup();
					}

					@Override
					protected void updadded(RoomDTO dto) {
						List<RoomDTO> selection = new ArrayList<RoomDTO>();
						selection.add(dto);
						selectionListener.onHasChanged(selection);
					}
				}, 100, 700, true
				);
	}

}
