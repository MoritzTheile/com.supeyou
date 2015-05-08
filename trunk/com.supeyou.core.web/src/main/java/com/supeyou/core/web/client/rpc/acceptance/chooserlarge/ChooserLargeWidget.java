package com.supeyou.core.web.client.rpc.acceptance.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.AcceptanceFetchQuery;
import com.supeyou.core.web.client.rpc.acceptance.ListDataProvider;
import com.supeyou.core.web.client.rpc.acceptance.chooserlarge.item.ItemWidget;
import com.supeyou.core.web.client.rpc.acceptance.form.FormForFieldsWidget;
import com.supeyou.core.web.client.rpc.acceptance.imexport.ExportLink;
import com.supeyou.core.web.client.rpc.acceptance.imexport.ImportLink;
import com.supeyou.core.web.client.rpc.acceptance.query.QueryWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {
	private final AbstrListDataProvider<AcceptanceDTO, AcceptanceFetchQuery> dataProvider;

	public ChooserLargeWidget() {

		dataProvider = new ListDataProvider(new AcceptanceFetchQuery());
		dataProvider.setPageSize(5);

		final AbstrListWidgetList<AcceptanceDTO, AcceptanceFetchQuery> widgetList = new AbstrListWidgetList<AcceptanceDTO, AcceptanceFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final AcceptanceDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<AcceptanceDTO>() {

			@Override
			public void onHasChanged(List<AcceptanceDTO> selection) {

				if (selection.size() == 1) {

					final PopupWidget formPopup = new PopupWidget(false);
					formPopup.setPosition(100, 700);
					formPopup.init(
							new FormForFieldsWidget(dataProvider, selection.iterator().next()) {
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
						new FormForFieldsWidget(dataProvider, new AcceptanceDTO()) {
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
