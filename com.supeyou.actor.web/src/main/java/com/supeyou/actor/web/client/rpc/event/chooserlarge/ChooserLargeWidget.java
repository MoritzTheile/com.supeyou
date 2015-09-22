package com.supeyou.actor.web.client.rpc.event.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.actor.iface.dto.EventDTO;
import com.supeyou.actor.iface.dto.EventFetchQuery;
import com.supeyou.actor.web.client.rpc.event.ListDataProvider;
import com.supeyou.actor.web.client.rpc.event.chooserlarge.item.ItemWidget;
import com.supeyou.actor.web.client.rpc.event.form.FormWidget;
import com.supeyou.actor.web.client.rpc.event.imexport.ExportLink;
import com.supeyou.actor.web.client.rpc.event.imexport.ImportLink;
import com.supeyou.actor.web.client.rpc.event.query.QueryWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {
	private final AbstrListDataProvider<EventDTO, EventFetchQuery> dataProvider;

	public ChooserLargeWidget() {

		dataProvider = new ListDataProvider(new EventFetchQuery());
		dataProvider.setPageSize(50);

		final AbstrListWidgetList<EventDTO, EventFetchQuery> widgetList = new AbstrListWidgetList<EventDTO, EventFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final EventDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<EventDTO>() {

			@Override
			public void onHasChanged(List<EventDTO> selection) {

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

		pagerSlot.add(new ListPagerWidget(dataProvider));

		createLink.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				event.stopPropagation();

				final PopupWidget formPopup = new PopupWidget(false);
				formPopup.setPosition(100, 700);
				formPopup.init(
						new FormWidget(dataProvider, new EventDTO()) {
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

}
