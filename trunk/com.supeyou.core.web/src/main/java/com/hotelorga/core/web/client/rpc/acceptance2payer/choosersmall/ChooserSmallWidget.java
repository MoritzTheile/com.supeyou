package com.hotelorga.core.web.client.rpc.acceptance2payer.choosersmall;

import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.core.iface.dto.Acceptance2PayerDTO;
import com.hotelorga.core.iface.dto.Acceptance2PayerFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.hotelorga.core.web.client.rpc.acceptance2payer.ListDataProvider;
import com.hotelorga.core.web.client.rpc.acceptance2payer.choosersmall.item.ItemWidget;
import com.hotelorga.foundation.web.client.ui.searchtext.SearchTextWidget;
import com.hotelorga.foundation.web.client.ui.searchtext.SearchTextWidget.Listener;

public class ChooserSmallWidget extends WidgetView {

	public ChooserSmallWidget(SelectionListener<Acceptance2PayerDTO> selectionListener) {

		final AbstrListDataProvider<Acceptance2PayerDTO, Acceptance2PayerFetchQuery> dataProvider = new ListDataProvider(new Acceptance2PayerFetchQuery());
		dataProvider.setPageSize(5);

		searchSlot.add(new SearchTextWidget(dataProvider.getFetchQuery().getSearchString(), new Listener() {

			@Override
			public void search(String searchString) {
				dataProvider.getFetchQuery().setSearchString(searchString);
				dataProvider.fetchData();
			}
		}));

		AbstrListWidgetList<Acceptance2PayerDTO, Acceptance2PayerFetchQuery> widgetList = new AbstrListWidgetList<Acceptance2PayerDTO, Acceptance2PayerFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Acceptance2PayerDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(selectionListener);

		itemsSlot.add(widgetList);

		pagerSlot.add(new ListPagerWidget(dataProvider));

		dataProvider.fetchData();

	}

}
