package com.hotelorga.core.web.client.rpc.acceptance2payer.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.core.iface.dto.Acceptance2PayerDTO;
import com.hotelorga.core.iface.dto.Acceptance2PayerFetchQuery;
import com.hotelorga.core.web.client.rpc.acceptance2payer.ListDataProvider;
import com.hotelorga.core.web.client.rpc.acceptance2payer.assoaeditor.item.ItemWidget;
import com.hotelorga.core.web.client.rpc.acceptance2payer.form.FormWidget;
import com.hotelorga.core.web.client.rpc.acceptance2payer.imexport.ExportLink;
import com.hotelorga.core.web.client.rpc.acceptance2payer.imexport.ImportLink;
import com.hotelorga.core.web.client.rpc.acceptance2payer.query.QueryWidget;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {

	public ChooserLargeWidget() {

		final AbstrListDataProvider<Acceptance2PayerDTO, Acceptance2PayerFetchQuery> dataProvider = new ListDataProvider(new Acceptance2PayerFetchQuery());
		dataProvider.setPageSize(5);

		AbstrListWidgetList<Acceptance2PayerDTO, Acceptance2PayerFetchQuery> widgetList = new AbstrListWidgetList<Acceptance2PayerDTO, Acceptance2PayerFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Acceptance2PayerDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<Acceptance2PayerDTO>() {

			PopupWidget popup;

			@Override
			public void onHasChanged(List<Acceptance2PayerDTO> selection) {

				if (selection.size() == 1) {

					popup = new PopupWidget(

							new FormWidget(dataProvider, selection.iterator().next(), true, true) {
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

				new PopupWidget(new FormWidget(dataProvider, true, true), false);

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
