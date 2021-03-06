package com.supeyou.core.web.client.rpc.donation.query;

import com.supeyou.core.iface.dto.DonationDTO;
import com.supeyou.core.iface.dto.DonationFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.ui.searchtext.SearchTextWidget;
import com.supeyou.crudie.web.client.ui.searchtext.SearchTextWidget.Listener;

public class QueryWidget extends WidgetView {

	public QueryWidget(final AbstrListDataProvider<DonationDTO, DonationFetchQuery> dataProvider) {

		searchSlot.add(new SearchTextWidget(dataProvider.getFetchQuery().getSearchString(), new Listener() {

			@Override
			public void search(String searchString) {
				dataProvider.getFetchQuery().setSearchString(searchString);
				dataProvider.fetchData();
			}
		}));

		// {
		// final Map<Integer, String> pagesize2string = new HashMap<Integer, String>();
		//
		// pagesize2string.put(new Integer(5), Text.i.QUERY_PAGESIZE_Five());
		// pagesize2string.put(new Integer(10), Text.i.QUERY_PAGESIZE_Ten());
		// pagesize2string.put(new Integer(25), Text.i.QUERY_PAGESIZE_TwentyFive());
		// pagesize2string.put(new Integer(50), Text.i.QUERY_PAGESIZE_Fifty());
		// pagesize2string.put(new Integer(100), Text.i.QUERY_PAGESIZE_OneHundred());
		//
		// dataProvider.setPageSize(5);
		//
		// pageSizeSlot.add(new AbstrListBoxField<Integer>(dataProvider.getPageSize(), false) {
		//
		// @Override
		// public void onHasChanged(Integer value) {
		//
		// if (value == null) {
		// value = dataProvider.getPageSize();
		// }
		// setOrigValue(value);
		// dataProvider.setPageSize(value);
		// dataProvider.fetchData();
		//
		// }
		//
		// @Override
		// public String value2displayName(Integer value) {
		// return pagesize2string.get(value);
		// }
		//
		// @Override
		// public List<Integer> getValues() {
		// List<Integer> values = new ArrayList<>(pagesize2string.keySet());
		// Collections.sort(values);
		// return values;
		// }
		//
		// });
		// }
		// {
		// final Map<String, String> column2label = new HashMap<String, String>();
		//
		// column2label.put("name", "Namen");
		//
		// sortSlot.add(new AbstrListBoxField<String>(dataProvider.getFetchQuery().getOrderByColumn(), false) {
		//
		// @Override
		// public void onHasChanged(String value) {
		// setOrigValue(value);
		// dataProvider.getFetchQuery().setOrderByColumn(value);
		// dataProvider.fetchData();
		// }
		//
		// @Override
		// public String value2displayName(String value) {
		// return column2label.get(value);
		// }
		//
		// @Override
		// public List<String> getValues() {
		// List<String> values = new ArrayList<>(column2label.keySet());
		// Collections.sort(values);
		// return values;
		// }
		// });
		//
		// sortSlot.add(new SortDirectionWidget(dataProvider.getFetchQuery().getSortDirection()) {
		//
		// @Override
		// public void hasChanged(SORTDIRECTION sortdirection) {
		//
		// dataProvider.getFetchQuery().setSortDirection(sortdirection);
		//
		// dataProvider.fetchData();
		//
		// }
		//
		// });
		// }
	}

}
