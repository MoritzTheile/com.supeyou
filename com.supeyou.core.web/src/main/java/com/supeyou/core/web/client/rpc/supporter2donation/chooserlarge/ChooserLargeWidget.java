package com.supeyou.core.web.client.rpc.supporter2donation.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.Supporter2DonationDTO;
import com.supeyou.core.iface.dto.Supporter2DonationFetchQuery;
import com.supeyou.core.web.client.rpc.supporter2donation.ListDataProvider;
import com.supeyou.core.web.client.rpc.supporter2donation.chooserlarge.item.ItemWidget;
import com.supeyou.core.web.client.rpc.supporter2donation.form.FormWidget;
import com.supeyou.core.web.client.rpc.supporter2donation.imexport.ExportLink;
import com.supeyou.core.web.client.rpc.supporter2donation.imexport.ImportLink;
import com.supeyou.core.web.client.rpc.supporter2donation.query.QueryWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {

	private final AbstrListDataProvider<Supporter2DonationDTO, Supporter2DonationFetchQuery> dataProvider;

	public ChooserLargeWidget() {

		dataProvider = new ListDataProvider(new Supporter2DonationFetchQuery());
		dataProvider.setPageSize(5);

		final AbstrListWidgetList<Supporter2DonationDTO, Supporter2DonationFetchQuery> widgetList = new AbstrListWidgetList<Supporter2DonationDTO, Supporter2DonationFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Supporter2DonationDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<Supporter2DonationDTO>() {

			@Override
			public void onHasChanged(List<Supporter2DonationDTO> selection) {

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
						new FormWidget(dataProvider, new Supporter2DonationDTO(), true, true) {
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
