package com.hotelorga.core.web.client.rpc.guestgroup.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.core.iface.dto.GuestGroupDTO;
import com.hotelorga.core.iface.dto.GuestGroupFetchQuery;
import com.hotelorga.core.web.client.rpc.guestgroup.ListDataProvider;
import com.hotelorga.core.web.client.rpc.guestgroup.chooserlarge.item.ItemWidget;
import com.hotelorga.core.web.client.rpc.guestgroup.form.FormWidget;
import com.hotelorga.core.web.client.rpc.guestgroup.imexport.ExportLink;
import com.hotelorga.core.web.client.rpc.guestgroup.imexport.ImportLink;
import com.hotelorga.core.web.client.rpc.guestgroup.query.QueryWidget;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {
	private final AbstrListDataProvider<GuestGroupDTO, GuestGroupFetchQuery> dataProvider;

	public ChooserLargeWidget() {

		dataProvider = new ListDataProvider(new GuestGroupFetchQuery());
		dataProvider.setPageSize(5);

		final AbstrListWidgetList<GuestGroupDTO, GuestGroupFetchQuery> widgetList = new AbstrListWidgetList<GuestGroupDTO, GuestGroupFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final GuestGroupDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<GuestGroupDTO>() {

			@Override
			public void onHasChanged(List<GuestGroupDTO> selection) {

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

				final PopupWidget formPopup = new PopupWidget(false);
				formPopup.setPosition(100, 700);
				formPopup.init(
						new FormWidget(dataProvider, new GuestGroupDTO()) {
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
