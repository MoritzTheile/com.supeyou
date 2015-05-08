package com.supeyou.crudie.web.client.rpc.file.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.crudie.iface.dto.FileDTO;
import com.supeyou.crudie.iface.dto.FileFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.supeyou.crudie.web.client.rpc.file.ListDataProvider;
import com.supeyou.crudie.web.client.rpc.file.chooserlarge.item.ItemWidget;
import com.supeyou.crudie.web.client.rpc.file.form.FormWidget;
import com.supeyou.crudie.web.client.rpc.file.query.QueryWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {
	private final AbstrListDataProvider<FileDTO, FileFetchQuery> dataProvider;

	public ChooserLargeWidget() {

		dataProvider = new ListDataProvider(new FileFetchQuery());
		dataProvider.setPageSize(5);

		AbstrListWidgetList<FileDTO, FileFetchQuery> widgetList = new AbstrListWidgetList<FileDTO, FileFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final FileDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<FileDTO>() {

			@Override
			public void onHasChanged(List<FileDTO> selection) {

				if (selection.size() == 1) {

					showInPopup(selection.iterator().next());

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
				showInPopup(new FileDTO());
			}

		}, ClickEvent.getType());

		dataProvider.fetchData();
	}

	private PopupWidget popup;

	private void showInPopup(FileDTO dto) {
		popup = new PopupWidget(

				new FormWidget(dataProvider, dto) {
					@Override
					protected void close() {
						popup.closePopup();
					}
				}, 100, 700, false
				);
	}

}
