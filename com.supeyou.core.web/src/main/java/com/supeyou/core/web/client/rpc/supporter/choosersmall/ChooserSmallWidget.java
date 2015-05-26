package com.supeyou.core.web.client.rpc.supporter.choosersmall;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterFetchQuery;
import com.supeyou.core.web.client.rpc.supporter.ListDataProvider;
import com.supeyou.core.web.client.rpc.supporter.choosersmall.item.ItemWidget;
import com.supeyou.core.web.client.rpc.supporter.form.FormWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.supeyou.crudie.web.client.ui.searchtext.SearchTextWidget;
import com.supeyou.crudie.web.client.ui.searchtext.SearchTextWidget.Listener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class ChooserSmallWidget extends WidgetView {

	private final AbstrListDataProvider<SupporterDTO, SupporterFetchQuery> dataProvider;
	private final SelectionListener<SupporterDTO> selectionListener;

	public ChooserSmallWidget(SelectionListener<SupporterDTO> selectionListener) {

		this.selectionListener = selectionListener;

		dataProvider = new ListDataProvider(new SupporterFetchQuery());
		dataProvider.setPageSize(5);

		searchSlot.add(new SearchTextWidget(dataProvider.getFetchQuery().getSearchString(), new Listener() {

			@Override
			public void search(String searchString) {
				dataProvider.getFetchQuery().setSearchString(searchString);
				dataProvider.fetchData();
			}
		}));

		AbstrListWidgetList<SupporterDTO, SupporterFetchQuery> widgetList = new AbstrListWidgetList<SupporterDTO, SupporterFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final SupporterDTO data) {

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

				showCreationFormInPopup(new SupporterDTO());
			}

		}, ClickEvent.getType());

		createLink.add(new Label());

		dataProvider.fetchData();

	}

	private PopupWidget popup;

	private void showCreationFormInPopup(SupporterDTO dto) {
		popup = new PopupWidget(

				new FormWidget(dataProvider, dto) {

					@Override
					protected void close() {
						popup.closePopup();
					}

					@Override
					protected void updadded(SupporterDTO dto) {
						List<SupporterDTO> selection = new ArrayList<SupporterDTO>();
						selection.add(dto);
						selectionListener.onHasChanged(selection);
					}
				}, 100, 700, true
				);
	}

}
