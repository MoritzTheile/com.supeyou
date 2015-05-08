package com.hotelorga.core.web.client.rpc.room.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.core.iface.dto.RoomDTO;
import com.hotelorga.core.iface.dto.RoomFetchQuery;
import com.hotelorga.core.web.client.rpc.room.ListDataProvider;
import com.hotelorga.core.web.client.rpc.room.chooserlarge.item.ItemWidget;
import com.hotelorga.core.web.client.rpc.room.form.FormWidget;
import com.hotelorga.core.web.client.rpc.room.imexport.ExportLink;
import com.hotelorga.core.web.client.rpc.room.imexport.ImportLink;
import com.hotelorga.core.web.client.rpc.room.query.QueryWidget;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.web.client.fields.types.FieldForDateType;
import com.hotelorga.foundation.web.client.model.LoginStateModel;
import com.hotelorga.foundation.web.client.resources.GWTSTATICS;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {
	private final AbstrListDataProvider<RoomDTO, RoomFetchQuery> dataProvider;
	private DateType fromDate;
	private DateType toDate;

	public ChooserLargeWidget() {

		if (LoginStateModel.i().userIsAdmin()) {// UI visibility
			pagerSlot.removeStyleName(GWTSTATICS.HIDESTYLE_CSS);
			createLink.removeStyleName(GWTSTATICS.HIDESTYLE_CSS);
			footer.removeStyleName(GWTSTATICS.HIDESTYLE_CSS);
		} else {
			pagerSlot.addStyleName(GWTSTATICS.HIDESTYLE_CSS);
			createLink.addStyleName(GWTSTATICS.HIDESTYLE_CSS);
			footer.addStyleName(GWTSTATICS.HIDESTYLE_CSS);
		}

		dataProvider = new ListDataProvider(new RoomFetchQuery());
		dataProvider.setPageSize(5);

		final AbstrListWidgetList<RoomDTO, RoomFetchQuery> widgetList = new AbstrListWidgetList<RoomDTO, RoomFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final RoomDTO data) {

				return getRoomWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<RoomDTO>() {

			@Override
			public void onHasChanged(List<RoomDTO> selection) {

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

		/*
		 * if no display scope is specified this algorithm finds one:
		 */
		if (fromDate == null && toDate == null) {

			this.fromDate = com.hotelorga.core.web.client.rpc.guest2room.chooserlarge.item.ItemWidget.incDateByDays(System.currentTimeMillis(), -30);
			this.toDate = com.hotelorga.core.web.client.rpc.guest2room.chooserlarge.item.ItemWidget.incDateByDays(System.currentTimeMillis(), 30);

		} else if (fromDate == null && toDate != null) {

			this.fromDate = com.hotelorga.core.web.client.rpc.guest2room.chooserlarge.item.ItemWidget.incDateByDays(toDate, -60);

		} else if (fromDate != null && toDate == null) {

			this.toDate = com.hotelorga.core.web.client.rpc.guest2room.chooserlarge.item.ItemWidget.incDateByDays(fromDate, 60);

		}

		fromdateSlot.add(new FieldForDateType(fromDate) {
			@Override
			public void onHasChanged(DateType value) {
				fromDate = value;
				setOrigValue(value);
				dataProvider.fetchData();
			}
		});

		todateSlot.add(new FieldForDateType(toDate) {
			@Override
			public void onHasChanged(DateType value) {
				toDate = value;
				setOrigValue(value);
				dataProvider.fetchData();
			}
		});

		pagerSlot.add(new ListPagerWidget(dataProvider));

		createLink.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				final PopupWidget formPopup = new PopupWidget(false);
				formPopup.setPosition(100, 700);
				formPopup.init(
						new FormWidget(dataProvider, new RoomDTO()) {
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

	public ItemWidget getRoomWidget(final RoomDTO data) {

		return new ItemWidget(data, fromDate, toDate);
	}

}
