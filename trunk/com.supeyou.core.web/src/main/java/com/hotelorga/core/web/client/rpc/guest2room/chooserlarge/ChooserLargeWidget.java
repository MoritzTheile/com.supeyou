package com.hotelorga.core.web.client.rpc.guest2room.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.core.iface.dto.Guest2RoomDTO;
import com.hotelorga.core.iface.dto.Guest2RoomFetchQuery;
import com.hotelorga.core.web.client.rpc.guest2room.ListDataProvider;
import com.hotelorga.core.web.client.rpc.guest2room.chooserlarge.item.ItemWidget;
import com.hotelorga.core.web.client.rpc.guest2room.form.FormWidget;
import com.hotelorga.core.web.client.rpc.guest2room.imexport.ExportLink;
import com.hotelorga.core.web.client.rpc.guest2room.imexport.ImportLink;
import com.hotelorga.core.web.client.rpc.guest2room.query.QueryWidget;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {

	public ChooserLargeWidget() {

		final AbstrListDataProvider<Guest2RoomDTO, Guest2RoomFetchQuery> dataProvider = new ListDataProvider(new Guest2RoomFetchQuery());
		dataProvider.setPageSize(5);

		AbstrListWidgetList<Guest2RoomDTO, Guest2RoomFetchQuery> widgetList = new AbstrListWidgetList<Guest2RoomDTO, Guest2RoomFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Guest2RoomDTO data) {

				return new ItemWidget(data, dataProvider.getFetchQuery().getFrom(), dataProvider.getFetchQuery().getTo());

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<Guest2RoomDTO>() {

			PopupWidget popup;

			@Override
			public void onHasChanged(List<Guest2RoomDTO> selection) {

				if (selection.size() == 1) {

					popup = new PopupWidget(

							new FormWidget(dataProvider, selection.iterator().next(), false, false) {
								@Override
								protected void close() {
									popup.closePopup();
								}
							}, 100, 700, false
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

				new PopupWidget(new FormWidget(dataProvider, false, false), false);

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
