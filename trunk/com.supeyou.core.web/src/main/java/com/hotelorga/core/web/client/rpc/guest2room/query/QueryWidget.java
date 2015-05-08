package com.hotelorga.core.web.client.rpc.guest2room.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.hotelorga.core.iface.dto.Guest2RoomDTO;
import com.hotelorga.core.iface.dto.Guest2RoomFetchQuery;
import com.hotelorga.core.iface.dto.RoomDTO;
import com.hotelorga.core.web.client.rpc.guest2room.query.sortdirection.SortDirectionWidget;
import com.hotelorga.core.web.client.rpc.room.chooserlarge.item.ItemWidget;
import com.hotelorga.foundation.iface.datatype.FetchQuery.SORTDIRECTION;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.web.client.fields.types.AbstrListBoxField;
import com.hotelorga.foundation.web.client.fields.types.FieldForDateType;
import com.hotelorga.foundation.web.client.resources.i18n.Text;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.ui.searchtext.SearchTextWidget;
import com.hotelorga.foundation.web.client.ui.searchtext.SearchTextWidget.Listener;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class QueryWidget extends WidgetView {

	public QueryWidget(final AbstrListDataProvider<Guest2RoomDTO, Guest2RoomFetchQuery> dataProvider) {

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

			// column2label.put(Guest2RoomFetchQuery.COLUMN_EMAILADDRESS, Text.i.QUERY_COLUMN_EMAILADDRESS());
			// column2label.put(Guest2RoomFetchQuery.COLUMN_BIRTHDATE, Text.i.QUERY_COLUMN_BIRTHDATE());
			// column2label.put(Guest2RoomFetchQuery.COLUMN_AMOUNT, Text.i.QUERY_COLUMN_AMOUNT());
			// column2label.put(Guest2RoomFetchQuery.COLUMN_ACTIVE, Text.i.QUERY_COLUMN_ACTIVE());

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

		fromSlot.add(new FieldForDateType(null) {
			@Override
			public void onHasChanged(DateType value) {
				dataProvider.getFetchQuery().setFrom(value);
				dataProvider.fetchData();
			}
		});

		toSlot.add(new FieldForDateType(null) {
			@Override
			public void onHasChanged(DateType value) {
				dataProvider.getFetchQuery().setTo(value);
				dataProvider.fetchData();
			}
		});

		roomSlot.add(new ItemWidget(dataProvider.getFetchQuery().getRoom()));
		roomSlot.addDomHandler(new ClickHandler() {

			PopupWidget popup;

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();
				popup = new PopupWidget(new com.hotelorga.core.web.client.rpc.room.choosersmall.ChooserSmallWidget(new SelectionListener<RoomDTO>() {

					@Override
					public void onHasChanged(List<RoomDTO> selection) {

						popup.closePopup();

						roomSlot.clear();
						for (RoomDTO room : selection) {
							dataProvider.getFetchQuery().setRoomAndRoomID(room);
							dataProvider.fetchData();
						}

						roomSlot.add(new ItemWidget(dataProvider.getFetchQuery().getRoom()));

					}

				}), true);

				popup.setPosition(roomSlot.getAbsoluteTop(), roomSlot.getAbsoluteLeft());

			}
		}, ClickEvent.getType());

	}

}
