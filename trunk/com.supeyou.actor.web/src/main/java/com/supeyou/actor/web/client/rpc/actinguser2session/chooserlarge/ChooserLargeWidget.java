package com.supeyou.actor.web.client.rpc.actinguser2session.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.actor.iface.dto.ActingUser2SessionDTO;
import com.supeyou.actor.iface.dto.ActingUser2SessionFetchQuery;
import com.supeyou.actor.web.client.rpc.actinguser2session.ListDataProvider;
import com.supeyou.actor.web.client.rpc.actinguser2session.chooserlarge.item.ItemWidget;
import com.supeyou.actor.web.client.rpc.actinguser2session.form.FormWidget;
import com.supeyou.actor.web.client.rpc.actinguser2session.imexport.ExportLink;
import com.supeyou.actor.web.client.rpc.actinguser2session.imexport.ImportLink;
import com.supeyou.actor.web.client.rpc.actinguser2session.query.QueryWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {

	private final AbstrListDataProvider<ActingUser2SessionDTO, ActingUser2SessionFetchQuery> dataProvider;

	public ChooserLargeWidget() {

		dataProvider = new ListDataProvider(new ActingUser2SessionFetchQuery());
		dataProvider.setPageSize(5);

		final AbstrListWidgetList<ActingUser2SessionDTO, ActingUser2SessionFetchQuery> widgetList = new AbstrListWidgetList<ActingUser2SessionDTO, ActingUser2SessionFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final ActingUser2SessionDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<ActingUser2SessionDTO>() {

			@Override
			public void onHasChanged(List<ActingUser2SessionDTO> selection) {

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
						new FormWidget(dataProvider, new ActingUser2SessionDTO(), true, true) {
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
