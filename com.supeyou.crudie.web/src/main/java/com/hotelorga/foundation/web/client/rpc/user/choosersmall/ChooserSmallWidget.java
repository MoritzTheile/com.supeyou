package com.hotelorga.foundation.web.client.rpc.user.choosersmall;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.foundation.iface.dto.UserDTO;
import com.hotelorga.foundation.iface.dto.UserFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.hotelorga.foundation.web.client.rpc.user.ListDataProvider;
import com.hotelorga.foundation.web.client.rpc.user.choosersmall.item.ItemWidget;
import com.hotelorga.foundation.web.client.rpc.user.form.FormWidget;
import com.hotelorga.foundation.web.client.ui.searchtext.SearchTextWidget;
import com.hotelorga.foundation.web.client.ui.searchtext.SearchTextWidget.Listener;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class ChooserSmallWidget extends WidgetView {

	private final AbstrListDataProvider<UserDTO, UserFetchQuery> dataProvider;
	private final SelectionListener<UserDTO> selectionListener;

	public ChooserSmallWidget(SelectionListener<UserDTO> selectionListener) {

		this.selectionListener = selectionListener;

		dataProvider = new ListDataProvider(new UserFetchQuery());
		dataProvider.setPageSize(5);

		searchSlot.add(new SearchTextWidget(dataProvider.getFetchQuery().getSearchString(), new Listener() {

			@Override
			public void search(String searchString) {
				dataProvider.getFetchQuery().setSearchString(searchString);
				dataProvider.fetchData();
			}
		}));

		AbstrListWidgetList<UserDTO, UserFetchQuery> widgetList = new AbstrListWidgetList<UserDTO, UserFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final UserDTO data) {

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

				showCreationFormInPopup(new UserDTO());

			}

		}, ClickEvent.getType());

		createLink.add(new Label());

		dataProvider.fetchData();

	}

	private PopupWidget popup;

	private void showCreationFormInPopup(UserDTO dto) {
		popup = new PopupWidget(

				new FormWidget(dataProvider, dto) {
					@Override
					protected void close() {
						popup.closePopup();
					}

					@Override
					protected void updadded(UserDTO dto) {
						List<UserDTO> selection = new ArrayList<UserDTO>();
						selection.add(dto);
						selectionListener.onHasChanged(selection);
					}
				}, 100, 700, true
				);
	}

}
