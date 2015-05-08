package com.supeyou.core.web.client.rpc.guest2guestgroup.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.Guest2GuestGroupDTO;
import com.supeyou.core.iface.dto.Guest2GuestGroupFetchQuery;
import com.supeyou.core.web.client.rpc.guest2guestgroup.ListDataProvider;
import com.supeyou.core.web.client.rpc.guest2guestgroup.chooserlarge.item.ItemWidget;
import com.supeyou.core.web.client.rpc.guest2guestgroup.form.FormWidget;
import com.supeyou.core.web.client.rpc.guest2guestgroup.imexport.ExportLink;
import com.supeyou.core.web.client.rpc.guest2guestgroup.imexport.ImportLink;
import com.supeyou.core.web.client.rpc.guest2guestgroup.query.QueryWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {

	public ChooserLargeWidget() {

		final AbstrListDataProvider<Guest2GuestGroupDTO, Guest2GuestGroupFetchQuery> dataProvider = new ListDataProvider(new Guest2GuestGroupFetchQuery());
		dataProvider.setPageSize(5);

		AbstrListWidgetList<Guest2GuestGroupDTO, Guest2GuestGroupFetchQuery> widgetList = new AbstrListWidgetList<Guest2GuestGroupDTO, Guest2GuestGroupFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Guest2GuestGroupDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<Guest2GuestGroupDTO>() {

			PopupWidget popup;

			@Override
			public void onHasChanged(List<Guest2GuestGroupDTO> selection) {

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

				new PopupWidget(new FormWidget(dataProvider, new Guest2GuestGroupDTO(), false, false), false);

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
