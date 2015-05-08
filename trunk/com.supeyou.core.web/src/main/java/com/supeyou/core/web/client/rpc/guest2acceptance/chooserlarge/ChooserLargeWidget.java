package com.supeyou.core.web.client.rpc.guest2acceptance.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.Guest2AcceptanceDTO;
import com.supeyou.core.iface.dto.Guest2AcceptanceFetchQuery;
import com.supeyou.core.web.client.rpc.guest2acceptance.ListDataProvider;
import com.supeyou.core.web.client.rpc.guest2acceptance.chooserlarge.item.ItemWidget;
import com.supeyou.core.web.client.rpc.guest2acceptance.form.FormWidget;
import com.supeyou.core.web.client.rpc.guest2acceptance.imexport.ExportLink;
import com.supeyou.core.web.client.rpc.guest2acceptance.imexport.ImportLink;
import com.supeyou.core.web.client.rpc.guest2acceptance.query.QueryWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {

	public ChooserLargeWidget() {

		final AbstrListDataProvider<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery> dataProvider = new ListDataProvider(new Guest2AcceptanceFetchQuery());
		dataProvider.setPageSize(5);

		AbstrListWidgetList<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery> widgetList = new AbstrListWidgetList<Guest2AcceptanceDTO, Guest2AcceptanceFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Guest2AcceptanceDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<Guest2AcceptanceDTO>() {

			PopupWidget popup;

			@Override
			public void onHasChanged(List<Guest2AcceptanceDTO> selection) {

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
