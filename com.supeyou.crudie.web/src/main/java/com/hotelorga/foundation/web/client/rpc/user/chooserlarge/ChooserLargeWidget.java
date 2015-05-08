package com.hotelorga.foundation.web.client.rpc.user.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.foundation.iface.dto.UserDTO;
import com.hotelorga.foundation.iface.dto.UserFetchQuery;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.hotelorga.foundation.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.hotelorga.foundation.web.client.rpc.user.ListDataProvider;
import com.hotelorga.foundation.web.client.rpc.user.chooserlarge.item.ItemWidget;
import com.hotelorga.foundation.web.client.rpc.user.form.FormWidget;
import com.hotelorga.foundation.web.client.rpc.user.imexport.ExportLink;
import com.hotelorga.foundation.web.client.rpc.user.imexport.ImportLink;
import com.hotelorga.foundation.web.client.rpc.user.query.QueryWidget;
import com.hotelorga.foundation.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {

	private final AbstrListDataProvider<UserDTO, UserFetchQuery> dataProvider;

	public ChooserLargeWidget() {

		dataProvider = new ListDataProvider(new UserFetchQuery());
		dataProvider.setPageSize(5);

		final AbstrListWidgetList<UserDTO, UserFetchQuery> widgetList = new AbstrListWidgetList<UserDTO, UserFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final UserDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<UserDTO>() {

			@Override
			public void onHasChanged(List<UserDTO> selection) {

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
						new FormWidget(dataProvider, new UserDTO()) {
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
