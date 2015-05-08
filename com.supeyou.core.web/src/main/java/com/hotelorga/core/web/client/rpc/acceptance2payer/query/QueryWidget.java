package com.hotelorga.core.web.client.rpc.acceptance2payer.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotelorga.core.iface.dto.Acceptance2PayerDTO;
import com.hotelorga.core.iface.dto.Acceptance2PayerFetchQuery;
import com.hotelorga.core.web.client.rpc.acceptance2payer.query.sortdirection.SortDirectionWidget;
import com.hotelorga.foundation.iface.datatype.FetchQuery.SORTDIRECTION;
import com.hotelorga.foundation.web.client.fields.types.AbstrListBoxField;
import com.hotelorga.foundation.web.client.resources.i18n.Text;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.ui.searchtext.SearchTextWidget;
import com.hotelorga.foundation.web.client.ui.searchtext.SearchTextWidget.Listener;

public class QueryWidget extends WidgetView {

	public QueryWidget(final AbstrListDataProvider<Acceptance2PayerDTO, Acceptance2PayerFetchQuery> dataProvider) {

		searchSlot.add(new SearchTextWidget(dataProvider.getFetchQuery().getSearchString(), new Listener() {

			@Override
			public void search(String searchString) {
				dataProvider.getFetchQuery().setSearchString(searchString);
				dataProvider.fetchData();
			}
		}));

		// Map<String, Object> name2Object = new HashMap<String, Object>();
		// name2Object.put("test1", "testObject1");
		// name2Object.put("test2", "testObject2");
		// sortSlot.add(new DropDownListPresenter("test1", name2Object, new DropDownListListener() {
		//
		// @Override
		// public void clicked(Object object) {
		//
		// }
		//
		// }));
		{
			final Map<Integer, String> pagesize2string = new HashMap<Integer, String>();

			pagesize2string.put(new Integer(5), Text.i.QUERY_PAGESIZE_Five());
			pagesize2string.put(new Integer(10), Text.i.QUERY_PAGESIZE_Ten());
			pagesize2string.put(new Integer(25), Text.i.QUERY_PAGESIZE_TwentyFive());
			pagesize2string.put(new Integer(50), Text.i.QUERY_PAGESIZE_Fifty());
			pagesize2string.put(new Integer(100), Text.i.QUERY_PAGESIZE_OneHundred());

			pageSizeSlot.add(new AbstrListBoxField<Integer>(dataProvider.getPageSize(), false) {

				@Override
				public void onHasChanged(Integer value) {

					if (value == null) {
						value = dataProvider.getPageSize();
					}
					setOrigValue(value);
					dataProvider.setPageSize(value);
					dataProvider.fetchData();

				}

				@Override
				public String value2displayName(Integer value) {
					return pagesize2string.get(value);
				}

				@Override
				public List<Integer> getValues() {
					List<Integer> values = new ArrayList<>(pagesize2string.keySet());
					Collections.sort(values);
					return values;
				}

			});
		}
		{
			final Map<String, String> column2label = new HashMap<String, String>();

			// column2label.put(Acceptance2PayerFetchQuery.COLUMN_EMAILADDRESS, Text.i.QUERY_COLUMN_EMAILADDRESS());
			// column2label.put(Acceptance2PayerFetchQuery.COLUMN_BIRTHDATE, Text.i.QUERY_COLUMN_BIRTHDATE());
			// column2label.put(Acceptance2PayerFetchQuery.COLUMN_AMOUNT, Text.i.QUERY_COLUMN_AMOUNT());
			// column2label.put(Acceptance2PayerFetchQuery.COLUMN_ACTIVE, Text.i.QUERY_COLUMN_ACTIVE());

			sortSlot.add(new AbstrListBoxField<String>(dataProvider.getFetchQuery().getOrderByColumn(), false) {

				@Override
				public void onHasChanged(String value) {
					setOrigValue(value);
					dataProvider.getFetchQuery().setOrderByColumn(value);
					dataProvider.fetchData();
				}

				@Override
				public String value2displayName(String value) {
					return column2label.get(value);
				}

				@Override
				public List<String> getValues() {
					List<String> values = new ArrayList<>(column2label.keySet());
					Collections.sort(values);
					return values;
				}
			});

			sortSlot.add(new SortDirectionWidget(dataProvider.getFetchQuery().getSortDirection()) {

				@Override
				public void hasChanged(SORTDIRECTION sortdirection) {

					dataProvider.getFetchQuery().setSortDirection(sortdirection);

					dataProvider.fetchData();

				}

			});
		}
	}

}
