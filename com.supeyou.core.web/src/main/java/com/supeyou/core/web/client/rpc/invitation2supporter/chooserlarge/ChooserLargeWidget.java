package com.supeyou.core.web.client.rpc.invitation2supporter.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterFetchQuery;
import com.supeyou.core.web.client.rpc.invitation2supporter.ListDataProvider;
import com.supeyou.core.web.client.rpc.invitation2supporter.chooserlarge.item.ItemWidget;
import com.supeyou.core.web.client.rpc.invitation2supporter.form.FormWidget;
import com.supeyou.core.web.client.rpc.invitation2supporter.imexport.ExportLink;
import com.supeyou.core.web.client.rpc.invitation2supporter.imexport.ImportLink;
import com.supeyou.core.web.client.rpc.invitation2supporter.query.QueryWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {

	private final AbstrListDataProvider<Invitation2SupporterDTO, Invitation2SupporterFetchQuery> dataProvider;

	public ChooserLargeWidget() {

		dataProvider = new ListDataProvider(new Invitation2SupporterFetchQuery());
		dataProvider.setPageSize(5);

		final AbstrListWidgetList<Invitation2SupporterDTO, Invitation2SupporterFetchQuery> widgetList = new AbstrListWidgetList<Invitation2SupporterDTO, Invitation2SupporterFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Invitation2SupporterDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<Invitation2SupporterDTO>() {

			@Override
			public void onHasChanged(List<Invitation2SupporterDTO> selection) {

				if (selection.size() == 1) {

					final PopupWidget formPopup = new PopupWidget(false);
					formPopup.setPosition(100, 700);
					formPopup.init(
							new FormWidget(dataProvider, selection.iterator().next(), true, true) {
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
						new FormWidget(dataProvider, new Invitation2SupporterDTO(), true, true) {
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
